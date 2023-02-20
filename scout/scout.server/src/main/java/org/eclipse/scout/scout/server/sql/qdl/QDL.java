package org.eclipse.scout.scout.server.sql.qdl;


import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.holders.IHolder;
import org.eclipse.scout.rt.platform.holders.ITableBeanHolder;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.util.*;
import org.eclipse.scout.rt.server.context.ServerRunContext;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;
import org.eclipse.scout.scout.shared.entities.common.Column;
import org.eclipse.scout.scout.shared.entities.common.SqlDataType;
import org.eclipse.scout.scout.shared.entities.common.Table;
import org.eclipse.scout.scout.shared.parameter.MaxRowsParameter;
import org.eclipse.scout.scout.shared.parameter.PARAMETERS;

import java.beans.Introspector;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.eclipse.scout.rt.platform.util.CollectionUtility.combine;

public class QDL {

  private static long f_bindBaseNr = 0;

  private static String createBindBaseName() {
    return "__" + ++f_bindBaseNr;
  }

  public static QuerySpecification select(Column<?>... columns) {
    Query query = new Query();
    query.append("SELECT");

    String queryContribution = Arrays.asList(columns)
      .stream()
      .map(c -> c.fullyQualifiedName())
      .collect(Collectors.joining(", "));

    query.append(queryContribution);

    for (Column<?> column : columns) {
      query.addBindBases(column.getBindBases());
    }

    return new QuerySpecificationImpl(query);
  }

  public static QuerySpecification selectInto(ITableBeanHolder bind, ColumnMapping... columns) {
    Query query = new Query();
    query.addBindBases(new NVPair("page", bind));
    query.append("SELECT");

    query.append(Arrays.asList(columns).stream().map(c -> c.column().fullyQualifiedName())
      .collect(Collectors.joining(", ")));

    query.append("INTO");

    query.append(Arrays.asList(columns).stream().map(c -> c.fullyQualifiedTableRowId())
      .collect(Collectors.joining(", ")));

    return new QuerySpecificationImpl(query);
  }

  public static QuerySpecification selectDistinct(Column<?>... columns) {
    Query query = new Query();
    query.append("SELECT DISTINCT");

    String queryContribution = Arrays.asList(columns).stream().map(c -> c.fullyQualifiedName())
      .collect(Collectors.joining(", "));

    query.append(queryContribution);
    return new QuerySpecificationImpl(query);
  }

  public static UpdateClause update(Table table) {

    Query query = new Query();
    query.append("UPDATE");
    query.append(table.name().toLowerCase() + " " + table.alias());
    return new UpdateClauseImpl(query);
  }

  public static InsertClause insertInto(Table table) {

    Query query = new Query();
    query.append("INSERT INTO ");
    query.append(table.name().toLowerCase());
    return new InsertClauseImpl(query);
  }

  public static FromClause deleteFrom(Table table) {

    Query query = new Query();
    query.append("DELETE FROM ");
    query.append(table.name().toLowerCase() + " " + table.alias());
    return new FormClauseImpl(query);
  }

  public static Long nextVal(String sequence) throws ProcessingException {
    String s = "select nextval('" + sequence + "')";
    Object[][] ret = SQL.select(s);
    if (ret.length == 1) {
      return NumberUtility.toLong(ObjectUtility.nvl((Number) ret[0][0], 0));
    }
    return 0L;
  }

  public static Long currVal(String sequence) throws ProcessingException {
    String s = "SELECT CURRVAL('" + sequence + "')";
    Object[][] ret = SQL.select(s);
    if (ret.length == 1) {
      return NumberUtility.toLong(ObjectUtility.nvl((Number) ret[0][0], 0));
    }
    return 0L;
  }

  public static ColumnMapping col(Column<?> column, String tableRowId) {
    return new ColumnMappingImpl(column, tableRowId);
  }

  public static Column<?> firstLine(Column<?> column) {
    CustomColumn customColumn = new CustomColumn("(REGEXP_SPLIT_TO_ARRAY(" + column.fullyQualifiedName() + ", E'\\\\n'))[1]");
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static Column<?> count(Column<?> column) {
    CustomColumn customColumn = new CustomColumn("COUNT (" + column.fullyQualifiedName() + ")");
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static Column<?> rowNum(Column<?> column) {
    CustomColumn customColumn = new CustomColumn("ROW_NUMBER() OVER (ORDER BY " + column.fullyQualifiedName() + ")");
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static Column<?> sum(Column<?> column) {
    CustomColumn customColumn = new CustomColumn("SUM(" + column.fullyQualifiedName() + ")");
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static Column<?> round(Column<?> column) {
    CustomColumn customColumn = new CustomColumn("ROUND(" + column.fullyQualifiedName() + ")");
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static Column<?> round(Column<?> column, int decimalPlaces) {
    CustomColumn customColumn = new CustomColumn("ROUND(" + column.fullyQualifiedName() + ", " + decimalPlaces + ")");
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static Column<?> min(Column<?> column) {
    CustomColumn customColumn = new CustomColumn("MIN(" + column.fullyQualifiedName() + ")");
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static Column<?> max(Column<?> column) {
    CustomColumn customColumn = new CustomColumn("MAX(" + column.fullyQualifiedName() + ")");
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static Column<?> times(Column<?> column1, Column<?> column2) {
    CustomColumn customColumn = new CustomColumn("(" + column1.fullyQualifiedName() + ") * (" + column2.fullyQualifiedName() + ")");
    customColumn.getBindBases().addAll(column1.getBindBases());
    customColumn.getBindBases().addAll(column2.getBindBases());
    return customColumn;
  }

  public static Column<?> times(Column<?> column, int integer) {
    CustomColumn customColumn = new CustomColumn("(" + column.fullyQualifiedName() + ") * (" + integer + ")");
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static Column<?> times(Column<?> column1, QueryFragment queryFragment) {
    CustomColumn customColumn = new CustomColumn("(" + column1.fullyQualifiedName() + ") * (" + queryFragment.getFragmentString() + ")");
    customColumn.getBindBases().addAll(queryFragment.getBindBases());
    customColumn.getBindBases().addAll(column1.getBindBases());
    return customColumn;
  }

  public static Column<?> divide(Column<?> column1, Column<?> column2) {
    CustomColumn customColumn = new CustomColumn("(" + column1.fullyQualifiedName() + ") / (" + column2.fullyQualifiedName() + ")");
    customColumn.getBindBases().addAll(column1.getBindBases());
    customColumn.getBindBases().addAll(column2.getBindBases());
    return customColumn;
  }

  public static Column<?> divide(Column<?> column1, QueryFragment queryFragment) {
    CustomColumn customColumn = new CustomColumn("(" + column1.fullyQualifiedName() + ") / (" + queryFragment.getFragmentString() + ")");
    customColumn.getBindBases().addAll(queryFragment.getBindBases());
    customColumn.getBindBases().addAll(column1.getBindBases());
    return customColumn;
  }

  public static Column<?> plus(Column<?> column1, Column<?> column2) {
    CustomColumn customColumn = new CustomColumn(column1.fullyQualifiedName() + " + " + column2.fullyQualifiedName());
    customColumn.getBindBases().addAll(column1.getBindBases());
    customColumn.getBindBases().addAll(column2.getBindBases());
    return customColumn;
  }

  public static Column<?> plus(Column<?> column, int integer) {
    CustomColumn customColumn = new CustomColumn(column.fullyQualifiedName() + " + " + integer);
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static Column<?> plus(Column<?> column, BigDecimal bigdecimal) {
    CustomColumn customColumn = new CustomColumn(column.fullyQualifiedName() + " + " + bigdecimal);
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static Column<?> plus(Column<?> column1, QueryFragment queryFragment) {
    CustomColumn customColumn = new CustomColumn("(" + column1.fullyQualifiedName() + ") + (" + queryFragment.getFragmentString() + ")");
    customColumn.getBindBases().addAll(queryFragment.getBindBases());
    customColumn.getBindBases().addAll(column1.getBindBases());
    return customColumn;
  }

  public static Column<?> minus(Column<?> column1, Column<?> column2) {
    CustomColumn customColumn = new CustomColumn(column1.fullyQualifiedName() + " - " + column2.fullyQualifiedName());
    customColumn.getBindBases().addAll(column1.getBindBases());
    customColumn.getBindBases().addAll(column2.getBindBases());
    return customColumn;
  }

  public static Column<?> concat(Column<?> column, String... fragment) {
    String parameters = fragment[0];
    for (int count = 1; count < fragment.length; count++) {
      parameters += " || " + fragment[count];
    }

    CustomColumn customColumn = new CustomColumn(column.fullyQualifiedName() + " || " + parameters);
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static Column<?> lower(Column<?> column) {
    CustomColumn customColumn = new CustomColumn("LOWER(" + column.fullyQualifiedName() + ")");
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static Column<?> to_char(Column<?> column, String format) {
    CustomColumn customColumn = new CustomColumn("TO_CHAR( " + column.fullyQualifiedName() + ", '" + format + "')");
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static Column<?> coalesce(Column<?> column, String altValue) {
    CustomColumn customColumn = new CustomColumn("COALESCE( " + column.fullyQualifiedName() + ", " + altValue + ")");
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static Column<?> extract(String part, Column<?> column) {
    CustomColumn customColumn = new CustomColumn("EXTRACT(" + part + " FROM " + column.fullyQualifiedName() + ")");
    customColumn.getBindBases().addAll(column.getBindBases());
    return customColumn;
  }

  public static QueryFragment toChar(Column<?> column, String format) {
    return new QueryFragment("TO_CHAR( " + column.fullyQualifiedName() + ", '" + format + "')");
  }

  public static QueryFragment coalesce(String string1, String string2) {
    return new QueryFragment("COALESCE( " + string1 + ", " + string2 + ")");
  }

  public static QueryFragment stringAgg(Column<?> column, String delimiter) {
    return new QueryFragment("STRING_AGG(" + column.fullyQualifiedName() + ", '" + delimiter + "')");
  }

  public static QueryFragment stringAgg(QueryFragment fragment, String delimiter) {
    return new QueryFragment("STRING_AGG(" + fragment.getFragmentString() + ", '" + delimiter + "')");
  }

  public static <T> QueryFragment cast(String value, SqlDataType type) {
    return new QueryFragment("CAST(" + value + " AS " + type.identifier() + ")");
  }

  public static CaseStatement caseWhen(Condition condition) {
    return new CaseStatement(condition, condition.getBindBases());
  }

  public static QueryFragment nullValue() {
    return new QueryFragment("null");
  }

  private static class WhenThenStatement {
    private Condition m_when;
    private Column<?> m_then;

    public WhenThenStatement(Condition when, Column<?> then) {
      this.m_then = then;
      this.m_when = when;
    }

    public String getQueryContribution() {
      return " WHEN " + m_when.getQueryContribution() + " THEN " + m_then.fullyQualifiedName();
    }
  }

  public static class CaseStatement {
    private Condition m_condition;
    private Column<?> m_then;
    private Column<?> m_else;
    private List<WhenThenStatement> m_whenThens = new ArrayList<>();
    private List<Object> m_bindBases = new ArrayList<>();

    public CaseStatement(Condition condition) {
      this.m_condition = condition;
    }

    public CaseStatement(Condition condition, List<Object> bindBases) {
      m_condition = condition;
      m_bindBases.addAll(bindBases);
    }

    public CaseStatement then(Column<?> col) {
      m_then = col;
      return this;
    }

    public CaseStatement whenThen(Condition when, Column<?> then) {
      m_whenThens.add(new WhenThenStatement(when, then));
      m_bindBases.add(when.getBindBases());
      m_bindBases.add(then.getBindBases());
      return this;
    }

    public CaseStatement otherwise(Column<?> col) {
      m_else = col;
      return this;
    }

    public Column<?> end() {
      String whenThens = m_whenThens.stream().map(wt -> wt.getQueryContribution()).collect(Collectors.joining());
      return new CustomColumn(
        new QueryFragment("CASE WHEN " + m_condition.getQueryContribution() + " THEN " + m_then.fullyQualifiedName() + whenThens + " ELSE " + (m_else != null ? m_else.fullyQualifiedName() : "''") + " END", m_bindBases));
    }
  }

  public static QueryFragment bindObject(Object obj) {
    if (obj == null) {
      return new QueryFragment("null");
    }
    return bindNvPair(new NVPair(createBindBaseName(), obj));
  }

  public static QueryFragment bindString(String str) {
    if (str == null) {
      return new QueryFragment("null");
    }
    return bindNvPair(new NVPair(createBindBaseName(), str));
  }

  public static QueryFragment bindBoolean(Boolean value) {
    if (value == null) {
      return new QueryFragment("null");
    }
    return bindBoolean(value.booleanValue());
  }

  public static QueryFragment bindLong(Long value) {
    if (value == null) {
      return new QueryFragment("null");
    }
    return bindNvPair(new NVPair(createBindBaseName(), value));
  }

  public static QueryFragment bindBigDecimal(BigDecimal value) {
    if (value == null) {
      return new QueryFragment("null");
    }
    return bindNvPair(new NVPair(createBindBaseName(), value));
  }

  public static QueryFragment bindNumber(Number value) {
    if (value == null) {
      return null;
    }
    return new QueryFragment("" + value);
  }

  public static QueryFragment bindDate(Date date) {
    if (date == null) {
      return null;
    }
    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return new QueryFragment("'" + dt.format(date) + "'");
  }

  public static Column<?> trunDay(Column<?> column) {
    return new CustomColumn("date_trunc('day', " + column.fullyQualifiedName() + ")");
  }

  public static Column<?> trunMonth(Column<?> column) {
    return new CustomColumn("date_trunc('month', " + column.fullyQualifiedName() + ")");
  }

  public static Column<?> datePartYear(Column<?> column) {
    return new CustomColumn("date_part('year', " + column.fullyQualifiedName() + ")");
  }

  public static Column<?> datePartMonth(Column<?> column) {
    return new CustomColumn("date_part('month', " + column.fullyQualifiedName() + ")");
  }

  public static Column<?> date(Column<?> column) {
    return new CustomColumn("date(" + column.fullyQualifiedName() + ")");
  }

  public static QueryFragment bindBlob(byte[] data) {
    NVPair nv = new NVPair(createBindBaseName(), data);
    return bindNvPair(nv);
  }

  public static QueryFragment bindHolder(Class<? extends IHolder<?>> holder) {
    String className = holder.getSimpleName();
    if (className.endsWith("Property")) {
      className = className.substring(0, className.length() - "Property".length());
    }
    return new QueryFragment(":" + Introspector.decapitalize(className), holder);
  }

  public static QueryFragment bindValueField(Class<? extends AbstractValueFieldData<?>> valueField) {
    return new QueryFragment(":" + Introspector.decapitalize(valueField.getSimpleName()));
  }

  public static QueryFragment bindNvPair(NVPair nvPair) {
    return new QueryFragment(":" + nvPair.getName(), nvPair);
  }

  public static QueryFragment bindListNvPair(NVPair nvPair) {
    return new QueryFragment(":{" + nvPair.getName() + "}", nvPair);
  }

  public static <T> QueryFragment bindList(List<T> list) {
    NVPair nv = new NVPair(createBindBaseName(), list);
    return new QueryFragment(":{" + nv.getName() + "}", nv);
  }

  public static QueryFragment bindBooleanValueField(Class<? extends AbstractValueFieldData<?>> valueField) {
    return new QueryFragment("CAST(:" + Introspector.decapitalize(valueField.getSimpleName()) + " AS BOOLEAN)",
      valueField);
  }

  public static QueryFragment bindBoolean(boolean bool) {
    return new QueryFragment(bool ? "TRUE" : "FALSE");
  }

  public static QueryFragment bindProperty(Class<? extends AbstractPropertyData<?>> property) {
    String className = property.getSimpleName();
    if (className.endsWith("Property")) {
      className = className.substring(0, className.length() - "Property".length());
    }
    return new QueryFragment(":" + Introspector.decapitalize(className), property);
  }

  public static Condition bracket(Condition... conditions) {

    List<Condition> conditionList = Arrays.asList(conditions).stream().filter(Objects::nonNull)
      .collect(Collectors.toList());
    if (conditionList.isEmpty()) {
      return null;
    }
    String queryContribution = conditionList.stream().map(c -> c.getQueryContribution())
      .collect(Collectors.joining(" AND "));
    List<Object> binds = conditionList.stream().flatMap(c -> c.getBindBases().stream())
      .collect(Collectors.toList());

    if (StringUtility.isNullOrEmpty(queryContribution)) {
      queryContribution = "1 = 1";
    }

    return new ConditionImpl("( " + queryContribution + " )", binds);
  }

  public static Executable bracket(Executable executable) {
    executable.getQuery().braket();
    return executable;
  }

  public static Condition eq(String string, String string2) {
    return new ConditionImpl(string + " = " + string2);
  }

  public static Condition eq(boolean boolean1, boolean boolean2) {
    return new ConditionImpl(boolean1 + " = " + boolean2);
  }

  public static Condition eq(Long longValue1, Long longValue2) {
    return new ConditionImpl(longValue1 + " = " + longValue2);
  }

  public static Condition eq(Column<?> column, Object o) {
    return new ConditionImpl(column.fullyQualifiedName() + " = " + o, column.getBindBases());
  }

  public static Condition eq(Column<?> column, String string) {
    return new ConditionImpl(column.fullyQualifiedName() + " = " + string, column.getBindBases());
  }

  public static Condition eq(Column<?> column, Executable executable) {
    return new ConditionImpl(column.fullyQualifiedName() + " = " + executable.getQueryString(), combine(column.getBindBases(), executable.getQuery().getBindBases()));
  }

  public static Condition eq(Column<?> column, QueryFragment fragment) {
    return new ConditionImpl(
      column.fullyQualifiedName() + " = " + (fragment != null ? fragment.getFragmentString() : "null"),
      (fragment != null ? fragment.getBindBases() : null));
  }

  public static Condition eq(Column<?> column, Long longValue) {
    return new ConditionImpl(column.fullyQualifiedName() + " = " + longValue, column.getBindBases());
  }

  public static Condition eq(Column<?> column, boolean booleanValue) {
    return new ConditionImpl(column.fullyQualifiedName() + " = " + booleanValue, column.getBindBases());
  }

  public static Condition eq(Column<?> column, long longValue) {
    return new ConditionImpl(column.fullyQualifiedName() + " = " + longValue, column.getBindBases());
  }

  public static Condition eq(Column<?> column, Double doubleValue) {
    return new ConditionImpl(column.fullyQualifiedName() + " = " + doubleValue, column.getBindBases());
  }

  public static Condition ne(String string, String string2) {
    return new ConditionImpl(string + " <> " + string2);
  }

  public static Condition ne(Column<?> column, Double doubleValue) {
    return new ConditionImpl(column.fullyQualifiedName() + " <> " + doubleValue, column.getBindBases());
  }

  public static Condition ne(Column<?> column, Long longValue) {
    return new ConditionImpl(column.fullyQualifiedName() + " <> " + longValue, column.getBindBases());
  }

  public static Condition ne(Column<?> column, String stringValue) {
    return new ConditionImpl(column.fullyQualifiedName() + " <> " + stringValue, column.getBindBases());
  }

  public static Condition ne(Column<?> column, QueryFragment fragment) {
    return new ConditionImpl(
      column.fullyQualifiedName() + " <> " + (fragment != null ? fragment.getFragmentString() : "null"),
      (fragment != null ? fragment.getBindBases() : null));
  }

  public static Condition ne(Column<?> column, Column<?> column2) {
    return new ConditionImpl(column.fullyQualifiedName() + " <> " + column2.fullyQualifiedName(), combine(column.getBindBases(), column2.getBindBases()));
  }

  public static Condition eq(Column<?> column, Column<?> column2) {
    return new ConditionImpl(column.fullyQualifiedName() + " = " + column2.fullyQualifiedName(), combine(column.getBindBases(), column2.getBindBases()));
  }

  public static Condition eq(Column<?> column, Class<? extends AbstractPropertyData<?>> property) {
    return new ConditionImpl(column.fullyQualifiedName() + " = " + bindProperty(property), column.getBindBases());
  }

  public static Condition isNotNull(Column<?> column) {
    return new ConditionImpl(column.fullyQualifiedName() + " IS NOT NULL", column.getBindBases());
  }

  public static Condition isNull(Column<?> column) {
    return new ConditionImpl(column.fullyQualifiedName() + " IS NULL", column.getBindBases());
  }

  public static Condition optionalEq(Boolean boolean1, Boolean boolean2) {
    if (boolean1 == null || boolean2 == null) {
      return null;
    }
    return eq(boolean1, boolean2);
  }

  public static Condition optionalEq(Column<?> column, String string) {
    if (column == null || string == null || string.isEmpty() || StringUtility.equalsIgnoreCase("null", string)) {
      return null;
    }
    return eq(column, string);
  }

  public static Condition optionalEq(Column<?> column, QueryFragment fragment) {
    if (column == null || fragment == null || StringUtility.equalsIgnoreCase("null", fragment.getFragmentString())) {
      return null;
    }
    return eq(column, fragment);
  }

  public static Condition optionalEq(Column<?> column, Long id) {
    if (column == null || id == null) {
      return null;
    }
    return eq(column, id);
  }

  public static Condition optionalEq(Column<?> column, Boolean value) {
    if (column == null || value == null) {
      return null;
    }
    return eq(column, value);
  }

  private static Condition greaterThan(String value1, String value2, List<Object> bindBases) {
    return new ConditionImpl(value1 + " > " + value2, bindBases);
  }

  public static Condition gt(Column<?> column, String string) {
    return greaterThan(column.fullyQualifiedName(), string, column.getBindBases());
  }

  public static Condition gt(Column<?> column, Long longValue) {
    return greaterThan(column.fullyQualifiedName(), longValue.toString(), column.getBindBases());
  }

  public static Condition gt(Column<?> column, BigDecimal bigDecimal) {
    return greaterThan(column.fullyQualifiedName(), bigDecimal.toString(), column.getBindBases());
  }

  public static Condition gt(Column<?> column, Column<?> column2) {
    return greaterThan(column.fullyQualifiedName(), column2.fullyQualifiedName(), combine(column.getBindBases(), column2.getBindBases()));
  }

  public static Condition gt(Column<?> column, QueryFragment fragment) {
    return greaterThan(column.fullyQualifiedName(), fragment.getFragmentString(), combine(column.getBindBases(), fragment.getBindBases()));
  }

  public static Condition gte(Column<?> column, BigDecimal bigDecimal) {
    return new ConditionImpl(column.fullyQualifiedName() + " >= " + bigDecimal, column.getBindBases());
  }

  public static Condition gte(Column<?> column, Column<?> column2) {
    return new ConditionImpl(column.fullyQualifiedName() + " >= " + column2.fullyQualifiedName(), combine(column.getBindBases(), column2.getBindBases()));
  }

  public static Condition gte(Column<?> column, String string) {
    return new ConditionImpl(column.fullyQualifiedName() + " >= " + string, column.getBindBases());
  }

  public static Condition gte(Column<?> column, QueryFragment fragment) {
    return new ConditionImpl(column.fullyQualifiedName() + " >= " + fragment.getFragmentString(),
      combine(fragment.getBindBases(), column.getBindBases()));
  }

  public static Condition gte(Column<?> column, Date date) {
    return gte(column, bindDate(date));
  }

  public static Condition optionalGte(Column<?> column, Date date) {
    if (column == null || date == null) {
      return null;
    }
    return gte(column, bindDate(date));
  }

  public static Condition optionalGte(Column<?> column, BigDecimal bigDecimal) {
    if (column == null || bigDecimal == null) {
      return null;
    }
    return gte(column, bindBigDecimal(bigDecimal));
  }

  @SuppressWarnings("unchecked")
  public static Condition optionalGte(Column<?> searchColumn, AbstractValueFieldData<?> valueField) {
    if (valueField.getValue() == null
      || (valueField.getValue() instanceof String && ((String) valueField.getValue()).isEmpty())) {
      return null;
    }
    return gte(searchColumn, bindValueField((Class<? extends AbstractValueFieldData<?>>) valueField.getClass()));
  }

  public static Condition lte(Column<?> column, String string) {
    return new ConditionImpl(column.fullyQualifiedName() + " <= " + string, column.getBindBases());
  }

  public static Condition lte(Column<?> column, BigDecimal bigDecimal) {
    return new ConditionImpl(column.fullyQualifiedName() + " <= " + bigDecimal, column.getBindBases());
  }

  public static Condition lte(Column<?> column, QueryFragment fragment) {
    return new ConditionImpl(column.fullyQualifiedName() + " <= " + fragment.getFragmentString(),
      combine(fragment.getBindBases(), column.getBindBases()));
  }

  public static Condition lt(Column<?> column, QueryFragment fragment) {
    return new ConditionImpl(column.fullyQualifiedName() + " < " + fragment.getFragmentString(),
      combine(fragment.getBindBases(), column.getBindBases()));
  }

  public static Condition optionalLte(Column<?> column, Date date) {
    if (column == null || date == null) {
      return null;
    }
    return lte(column, bindDate(date));
  }

  public static Condition optionalLte(Column<?> column, BigDecimal bigDecimal) {
    if (column == null || bigDecimal == null) {
      return null;
    }
    return lte(column, bindBigDecimal(bigDecimal));
  }

  @SuppressWarnings("unchecked")
  public static Condition optionalLte(Column<?> searchColumn, AbstractValueFieldData<?> valueField) {
    if (valueField.getValue() == null
      || (valueField.getValue() instanceof String && ((String) valueField.getValue()).isEmpty())) {
      return null;
    }
    return lte(searchColumn, bindValueField((Class<? extends AbstractValueFieldData<?>>) valueField.getClass()));
  }

  public static Condition likeIgnoreCase(String string, String string2) {
    return new ConditionImpl("UPPER(" + string + ") LIKE UPPER(" + string2 + ")");
  }

  public static Condition likeIgnoreCase(Column<String> column, String string) {
    return new ConditionImpl("UPPER(" + column.fullyQualifiedName() + ") LIKE UPPER(" + string + ")", column.getBindBases());
  }

  public static Condition likeIgnoreCase(Column<?> column, Column<?> column2) {
    return new ConditionImpl(
      "UPPER(" + column.fullyQualifiedName() + ") LIKE UPPER(" + column2.fullyQualifiedName() + ")", combine(column.getBindBases(), column2.getBindBases()));
  }

  public static Condition startsWith(Column<String> column, String string) {
    return new ConditionImpl("UPPER(" + column.fullyQualifiedName() + ") LIKE UPPER(CONCAT(" + string + ", '%'))", column.getBindBases());
  }

  public static Condition startsWith(Column<?> column, QueryFragment fragment) {
    return new ConditionImpl("UPPER(" + column.fullyQualifiedName() + ") LIKE UPPER(CONCAT("
      + fragment.getFragmentString() + ", '%'))", combine(fragment.getBindBases(), column.getBindBases()));
  }

  public static Condition contains(Column<String> column, String string) {
    return new ConditionImpl("UPPER(" + column.fullyQualifiedName() + ") LIKE UPPER('%' ||" + string + " || '%')", column.getBindBases());
  }

  public static Condition contains(Column<?> column, QueryFragment fragment) {
    return new ConditionImpl("UPPER(" + column.fullyQualifiedName() + ") LIKE UPPER('%' ||"
      + fragment.getFragmentString() + " || '%')", combine(fragment.getBindBases(), column.getBindBases()));
  }

  public static Condition containsNumbers(Column<?> column, String value) {
    return new ConditionImpl("CAST (" + column.fullyQualifiedName() + " AS VARCHAR) LIKE '%' || CAST('" + value + "' AS VARCHAR) || '%'", column.getBindBases());
  }

  public static Condition optionalContains(Column<String> column, String string) {
    if (string == null) {
      return null;
    }
    return contains(column, bindString(string));
  }

  public static Condition optionalContainsNumbers(Column<Long> column, String string) {
    if (string == null) {
      return null;
    }
    return containsNumbers(column, string);
  }

  public static Condition between(Column<?> column, QueryFragment fragment1, QueryFragment fragment2) {
    if (fragment1 == null || fragment2 == null) {
      return null;
    }
    return between(column, fragment1.getFragmentString(), fragment2.getFragmentString());
  }

  public static Condition between(Column<?> column, String value1, String value2) {
    return new ConditionImpl(column.fullyQualifiedName() + " BETWEEN " + value1 + " AND " + value2);
  }

  public static Condition between(QueryFragment fragment1, Column<?> column1, Column<?> column2) {
    if(fragment1 == null) {
      return null;
    }
    return new ConditionImpl(fragment1.getFragmentString() + " BETWEEN " + column1.fullyQualifiedName() + " AND " + column2.fullyQualifiedName(),
      combine(fragment1.getBindBases(), column1.getBindBases(), column2.getBindBases()));
  }

  public static Condition matchesPhoneNumber(Column<?> column, String string) {
    if (string == null) {
      return null;
    }

    string = string.replaceAll(" ", "").replaceAll("\\+", "");
    if (string.startsWith("0")) {
      string = string.substring(1);
    }

    if (string.length() == 0) {
      return null;
    }

    return new ConditionImpl("REPLACE(REPLACE(" + column.fullyQualifiedName() + ", '+', ''), ' ', '') LIKE '%' || "
      + string + " || '%' ", column.getBindBases());
  }

  public static Condition or(List<Condition> conditions) {
    String queryContribution = conditions.stream()
      .filter(Objects::nonNull)
      .map(c -> c.getQueryContribution())
      .collect(Collectors.joining(" OR "));

    List<Object> bindBases = new ArrayList<>();

    conditions.stream()
      .filter(Objects::nonNull)
      .map(c -> c.getBindBases())
      .collect(Collectors.toList())
      .forEach(bindBases::addAll);

    return new ConditionImpl(queryContribution, bindBases);
  }

  public static Condition or(Condition... conditions) {
    return or(Arrays.asList(conditions));
  }

  public static Condition and(List<Condition> conditions) {
    List<Condition> conditionList = conditions.stream().filter(Objects::nonNull)
      .collect(Collectors.toList());
    if (conditionList.isEmpty()) {
      return null;
    }
    String queryContribution = "( ";

    queryContribution += conditionList.stream().map(c -> c.getQueryContribution())
      .collect(Collectors.joining(" AND "));

    queryContribution += " )";

    List<Object> bindBases = new ArrayList<>();
    conditions.stream()
      .filter(Objects::nonNull)
      .map(c -> c.getBindBases())
      .collect(Collectors.toList())
      .forEach(bindBases::addAll);

    return new ConditionImpl(queryContribution, bindBases);
  }

  public static Condition and(Condition... conditions) {
    return and(Arrays.asList(conditions));
  }

  @SuppressWarnings("unchecked")
  public static Condition startsWith(Column<?> searchColumn, AbstractValueFieldData<?> valueField) {
    if (valueField.getValue() == null
      || (valueField.getValue() instanceof String && ((String) valueField.getValue()).isEmpty())) {
      return null;
    }
    return startsWith(searchColumn,
      bindValueField((Class<? extends AbstractValueFieldData<?>>) valueField.getClass()));
  }

  @SuppressWarnings("unchecked")
  public static Condition contains(Column<?> searchColumn, AbstractValueFieldData<?> valueField) {
    if (valueField.getValue() == null
      || (valueField.getValue() instanceof String && ((String) valueField.getValue()).isEmpty())) {
      return null;
    }
    return contains(searchColumn,
      bindValueField((Class<? extends AbstractValueFieldData<?>>) valueField.getClass()));
  }

  @SuppressWarnings("unchecked")
  public static Condition optionalEq(Column<?> searchColumn, AbstractValueFieldData<?> valueField) {
    if (valueField.getValue() == null
      || (valueField.getValue() instanceof String && ((String) valueField.getValue()).isEmpty())) {
      return null;
    }
    return eq(searchColumn, bindValueField((Class<? extends AbstractValueFieldData<?>>) valueField.getClass()));
  }

  public static <T> Condition in(Column<?> column, @SuppressWarnings("unchecked") T... t) {
    List<T> list = Arrays.asList(t);
    return in(column, list);
  }

  public static <T> Condition in(Column<?> column, List<T> list) {
    if (list == null || list.isEmpty()) {
      return new ConditionImpl("FALSE");
    }
    String expandedList = list.stream().map(l -> "" + l).collect(Collectors.joining(", "));
    return new ConditionImpl(column.fullyQualifiedName() + " IN(" + expandedList + ")", column.getBindBases());
  }

  public static Condition in(Column<?> column, Set<Long> list) {
    if (list == null || list.isEmpty()) {
      return new ConditionImpl("FALSE");
    }
    String expandedList = list.stream().map(l -> "" + l).collect(Collectors.joining(", "));
    return new ConditionImpl(column.fullyQualifiedName() + " IN(" + expandedList + ")", column.getBindBases());
  }

  public static <T extends Executable> Condition in(Column<?> column, T subquery) {
    List<Object> bindBases = new ArrayList<>();
    bindBases.addAll(column.getBindBases());
    bindBases.addAll(subquery.getQuery().getBindBases());
    return new ConditionImpl(column.fullyQualifiedName() + " IN(" + subquery.getQueryString() + ")", bindBases);
  }

  public static <T> Condition notIn(Column<?> column, @SuppressWarnings("unchecked") T... t) {
    return notIn(column, Arrays.asList(t));
  }

  public static Condition not(List<Condition> conditions) {
    String queryContribution = " NOT (" +conditions.stream()
      .filter(Objects::nonNull)
      .map(c -> c.getQueryContribution())
      .collect(Collectors.joining(" OR ")) + ") ";

    List<Object> bindBases = new ArrayList<>();

    conditions.stream()
      .filter(Objects::nonNull)
      .map(c -> c.getBindBases())
      .collect(Collectors.toList())
      .forEach(bindBases::addAll);

    return new ConditionImpl(queryContribution, bindBases);
  }

  public static Condition not(Condition condition){
    return not(List.of(condition));
  }

  public static <T> Condition optionalNotIn(Column<?> column, List<T> list) {
    if (list == null) {
      return null;
    }
    else {
      list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
      if (CollectionUtility.isEmpty(list)) {
        return null;
      }
    }
    return notIn(column, list);
  }

  public static <T> Condition optionalIn(Column<?> column, List<T> list) {
    if (list == null) {
      return null;
    }
    else {
      list = list.stream().filter(Objects::nonNull).collect(Collectors.toList());
      if (CollectionUtility.isEmpty(list)) {
        return null;
      }
    }
    return in(column, list);
  }

  public static <T> Condition notIn(Column<?> column, List<T> list) {
    //FIXME: implement with binds
    //NVPair nv = new NVPair(createBindBaseName(), list);

    String expandedList = list.stream().filter(Objects::nonNull).map(l -> "" + l).collect(Collectors.joining(", "));
    return new ConditionImpl(column.fullyQualifiedName() + " NOT IN(" + expandedList + ")", combine(column.getBindBases()));
  }

  public static <T> Condition notIn(Column<?> column, Set<T> list) {
    String expandedList = list.stream().filter(Objects::nonNull).map(l -> "" + l).collect(Collectors.joining(", "));
    return new ConditionImpl(column.fullyQualifiedName() + " NOT IN(" + expandedList + ")", column.getBindBases());
  }

  public static Condition exists(Executable subquery) {
    return new ConditionImpl(" EXISTS (" + subquery.getQueryString() + ")", subquery.getQuery().getBindBases());
  }

  public static Condition notExists(Executable subquery) {
    return new ConditionImpl(" NOT EXISTS (" + subquery.getQueryString() + ")", subquery.getQuery().getBindBases());
  }

  public static Assignment assign(final Column<?> column, final String value) {
    return assign(column, bindString(value));
  }

  public static Assignment assign(final Column<?> column, final QueryFragment value) {
    return new AssignmentImpl(column, value);
  }

  public static Assignment assign(final Column<?> column, final Long value) {
    return assign(column, bindLong(value));
  }

  public static Assignment assign(final Column<?> column, final Number value) {
    return assign(column, bindNumber(value));
  }

  public static Assignment assign(final Column<?> column, final Boolean value) {
    return assign(column, bindBoolean(value));
  }

  public static Assignment assign(final Column<?> column, Class<? extends AbstractValueFieldData<?>> valueField) {
    return assign(column, bindValueField(valueField));
  }

  public static Assignment assign(final Column<?> column, final Date date) {
    return assign(column, bindDate(date));
  }

  public static Assignment assignBoolean(final Column<?> column, Class<? extends AbstractValueFieldData<?>> valueField) {
    return assign(column, bindBooleanValueField(valueField));
  }

  public static SortExpression asc(final Column<?> column) {
    return sortExpression(column, SortOrder.ASC);
  }

  public static SortExpression desc(final Column<?> column) {
    return sortExpression(column, SortOrder.DESC);
  }

  public static SortExpression sortExpression(final Column<?> column, final SortOrder order) {
    return new SortExpression(column, order);
  }

  public static SortExpression2 sortExpression(final String string, final SortOrder order) {
    return new SortExpression2(string, order);
  }

  public static Executable union(Executable statement1, Executable statement2) {
    Query query = new Query();
    query.append(statement1.getQueryString());
    query.addBindBases(statement1.getQuery().getBindBases());
    query.append(" UNION ");
    query.append(statement2.getQueryString());
    query.addBindBases(statement2.getQuery().getBindBases());
    return new ExecutableImpl(query);
  }

  public interface QuerySpecification {
    FromClause from(Table... tables);

    FromClause withoutFrom();
  }

  public interface FromClause extends Executable {
    WhereClause where(Condition... conditions);

    WhereClause where(Collection<Condition> conditions);

    WhereClause where(Collection<Condition> conditionList, Condition... conditions);

    JoinCondition leftJoin(Table table);

    JoinCondition leftOuterJoin(Table table);

    JoinCondition fullOuterJoin(Table table);

    JoinCondition rightJoin(Table table);

    JoinCondition innerJoin(Table table);
  }

  public interface UpdateClause extends Executable {
    FromClause set(Assignment... assignments);

    FromClause set(List<Assignment> assignments);
    QuerySpecification setWithFrom(Assignment... assignments);
  }

  public interface InsertClause {
    InsertValuesClause columns(Column<?>... columns);

    InsertValuesClause columns(List<Column<?>> columns);
  }

  public interface InsertValuesClause {
    FromClause values(QueryFragment... values);

    FromClause values(List<QueryFragment> values);

    FromClause select(Executable exec);

    FromClause select(String queryString);

    FromClause select(QueryFragment... values);

    FromClause select(List<QueryFragment> values);
  }

  public interface Assignment {

    List<Object> getBindBases();

    String getQueryContribution();
  }

  public interface JoinCondition {
    FromClause on(Condition... conditions);

    FromClause on(Collection<Condition> conditions);
  }

  public interface WhereClause extends Executable {

    Executable into(List<QueryFragment> binds);

    Executable into(QueryFragment... binds);

    @SuppressWarnings("unchecked")
    Executable into(Class<? extends IHolder<?>>... valueFields);
  }

  public interface GroupByClause extends Executable {
    Executable having(Condition...conditions);

    Executable having(Collection<Condition> collections);
  }

  public interface Condition {

    List<Object> getBindBases();

    String getQueryContribution();
  }

  public enum SortOrder {
    ASC("ASC"),
    DESC("DESC");

    private final String m_queryString;

    SortOrder(String queryString) {
      m_queryString = queryString;
    }

    public String getQueryString() {
      return m_queryString;
    }
  }

  public static class SortExpression {
    private final Column<?> m_column;
    private final SortOrder m_sortOrder;

    public SortExpression(final Column<?> column, final SortOrder order) {
      m_column = column;
      m_sortOrder = order;
    }

    public List<Object> getBindBases() {
      return m_column.getBindBases();
    }

    public String getQueryString() {
      return m_column.fullyQualifiedName() + " " + m_sortOrder.getQueryString();
    }
  }

  public static class SortExpression2 {
    private final String m_string;
    private final SortOrder m_sortOrder;

    public SortExpression2(final String string, final SortOrder order) {
      m_string = string;
      m_sortOrder = order;
    }

    public String getQueryString() {
      return m_string + " " + m_sortOrder.getQueryString();
    }
  }

  public interface Executable {

    Query getQuery();

    List<Object> getBindBases();

    default Executable orderBy(Column<?> column) {
      getQuery().append("ORDER BY " + column.fullyQualifiedName());
      getQuery().addBindBases(column.getBindBases());
      return new ExecutableImpl(getQuery());
    }

    default Executable orderBy(SortExpression... sortExpressions) {
      if (sortExpressions == null || sortExpressions.length == 0) {
        return new ExecutableImpl(getQuery());
      }
      List<SortExpression> expressions = Arrays.asList(sortExpressions);

      getQuery().append("ORDER BY " + expressions.stream().map(e -> e.getQueryString()).collect(Collectors.joining(", ")));

      List<Object> bindBases = new ArrayList<>();
      expressions.stream()
        .map(c -> c.getBindBases())
        .collect(Collectors.toList())
        .forEach(bindBases::addAll);
      getQuery().addBindBases(bindBases);

      return new ExecutableImpl(getQuery());
    }

    default Executable orderBy(Column<?>... columns) {
      int iteration = 0;
      for (Column<?> selectedColumn : columns) {
        if (iteration == 0) {
          getQuery().append("ORDER BY " + selectedColumn.fullyQualifiedName());
          getQuery().addBindBases(selectedColumn.getBindBases());
        }
        else {
          getQuery().append("," + selectedColumn.fullyQualifiedName());
          getQuery().addBindBases(selectedColumn.getBindBases());
        }
        iteration++;
      }
      return new ExecutableImpl(getQuery());
    }

    default Executable orderByDesc(Column<?>... columns) {
      int iteration = 0;
      for (Column<?> selectedColumn : columns) {
        if (iteration == 0) {
          getQuery().append("ORDER BY " + selectedColumn.fullyQualifiedName() + " DESC");
          getQuery().addBindBases(selectedColumn.getBindBases());
        }
        else {
          getQuery().append("," + selectedColumn.fullyQualifiedName() + " DESC");
          getQuery().addBindBases(selectedColumn.getBindBases());
        }
        iteration++;
      }
      return new ExecutableImpl(getQuery());
    }

    default Executable orderByDesc(Column<?> column) {
      getQuery().append("ORDER BY " + column.fullyQualifiedName() + " DESC");
      getQuery().addBindBases(column.getBindBases());
      return new ExecutableImpl(getQuery());
    }

    default Executable limit(int limitValue) {
      getQuery().append("LIMIT " + limitValue);
      return new ExecutableImpl(getQuery());
    }

    default GroupByClause groupBy(Column<?> column) {
      getQuery().append("GROUP BY " + column.fullyQualifiedName());
      getQuery().addBindBases(column.getBindBases());
      return new GroupByClauseImpl(getQuery());
    }

    default GroupByClause groupBy(Column<?>... column) {
      int iteration = 0;
      for (Column<?> selectedColumn : column) {
        if (iteration == 0) {
          getQuery().append("GROUP BY " + selectedColumn.fullyQualifiedName());
        }
        else {
          getQuery().append(", " + selectedColumn.fullyQualifiedName());
          getQuery().addBindBases(selectedColumn.getBindBases());
        }
        iteration++;
      }
      return new GroupByClauseImpl(getQuery());
    }

    default String getQueryString() {
      return getQuery().getQueryString();
    }

    default Object[][] executeSelectUnlimited(Object... bindBases) throws ProcessingException {
      assertRunningInServerRunContext();
      return SQL.select(getQueryString(), getQuery().combineWithExternalBindBases(bindBases));
    }

    /**
     * Wenn bei einem Service die @TunnelToServer Annotation fehlt, funktioniert der Servicaufruf trotzdem, weil die
     * Anwendung mit der Scout serverbridge läuft. Allerdings läuft der Aufruf im ClientContext statt im ServerContext. Dies
     * führt dazu, dass die abgesetzten Queries auf der DB im state = 'idle in transaction' bleiben und die Anwendung dann
     * abstürzt. Darum wird hier ein assertRunningInServerContext gemacht.
     */
    default void assertRunningInServerRunContext() {
      Assertions.assertTrue(ServerRunContext.CURRENT.get() instanceof ServerRunContext, "Current service is running in ClientContext instead of ServerContext. Check if this Service is missing @TunnelToServer Annotation");
    }

    default Object[][] executeSelect(Object... bindBases) throws ProcessingException {
      assertRunningInServerRunContext();
      Number limit = PARAMETERS.get(MaxRowsParameter.class).getValue();
      if (limit != null && limit.intValue() > 0) {
        String limitString = " LIMIT " + (limit.intValue() + 1);
        return SQL.select(getQueryString() + limitString, getQuery().combineWithExternalBindBases(bindBases));
      }
      return SQL.select(getQueryString(), getQuery().combineWithExternalBindBases(bindBases));
    }

    default int executeUpdate(Object... bindBases) throws ProcessingException {
      assertRunningInServerRunContext();
      return SQL.update(getQueryString(), getQuery().combineWithExternalBindBases(bindBases));
    }

    default int executeDelete(Object... bindBases) throws ProcessingException {
      assertRunningInServerRunContext();
      return SQL.delete(getQueryString(), getQuery().combineWithExternalBindBases(bindBases));
    }

    default int executeInsert(Object... bindBases) throws ProcessingException {
      assertRunningInServerRunContext();
      return SQL.insert(getQueryString(), getQuery().combineWithExternalBindBases(bindBases));
    }

    @SuppressWarnings("unchecked")
    default <T> List<T> list(Class<T> type, Object... bindBases) throws ProcessingException {
      Object[][] result = SQL.select(getQueryString(), getQuery().combineWithExternalBindBases(bindBases));
      List<T> list = new ArrayList<T>();
      for (Object[] row : result) {
        if (row != null && row.length == 1) {
          list.add((T) row[0]);
        }
      }
      return list;
    }

    @SuppressWarnings("unchecked")
    default <K, V> Map<K, V> map(Class<K> keyType, Object... bindBases) {
      Object[][] result = SQL.select(getQueryString(), getQuery().combineWithExternalBindBases(bindBases));
      Map<K, V> map = new HashMap<K, V>();

      for (Object[] row : result) {
        if (row != null && row.length == 2) {
          map.put((K) row[0], (V) row[1]);
        }
      }

      return map;
    }

    @SuppressWarnings("unchecked")
    default <T> T singleResult(Class<T> type, Object... bindBases) throws ProcessingException {
      Object[][] result = SQL.select(getQueryString(), getQuery().combineWithExternalBindBases(bindBases));

      if (result != null && result.length == 1 && result[0] != null && result[0].length == 1) {
        return (T) result[0][0];
      }
      if (result != null && result.length == 0) {
        return null;
      }

      throw new ProcessingException("Result set must have exactly one row and one column");
    }

    @SuppressWarnings("unchecked")
    default <T> T singleResultTrunc(Class<T> type, Object... bindBases) throws ProcessingException {
      Object[][] result = SQL.select(getQueryString(), getQuery().combineWithExternalBindBases(bindBases));

      if (result != null && result.length > 0 && result[0] != null && result[0].length > 0) {
        return (T) result[0][0];
      }
      return null;
    }

    default Object[][] executeSelectLimited(int maxRowCount, Object... bindBases) throws ProcessingException {
      assertRunningInServerRunContext();
      return SQL.selectLimited(getQueryString(), maxRowCount, getQuery().combineWithExternalBindBases(bindBases));
    }
  }

}
