package org.eclipse.scout.scout.server.sql.qdl;

import java.util.Arrays;
import java.util.List;

public class ExecutableImpl implements QDL.Executable{

  private final Query m_query;
  private final List<Object> m_bindBases;

  public ExecutableImpl(final Query query, Object... bindBases) {
    this.m_query = query;
    this.m_bindBases = Arrays.asList(bindBases);
  }

  @Override
  public Query getQuery() {
    return m_query;
  }

  @Override
  public List<Object> getBindBases() {
    return m_bindBases;
  }

}
