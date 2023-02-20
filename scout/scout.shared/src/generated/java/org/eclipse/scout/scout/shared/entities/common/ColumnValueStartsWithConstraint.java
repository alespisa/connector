package org.eclipse.scout.scout.shared.entities.common;

import java.util.function.Function;

public class ColumnValueStartsWithConstraint<T extends EntityTable> implements IEntityQueryConstraint<T> {
  private final Function<? super T, ? extends Column<String>> m_columnMapper;
  private final String m_value;

  private ColumnValueStartsWithConstraint(final Function<? super T, ? extends Column<String>> columnMapper, final String value) {
    m_columnMapper = columnMapper;
    m_value = value;
  }

  public static <T extends EntityTable> ColumnValueStartsWithConstraint<T> columnValueStartsWith(final Function<? super T, ? extends Column<String>> columnMapper, final String value) {
    return new ColumnValueStartsWithConstraint<T>(columnMapper, value);
  }

  public Function<? super T, ? extends Column<String>> getColumnMapper() {
    return m_columnMapper;
  }

  public String getValue() {
    return m_value;
  }
}
