package org.eclipse.scout.scout.server.sql.qdl;

import org.eclipse.scout.rt.platform.util.CollectionUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Query {

  private final StringBuilder m_query = new StringBuilder();
  private final List<Object> m_bindBases = new ArrayList<Object>();

  public String getQueryString() {
    return m_query.toString();
  }

  /**
   * Appends a string followed by a space to the query.
   */
  public void append(String string) {
    m_query.append(string);
    m_query.append(" ");
  }

  public void braket() {
    m_query.insert(0, "(").append(")");
  }

  public void addBindBases(Object bindBase) {
    if (bindBase instanceof Collection) {
      addBindBases((Collection<?>) bindBase);
    }
    else {
      m_bindBases.add(bindBase);
    }
  }

  public void addBindBases(Collection<?> bindBases) {
    for (Object bindBase : bindBases) {
      if (bindBase instanceof Collection) {
        m_bindBases.addAll((Collection<?>) bindBase);
      }
      else {
        m_bindBases.add(bindBase);
      }
    }
  }

  public List<Object> getBindBases() {
    return m_bindBases;
  }

  public Object[] combineWithExternalBindBases(Object... externalBindBases) {
    List<? extends Object> bindBasesList = CollectionUtility.flatten(Arrays.asList(externalBindBases), getBindBases());
    return bindBasesList.toArray(new Object[bindBasesList.size()]);
  }

}
