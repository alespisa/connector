package org.eclipse.scout.scout.shared.entities.common;

import java.util.ArrayList;
import java.util.List;

public class ColumnImpl implements Column<Object>{

  private final String m_name;
  private final Table m_table;
  private final List<Object> m_bindBases;

  public ColumnImpl(String name, Table table) {
    m_name = name;
    m_table = table;
    m_bindBases = new ArrayList<Object>();
  }

  @Override
  public String name() {
    return m_name;
  }

  @Override
  public Table table() {
    return m_table;
  }

  @Override
  public String fullyQualifiedName() {
    return table().alias() + "." + name().toLowerCase();
  }

  @Override
  public String toString() {
    return fullyQualifiedName();
  }

  @Override
  public List<Object> getBindBases() {
    return m_bindBases;
  }

  @Override
  public Object value() {
    throw new UnsupportedOperationException("cannot read the value of a column definition");
  }

}
