package org.eclipse.scout.scout.server.sql;

import org.eclipse.scout.scout.server.sql.qdl.Entities;
import org.eclipse.scout.scout.shared.entities.TableReflectionUtility;
import org.eclipse.scout.scout.shared.entities.common.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class TableAliases {

  public static Map<Table, String> m_aliases = new HashMap<>();
  private static final Map<Class<? extends Table>, Object> m_tableAliases = new HashMap<>();

  private static String getAliasForClass(Table table) {

    String alias = m_aliases.get(table);
    if (alias != null) {
      return alias;
    }

    String tableName = table.name().toLowerCase();
    alias = "" + tableName.charAt(0);

    int trial = 1;
    while (m_aliases.containsValue(alias)) {
      alias = tableName.charAt(0) + "" + trial++;
    }

    m_aliases.put(table, alias);
    return alias;
  }

  public static <T extends Table> T getOrCreate(Class<T> entity) {
    return getOrCreate(entity, Entities.ENTITY_ALIAS_STRING);
  }

  @SuppressWarnings("unchecked")
  public static <T extends Table> T getOrCreate(Class<T> entity, String aliasString) {
    T alias = null;
    if (m_tableAliases.containsKey(entity)) {
      alias = (T) m_tableAliases.get(entity);
    }
    else {
      alias = TableAliases.create(entity, aliasString);
      m_tableAliases.put(entity, alias);
    }
    return alias;
  }

  @SuppressWarnings("unchecked")
  public static <T extends Table> T create(Class<T> clazz) {
    T table = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new TableAliasInvocationHandler<T>(clazz, null));
    getAliasForClass(table);
    return table;
  }

  @SuppressWarnings("unchecked")
  public static <T extends Table> T create(Class<T> clazz, String alias) {
    T table = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new TableAliasInvocationHandler<T>(clazz, alias));
    getAliasForClass(table);
    return table;
  }

  private static final class TableAliasInvocationHandler<T extends Table> implements InvocationHandler {
    private final Class<T> m_clazz;
    private String m_alias;

    private TableAliasInvocationHandler(Class<T> clazz, String alias) {
      m_clazz = clazz;
      m_alias = alias;
    }

    @SuppressWarnings("unused")
    public Class<T> getClazz() {
      return m_clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

      if (method.isAnnotationPresent(ColumnDef.class)) {
        return handleColumn(proxy, method, args);
      }

      if (method.getName().equals("alias")) {
        return m_alias == null ? getAliasForClass((Table) proxy) : m_alias;
      }

      if (method.getName().equals("name")) {
        if (m_clazz.isAnnotationPresent(TableDef.class)) {
          return m_clazz.getAnnotation(TableDef.class).name();
        }
        else if (m_clazz.isAnnotationPresent(ViewDef.class)) {
          return m_clazz.getAnnotation(ViewDef.class).name();
        }
      }

      if (method.getName().equals("hasSequences")) {
        if (m_clazz.isAnnotationPresent(TableDef.class)) {
          return TableReflectionUtility.hasSequences(m_clazz);
        }
      }

      if (method.getName().equals("hashCode")) {
        // a proxy object does not implement hashCode by default. We need it
        // since we put the proxy object in a HashMap.
        return System.identityHashCode(proxy);
      }

      if (method.getName().equals("getTableClass")) {
        return m_clazz;
      }

      if (method.getName().equals("toString")) {
        return "TableAlias[" + m_clazz.getName() + "]";
      }

      return null;
    }
  }

  protected static Object handleColumn(Object proxy, Method method, Object[] args) {
    String name = method.getAnnotation(ColumnDef.class).name();
    Table table = (Table) proxy;
    return new ColumnImpl(name, table);
  }

}
