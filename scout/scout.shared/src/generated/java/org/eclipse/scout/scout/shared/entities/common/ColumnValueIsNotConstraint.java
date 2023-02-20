package org.eclipse.scout.scout.shared.entities.common;

import java.util.function.Function;

public class ColumnValueIsNotConstraint<V, T extends Table> implements IEntityQueryConstraint<T>{

  private final Function<? super T, ? extends Column<V>> m_columnMapper;
  private final V m_value;

  private ColumnValueIsNotConstraint(final Function<? super T, ? extends Column<V>> columnMapper, final V value) {
    m_columnMapper = columnMapper;
    m_value = value;
  }

  public static <V, T extends Table> ColumnValueIsNotConstraint<V, T> columnValueIsNot(final Function<? super T, ? extends Column<V>> columnMapper, final V value) {
    return new ColumnValueIsNotConstraint<V, T>(columnMapper, value);
  }

  public Function<? super T, ? extends Column<V>> getColumnMapper() {
    return m_columnMapper;
  }

  public V getValue() {
    return m_value;
  }

}
