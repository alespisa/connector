package org.eclipse.scout.scout.server.sql.qdl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConditionImpl implements QDL.Condition{

  private final String m_queryContribution;
  private final List<Object> m_bindBases;

  public ConditionImpl(String queryContribution) {
    m_queryContribution = queryContribution;
    m_bindBases = new ArrayList<Object>();
  }

  public ConditionImpl(String queryContribution, Object... bindBases) {
    m_queryContribution = queryContribution;
    m_bindBases = Arrays.asList(bindBases);
  }

  public ConditionImpl(String queryContribution, List<Object> bindBases) {
    m_queryContribution = queryContribution;
    m_bindBases = bindBases;
  }

  @Override
  public String getQueryContribution() {
    return m_queryContribution;
  }

  @Override
  public List<Object> getBindBases() {
    return m_bindBases;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;

    result = prime * result + ((m_bindBases == null) ? 0 : clearBindBaseNr(m_bindBases).hashCode());
    result = prime * result + ((m_queryContribution == null) ? 0 : clearBindBaseNr(m_queryContribution).hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    ConditionImpl other = (ConditionImpl) obj;
    if (m_bindBases == null) {
      if (other.m_bindBases != null) return false;
    }
    else if (!clearBindBaseNr(m_bindBases).equals(clearBindBaseNr(other.m_bindBases))) return false;
    if (m_queryContribution == null) {
      if (other.m_queryContribution != null) return false;
    }
    else if (!clearBindBaseNr(m_queryContribution).equals(clearBindBaseNr(other.m_queryContribution))) return false;
    return true;
  }

  /**
   * This method removes the arbitrarily created bind base numbers (generated in QDL.class) in order to be able to compare
   * just the condition. This functionality allows caching for equal conditions (see Ticket #237215)
   *
   * @param o
   * @return
   */
  public String clearBindBaseNr(Object o) {
    if (o == null) {
      return null;
    }
    return o.toString().replaceAll("(__[0-9]+)", "");
  }

}
