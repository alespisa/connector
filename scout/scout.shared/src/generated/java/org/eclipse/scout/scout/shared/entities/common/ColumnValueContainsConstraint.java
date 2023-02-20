package org.eclipse.scout.scout.shared.entities.common;

import java.util.function.Function;

public class ColumnValueContainsConstraint<T extends EntityTable> implements IEntityQueryConstraint<T> {
  private final Function<? super T, ? extends Column<String>> m_columnMapper;
  private final String m_value;

  private ColumnValueContainsConstraint(final Function<? super T, ? extends Column<String>> columnMapper, final String value) {
    m_columnMapper = columnMapper;
    m_value = value;
  }

  public static <T extends EntityTable> ColumnValueContainsConstraint<T> columnValueContains(final Function<? super T, ? extends Column<String>> columnMapper, final String value) {
    return new ColumnValueContainsConstraint<T>(columnMapper, value);
  }

  public Function<? super T, ? extends Column<String>> getColumnMapper() {
    return m_columnMapper;
  }

  public String getValue() {
    return m_value;
  }
}
