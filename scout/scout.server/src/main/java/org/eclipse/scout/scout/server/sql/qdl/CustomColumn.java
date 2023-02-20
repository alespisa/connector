package org.eclipse.scout.scout.server.sql.qdl;

import org.eclipse.scout.scout.shared.entities.common.Column;
import org.eclipse.scout.scout.shared.entities.common.Table;

import java.util.List;

public class CustomColumn implements Column<Object>{

  private final String m_ColumnQuery;
  private final List<Object> m_bindBases;

  public CustomColumn(String columnQuery) {
    this(new QueryFragment(columnQuery));
  }

  public CustomColumn(QueryFragment queryFragment) {
    this.m_ColumnQuery = queryFragment.getFragmentString();
    this.m_bindBases = queryFragment.getBindBases();
  }

  public CustomColumn(Column<?> column) {
    this(new QueryFragment(column.fullyQualifiedName()));
  }

  public CustomColumn(QDL.Executable executable) {
    this.m_ColumnQuery = executable.getQueryString();
    this.m_bindBases = executable.getQuery().getBindBases();
  }

  @Override
  public String name() {
    return m_ColumnQuery;
  }

  @Override
  public String fullyQualifiedName() {
    return m_ColumnQuery;
  }

  @Override
  public List<Object> getBindBases() {
    return m_bindBases;
  }

  @Override
  public Object value() {
    throw new UnsupportedOperationException("cannot read the value of a cusom column");
  }

  @Override
  public Table table() {

    return new Table() {
      private static final long serialVersionUID = 1L;

      @Override
      public String name() {
        return "";
      }

      @Override
      public String alias() {
        return "";
      }

      @Override
      public <T> T getValueByColumnMethodName(String columnMethodName) {
        throw new UnsupportedOperationException("cannot read the value of a cusom column");
      }

      @Override
      public <T extends Table> Class<T> getTableClass() {
        return null;
      }
    };
  }

}
