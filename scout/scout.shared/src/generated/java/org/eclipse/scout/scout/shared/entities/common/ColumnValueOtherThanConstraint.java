package org.eclipse.scout.scout.shared.entities.common;

import java.util.function.Function;

public class ColumnValueOtherThanConstraint<V, T extends EntityTable> implements IEntityQueryConstraint<T> {
  private final Function<? super T, ? extends Column<V>> m_columnMapper;
  private final V m_value;

  private ColumnValueOtherThanConstraint(final Function<? super T, ? extends Column<V>> columnMapper, final V value) {
    m_columnMapper = columnMapper;
    m_value = value;
  }

  public static <V, T extends EntityTable> ColumnValueOtherThanConstraint<V, T> columnValueOtherThan(final Function<? super T, ? extends Column<V>> columnMapper, final V value) {
    return new ColumnValueOtherThanConstraint<V, T>(columnMapper, value);
  }

  public Function<? super T, ? extends Column<V>> getColumnMapper() {
    return m_columnMapper;
  }

  public V getValue() {
    return m_value;
  }
}
