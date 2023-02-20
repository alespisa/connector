package org.eclipse.scout.scout.server.sql.qdl;

import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.scout.shared.entities.common.Column;

import java.util.ArrayList;
import java.util.List;

public class AssignmentImpl implements QDL.Assignment{

  private final Column<?> m_column;
  private final String m_value;
  private List<Object> m_bindBases;
  private final QueryFragment m_fragment;

  public AssignmentImpl(final Column<?> column, final String value) {
    m_column = column;
    m_value = value;
    m_bindBases = new ArrayList<Object>();
    m_fragment = null;
  }

  public AssignmentImpl(final Column<?> column, final QueryFragment fragment) {
    m_column = column;
    m_fragment = fragment;
    m_value = null;
    m_bindBases = fragment != null ? fragment.getBindBases() : new ArrayList<Object>();
    if(column != null) {
      m_bindBases = CollectionUtility.combine(m_bindBases, column.getBindBases());
    }
  }

  @Override
  public List<Object> getBindBases() {
    return m_bindBases;
  }

  @Override
  public String getQueryContribution() {
    return m_column.name() + " = " + (m_fragment == null ? m_value : m_fragment.getFragmentString());
  }

}
