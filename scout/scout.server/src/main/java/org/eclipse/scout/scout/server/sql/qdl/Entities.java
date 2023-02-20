package org.eclipse.scout.scout.server.sql.qdl;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.util.Assertions;
import org.eclipse.scout.rt.server.context.ServerRunContext;
import org.eclipse.scout.scout.server.sql.TableAliases;
import org.eclipse.scout.scout.shared.entities.TableReflectionUtility;
import org.eclipse.scout.scout.shared.entities.common.*;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.eclipse.scout.scout.server.sql.qdl.QDL.and;
import static org.eclipse.scout.scout.server.sql.qdl.QDL.eq;

public class Entities implements IEntities{

  public static final String ENTITY_ALIAS_STRING = "entity";
  public static final String JOINED_ENTITY_ALIAS_STRING = "joined_entity";

  private void assertRunningInServerRunContext(String methodName) {
    Assertions.assertTrue(ServerRunContext.CURRENT.get() instanceof ServerRunContext, "This method does not work without a server session. call \"" + methodName + "\" instead");
  }

  public static <T extends Table> T fetch(Class<T> clazz, Object... primaryKey) {
    return BEANS.get(IEntities.class).fetchInExistingServerContext(clazz, primaryKey);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T extends Table> T fetchInExistingServerContext(Class<T> clazz, Object... primaryKey) {
    assertRunningInServerRunContext("fetch");
    checkArguments(clazz, primaryKey);
    EntityCache cache = BEANS.get(EntityCache.class);

    T entity = cache.opt(clazz, primaryKey);
    if (entity == null) {
      entity = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new EntityInvocationHandler<T>(clazz, primaryKey));
      cache.put(entity, clazz, primaryKey);
    }
    return entity;
  }

  @SuppressWarnings("unchecked")
  private static <T extends Table> T fetch(Class<T> clazz, Object[] data, Object... primaryKey) {
    checkArguments(clazz, primaryKey);
    EntityCache cache = BEANS.get(EntityCache.class);

    T entity = cache.opt(clazz, primaryKey);
    if (entity == null) {
      entity = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new EntityInvocationHandler<T>(clazz, primaryKey, data));
      cache.put(entity, clazz, primaryKey);
    }
    return entity;
  }

  public static <T extends EntityTable> EntityQuery<T> query(Class<T> entity) {
    return new EntityQuery<T>(entity);
  }

  public static <T extends Table> List<T> fetchAll(Class<T> entity) {
    return fetchByCondition(entity, TableAliases.getOrCreate(entity), eq("1", "1"), false);
  }

  public static <T extends Table> List<T> fetchAllLimited(Class<T> entity) {
    return fetchByCondition(entity, TableAliases.getOrCreate(entity), eq("1", "1"), true);
  }

  public static <T extends Table> List<T> fetchByColumnValue(Class<T> entity, Object columnValue, String physicalColumnName) {
    T alias = TableAliases.getOrCreate(entity);
    QDL.Condition condition = eq(new ColumnImpl(physicalColumnName, alias), columnValue);
    return fetchByCondition(entity, alias, condition, false);
  }

  //TODO add checked exception in order to force error handling and prevent errors cause by missing keys
  public <T extends Table> List<T> fetchByConditionInExistingServerContext(Class<T> tableClass, T alias, QDL.Condition condition, boolean limited) {
    assertRunningInServerRunContext("fetchByCondition");
    EntityCache cache = BEANS.get(EntityCache.class);

    List<T> enitites = new ArrayList<>();
    Object[][] primaryKeyValues;
    EntityObjectsBean values;

    primaryKeyValues = cache.optCondition(tableClass, condition);

    if (primaryKeyValues == null) {
      values = fetchValues(tableClass, alias, condition, limited);
      if (values.getPrimaryKeyValues() == null || values.getData() == null || values.getData().length != values.getPrimaryKeyValues().length) {
        throw new ProcessingException("Couldn't fetch data for " + tableClass.getName() + " with condition: " + condition.getQueryContribution());
      }
      for (int i = 0; i < values.getData().length; i++) {
        // not cached: create entity with data (will not issue a db select)
        enitites.add(fetch(tableClass, values.getData()[i], values.getPrimaryKeyValues()[i]));
      }
      cache.putCondition(tableClass, condition, values.getPrimaryKeyValues());
    }
    else {
      // cached: fetch will not issue a db select - entities can be created individually without performance loss
      for (Object[] keyValue : primaryKeyValues) {
        enitites.add(fetch(tableClass, keyValue));
      }
    }
    return enitites;
  }

  public static <T extends Table> List<T> fetchByCondition(Class<T> tableClass, T alias, QDL.Condition condition, boolean limited) {
    return BEANS.get(IEntities.class).fetchByConditionInExistingServerContext(tableClass, alias, condition, limited);
  }

  private static <T extends Table> EntityObjectsBean fetchValues(Class<T> tableClass, T alias, QDL.Condition condition, boolean limited) {
    List<Method> columnMethods = TableReflectionUtility.getColumnMethods(tableClass);
    List<Method> primaryKeyMethods = TableReflectionUtility.getPrimaryKeyMethods(tableClass);

    Column<?>[] columns = new Column[columnMethods.size() + primaryKeyMethods.size()];

    int i = 0;
    for (Method method : primaryKeyMethods) {
      String name = method.getAnnotation(ColumnDef.class).name();
      columns[i++] = new ColumnImpl(name, alias);
    }

    for (Method method : columnMethods) {
      String name = method.getAnnotation(ColumnDef.class).name();
      columns[i++] = new ColumnImpl(name, alias);
    }

    Object[][] result;

    if (limited) {
      result = QDL.select(columns).from(alias).where(condition).executeSelect();
    }
    else {
      result = QDL.select(columns).from(alias).where(condition).executeSelectUnlimited();
    }

    Object[][] primaryKeyValues = new Object[result.length][primaryKeyMethods.size()];
    Object[][] values = new Object[result.length][columnMethods.size()];

    for (int index = 0; index < result.length; index++) {
      primaryKeyValues[index] = Arrays.copyOfRange(result[index], 0, primaryKeyMethods.size());
      values[index] = Arrays.copyOfRange(result[index], primaryKeyMethods.size(), result[index].length);
    }

    return new EntityObjectsBean(primaryKeyValues, values);
  }

  private static <T extends Table> void checkArguments(Class<T> clazz, Object... primaryKey) {
    List<String> primaryKeys = TableReflectionUtility.getPrimaryKeys(clazz);
    if (primaryKey == null || primaryKey.length != primaryKeys.size()) {
      throw new IllegalArgumentException("Table " + clazz.getSimpleName() + " needs " + primaryKeys.size()
        + " primaryKey attributes, provided were only " + (primaryKey != null ? primaryKey.length : 0));
    }
  }

  private static final class EntityInvocationHandler<T extends Table> implements InvocationHandler {
    private final Class<T> m_clazz;
    private final T m_alias;
    private final List<Method> m_columnMethods;
    private final Object[] m_primaryKeyValues;
    private List<Method> m_primaryKeyMethods;
    private Map<String, Column<?>> m_columnNameToColumn;
    private Map<String, Integer> m_columnArrayIndexMap;
    private Object[] m_values;

    public EntityInvocationHandler(Class<T> clazz, Object[] primaryKeyValues) {
      m_clazz = clazz;
      m_primaryKeyValues = primaryKeyValues;
      m_primaryKeyMethods = TableReflectionUtility.getPrimaryKeyMethods(clazz);
      m_columnMethods = TableReflectionUtility.getColumnMethods(clazz);
      m_alias = TableAliases.getOrCreate(m_clazz);
      generateArrayIndexMap();
      generateColumns();
      fetchValues();
    }

    public EntityInvocationHandler(Class<T> clazz, Object[] primaryKeyValues, Object[] values) {
      m_clazz = clazz;
      m_primaryKeyValues = primaryKeyValues;
      m_primaryKeyMethods = TableReflectionUtility.getPrimaryKeyMethods(clazz);
      m_columnMethods = TableReflectionUtility.getColumnMethods(clazz);
      m_alias = TableAliases.getOrCreate(m_clazz);
      generateArrayIndexMap();
      generateColumns();
      m_values = values;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if (method.getName().equals("getValueByColumnMethodName")) {
        return getValueByColumnMethodName((String) args[0]);
      }

      if (m_columnNameToColumn.containsKey(method.getName())) {
        return m_columnNameToColumn.get(method.getName());
      }

      if (method.isAnnotationPresent(Join.class)) {
        return handleJoin(method, proxy);
      }

      if (method.getName().equals("toString")) {
        return "Entity[" + m_clazz.getName() + "]";
      }

      if (method.isDefault()) {
        return invokeDefaultMethod(proxy, method, args);
      }

      throw new UnsupportedOperationException("Cannot invoke method: " + method.getName() + " of class " + m_clazz.getName());
    }

    private Object invokeDefaultMethod(Object proxy, Method method, Object[] args) throws Throwable {
      // See: https://blog.jooq.org/2018/03/28/correct-reflective-access-to-interface-default-methods-in-java-8-9-10/
      Constructor<MethodHandles.Lookup> constructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class);
      constructor.setAccessible(true);
      MethodHandles.Lookup lookup = constructor.newInstance(m_clazz);

      return lookup.in(m_clazz)
        .unreflectSpecial(method, m_clazz)
        .bindTo(proxy)
        .invokeWithArguments(args);
    }

    private Object handleJoin(Method method, Object proxy) {
      if (!method.getAnnotation(Join.class).entityProvider().equals(Join.NullEntityProvider.class)) {
        return handleEntityProviderJoins(method, proxy);
      }

      if (List.class.isAssignableFrom(method.getReturnType())) {
        return handle1toNJoins(method);
      }

      return handleNto1Joins(method);
    }

    private Object handleEntityProviderJoins(Method method, Object proxy) {
      EntityProvider<?, ?> entityProvider = BEANS.get(method.getAnnotation(Join.class).entityProvider());
      Object entity = entityProvider.getEntity(proxy);
      return entity;
    }

    @SuppressWarnings("unchecked")
    private Object handleNto1Joins(Method method) {
      return Entities.fetch((Class<T>) method.getReturnType(), getValueByPyhsicalColumnName(method.getAnnotation(Join.class).foreignKeyColumn()));
    }

    @SuppressWarnings("unchecked")
    private Object handle1toNJoins(Method method) {
      Type[] actualTypeArguments = ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments();
      if (actualTypeArguments == null || actualTypeArguments.length == 0) {
        throw new UnsupportedOperationException("Raw lists are not supported: join entity cannot be resolved");
      }
      return Entities.fetchByColumnValue((Class<T>) actualTypeArguments[0], getPrimaryKeyValue(), method.getAnnotation(Join.class).foreignKeyColumn());
    }

    private Object getValueByColumnMethodName(String columnMethodName) {
      return m_values[columnMethodNameToArrayIndex(columnMethodName)];
    }

    private Object getValueByPyhsicalColumnName(String physicalColumnName) {
      for (Method method : m_columnMethods) {
        if (physicalColumnName.equals(method.getAnnotation(ColumnDef.class).name())) {
          return getValueByColumnMethodName(method.getName());
        }
      }
      throw new ProcessingException("Couldn't find value for join column: " + physicalColumnName + ", entity: " + m_alias.name());
    }

    private Object getPrimaryKeyValue() {
      if (m_primaryKeyMethods == null || m_primaryKeyMethods.size() == 0) {
        throw new UnsupportedOperationException("a primary key is nessesary for a join opperation");
      }

      if (m_primaryKeyMethods.size() > 1) {
        throw new UnsupportedOperationException("composite primary keys are not supported in a join opperation");
      }
      return getValueByColumnMethodName(m_primaryKeyMethods.get(0).getName());
    }

    private T getAlias() {
      return m_alias;
    }

    private int columnMethodNameToArrayIndex(String columnMethodName) {
      return m_columnArrayIndexMap.get(columnMethodName);
    }

    private void generateColumns() {
      m_columnNameToColumn = new HashMap<>();
      for (Method method : m_columnMethods) {
        m_columnNameToColumn.put(method.getName(), generateColumn(method));
      }
    }

    private void generateArrayIndexMap() {
      m_columnArrayIndexMap = new HashMap<>();
      int i = 0;
      for (Method method : m_columnMethods) {
        m_columnArrayIndexMap.put(method.getName(), i++);
      }
    }

    private void fetchValues() {
      Column<?>[] columns = new Column[m_columnMethods.size()];
      int i = 0;
      for (Method method : m_columnMethods) {
        String name = method.getAnnotation(ColumnDef.class).name();
        columns[i++] = new ColumnImpl(name, m_alias);
      }
      Object[][] result = QDL.select(columns).from(m_alias).where(getPrimaryKeyCondition(m_alias)).executeSelectUnlimited();
      checkResult(result);
      m_values = result[0];
    }

    private void checkResult(Object[][] result) {
      if (result == null || result.length == 0) {
        throw new ProcessingException("Couldn't fetch data for " + m_clazz.getName() + " with primaryKey: " + getPrimaryKeyDescription());
      }
      if (result.length > 1) {
        throw new ProcessingException("More than one row returned for " + m_clazz.getName() + " with primaryKey: " + getPrimaryKeyDescription());
      }
    }

    private Collection<QDL.Condition> getPrimaryKeyCondition(T alias) {
      QDL.Condition[] conditions = new QDL.Condition[m_primaryKeyValues.length];
      for (int i = 0; i < m_primaryKeyValues.length; i++) {
        conditions[i] = eq(new ColumnImpl(m_primaryKeyMethods.get(i).getAnnotation(ColumnDef.class).name(), alias), m_primaryKeyValues[i]);
      }
      return (Collection<QDL.Condition>) and(conditions);
    }

    private String getPrimaryKeyDescription() {
      if (m_primaryKeyValues == null || (m_primaryKeyValues.length == 1 && m_primaryKeyValues[0] == null)) {
        return "no primary values key specified";
      }
      return Arrays.asList(m_primaryKeyValues).stream().map(o -> o.toString()).collect(Collectors.joining(", "));
    }

    private Column<?> generateColumn(Method column) {
      return (Column<?>) Proxy.newProxyInstance(m_clazz.getClassLoader(), new Class[]{Column.class}, new ValueColumnInvocationHandler(column));
    }

    private final class ValueColumnInvocationHandler implements InvocationHandler {

      private final Method m_columnMethod;

      public ValueColumnInvocationHandler(Method columnMethod) {
        m_columnMethod = columnMethod;
      }

      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("value")) {
          return EntityInvocationHandler.this.getValueByColumnMethodName(m_columnMethod.getName());
        }

        if (method.getName().equals("name")) {
          return m_columnMethod.getAnnotation(ColumnDef.class).name();
        }

        if (method.getName().equals("table")) {
          return EntityInvocationHandler.this.getAlias();
        }

        if (method.getName().equals("fullyQualifiedName")) {
          return EntityInvocationHandler.this.getAlias().alias() + "." + m_columnMethod.getAnnotation(ColumnDef.class).name().toLowerCase();
        }

        if (method.getName().equals("getBindBases")) {
          return null;
        }

        throw new UnsupportedOperationException();
      }
    }
  }

}
