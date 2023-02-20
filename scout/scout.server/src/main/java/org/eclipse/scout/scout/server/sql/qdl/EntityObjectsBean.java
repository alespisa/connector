package org.eclipse.scout.scout.server.sql.qdl;

public class EntityObjectsBean {

  Object[][] m_primaryKeyValues;
  Object[][] m_data;

  public EntityObjectsBean(Object[][] primaryKeyValues, Object[][] data) {
    m_primaryKeyValues = primaryKeyValues;
    m_data = data;
  }

  public Object[][] getPrimaryKeyValues() {
    return m_primaryKeyValues;
  }

  public Object[][] getData() {
    return m_data;
  }

}
