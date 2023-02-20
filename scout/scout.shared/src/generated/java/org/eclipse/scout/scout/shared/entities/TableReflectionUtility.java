package org.eclipse.scout.scout.shared.entities;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.scout.shared.entities.common.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class TableReflectionUtility {

  public static List<String> getPrimaryKeys(Class<? extends Table> clazz) {
    List<String> primaryKeys = new ArrayList<>();
    for (Method method : getPrimaryKeyMethods(clazz)) {
      primaryKeys.add(method.getAnnotation(ColumnDef.class).name().toLowerCase());
    }
    return primaryKeys;
  }

  public static List<Method> getPrimaryKeyMethods(Class<? extends Table> clazz) {
    List<Method> primaryKeys = new ArrayList<>();

    for (Method method : TableReflectionUtility.getColumnMethods(clazz)) {
      if (method.isAnnotationPresent(PrimaryKey.class)) {
        primaryKeys.add(method);
      }
    }
    sortColumnMethodsByOrder(primaryKeys);
    return primaryKeys;
  }

  public static List<String> getSequences(Class<? extends Table> clazz) {
    List<String> sequences = new ArrayList<>();

    for (Method method : TableReflectionUtility.getSequenceMethods(clazz)) {
      sequences.add(method.getAnnotation(Sequence.class).name());
    }

    return sequences;
  }

  public static List<Method> getSequenceMethods(Class<? extends Table> clazz) {
    List<Method> methods = new ArrayList<>();

    for (Method method : TableReflectionUtility.getColumnMethods(clazz)) {
      if (method.isAnnotationPresent(Sequence.class)) {
        methods.add(method);
      }
    }
    return methods;
  }

  public static List<Method> getColumnMethods(Class<? extends Table> clazz) {
    List<Method> methods = new ArrayList<Method>();
    for (Method method : clazz.getMethods()) {
      if (method.isAnnotationPresent(ColumnDef.class)) {
        if (!method.isAnnotationPresent(Order.class)) {
          throw new RuntimeException("In order to generate the SQL Create Statements, every column must be annotated with @Order to insure the right order of the columns. "
            + "The column" + clazz.getAnnotation(TableDef.class).name() + "." + method.getAnnotation(ColumnDef.class).name() + " is not annotated with order.");
        }
        methods.add(method);
      }
    }
    sortColumnMethodsByOrder(methods);
    return methods;
  }

  public static Method getColumnMethodByPhysicalColumnName(Class<? extends Table> clazz, String physicalColumnName) {
    for (Method method : clazz.getMethods()) {
      if (method.isAnnotationPresent(ColumnDef.class) && StringUtility.equalsIgnoreCase(method.getAnnotation(ColumnDef.class).name(), physicalColumnName)) {
        return method;
      }
    }
    return null;
  }

  public static void sortColumnMethodsByOrder(List<Method> methods) {
    Collections.sort(methods, new Comparator<Method>() {

      @Override
      public int compare(Method o1, Method o2) {
        return Double.compare(o1.getAnnotation(Order.class).value(), o2.getAnnotation(Order.class).value());
      }
    });
  }

  //FIXME: find general solution
  public static String getDefaultValueString(ColumnDef column, DefaultValue defaultValue) {
    if (defaultValue.value().equalsIgnoreCase("NULL") || defaultValue.value().equals("")) {
      return "NULL";
    }
    if (column.type().equals(SqlDataType.VARCHAR_255) || column.type().equals(SqlDataType.VARCHAR_4000)) {
      return " CAST (" + defaultValue.value() + ") AS CHARACTER VARYING";
    }
    if(column.type().equals(SqlDataType.TEXT)) {
      return " CAST (" + defaultValue.value() + ") AS TEXT";
    }

    return defaultValue.value();
  }

  public static String getDefaultValueString(Method columnMethod) {
    if(columnMethod.isAnnotationPresent(DefaultValue.class)) {
      return getDefaultValueString(columnMethod.getAnnotation(ColumnDef.class), columnMethod.getAnnotation(DefaultValue.class));
    }
    if(columnMethod.isAnnotationPresent(Sequence.class)) {
      return "nextval('" + columnMethod.getAnnotation(Sequence.class).name() + "'::regclass)";
    }

    return null;
  }

  public static String getViewColumnDefinition(StringBuilder builder, Method method) {
    ColumnDef column = method.getAnnotation(ColumnDef.class);
    return column.name().toLowerCase();
  }

  public static boolean hasSequences(Class<? extends Table> clazz) {
    //FIXME returns always false
    for (Method method : clazz.getMethods()) {
      if (method.isAnnotationPresent(Sequence.class)) {
        return true;
      }
    }
    return false;
  }

  public static Column<?> getSingleForeignKeyToJoinedTableColumn(EntityTable alias, Class<? extends EntityTable> joinedTable) {
    Class<? extends EntityTable> tableClass = alias.getTableClass();
    List<Method> foreignKeyColumnMethods = Arrays.asList(tableClass.getMethods()).stream()
      .filter(c -> c.getReturnType().equals(joinedTable) && c.isAnnotationPresent(Join.class))
      .collect(Collectors.toList());

    checkSingularity(foreignKeyColumnMethods, "a foreign key is nessesary for a join opperation", "composite foreign keys are not supported in a join opperation");
    String physicalColumnName = foreignKeyColumnMethods.get(0).getAnnotation(Join.class).foreignKeyColumn();

    if(physicalColumnName.isEmpty()) {
      throw new UnsupportedOperationException("EntityProviders are not supported");
    }
    return getColumnByColumnName(physicalColumnName, alias);
  }

  public static Column<?> getSinglePrimaryKey(EntityTable alias) {
    Class<? extends EntityTable> tableClass = alias.getTableClass();
    List<Method> primaryKeyMethods = TableReflectionUtility.getPrimaryKeyMethods(tableClass);
    checkSingularity(primaryKeyMethods, "a primary key is nessesary for a join opperation", "composite primary keys are not supported in a join opperation");
    String physicalColumnName = primaryKeyMethods.get(0).getAnnotation(ColumnDef.class).name();
    return getColumnByColumnName(physicalColumnName, alias);
  }

  public static boolean isEntityProvider(EntityTable alias, Class<? extends EntityTable> joinedTable) {
    Class<? extends EntityTable> tableClass = alias.getTableClass();
    List<Method> foreignKeyColumnMethods = Arrays.asList(tableClass.getMethods()).stream()
      .filter(c -> c.getReturnType().equals(joinedTable) && c.isAnnotationPresent(Join.class))
      .collect(Collectors.toList());

    return foreignKeyColumnMethods.get(0).getAnnotation(Join.class).foreignKeyColumn() == null;
  }

  public static Column<?> getColumnByColumnName(String physicalColumnName, Table alias) {
    Class<? extends Table> tableClass = alias.getTableClass();
    Method method = getColumnMethodByPhysicalColumnName(tableClass, physicalColumnName);

    try {
      return (Column<?>) method.invoke(alias);
    }
    catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      throw new ProcessingException();
    }
  }

  private static void checkSingularity(List<Method> foreignKeyColumnMethods, String textIfEmpty, String textIfNotSingular) {
    if (foreignKeyColumnMethods == null || foreignKeyColumnMethods.isEmpty()) {
      throw new UnsupportedOperationException(textIfEmpty);
    }
    else if (foreignKeyColumnMethods.size() > 1) {
      throw new UnsupportedOperationException(textIfNotSingular);
    }
  }

}
