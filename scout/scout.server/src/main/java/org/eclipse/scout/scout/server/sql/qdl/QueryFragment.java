package org.eclipse.scout.scout.server.sql.qdl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryFragment {

  private final String m_queryFragment;
  private final List<Object> m_bindBases;

  public QueryFragment(String queryFragment, Object... bindBases) {
    m_queryFragment = queryFragment;
    m_bindBases = Arrays.asList(bindBases);
  }

  public QueryFragment(String queryFragment, List<Object> bindBases) {
    m_queryFragment = queryFragment;
    m_bindBases = bindBases;
  }

  public QueryFragment(String queryFragment) {
    m_queryFragment = queryFragment;
    m_bindBases = new ArrayList<Object>();
  }

  public List<Object> getBindBases() {
    return m_bindBases;
  }

  public String getFragmentString() {
    return m_queryFragment;
  }

  @Override
  public String toString() {
    return m_queryFragment;
  }

}
