package org.eclipse.scout.scout.server.sql.qdl;

import org.eclipse.scout.scout.shared.entities.common.Column;

public class ColumnMappingImpl implements ColumnMapping{

  private final Column<?> m_column;
  private final String m_tableRowId;

  public ColumnMappingImpl(Column<?> column, String tableRowId) {
    m_column = column;
    m_tableRowId = tableRowId;
  }

  @Override
  public Column<?> column() {
    return m_column;
  }

  @Override
  public String tableRowId() {
    return m_tableRowId;
  }

  @Override
  public String fullyQualifiedTableRowId() {
    return ":{page." + m_tableRowId + "}";
  }

}
