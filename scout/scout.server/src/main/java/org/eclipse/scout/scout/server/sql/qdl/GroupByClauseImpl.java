package org.eclipse.scout.scout.server.sql.qdl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GroupByClauseImpl implements QDL.GroupByClause{

  private final Query m_query;

  public GroupByClauseImpl(final Query query) {
    m_query = query;
  }

  @Override
  public Query getQuery() {
    return m_query;
  }

  @Override
  public QDL.Executable having(QDL.Condition... conditions) {
    return having(Arrays.asList(conditions));
  }

  @Override
  public QDL.Executable having(Collection<QDL.Condition> conditions) {
    conditions = conditions.stream().filter(Objects::nonNull).collect(Collectors.toList());

    if(conditions.size() > 0) {
      m_query.append("HAVING");

      String queryContribution = conditions.stream().map(c -> c.getQueryContribution()).collect(Collectors.joining(" AND "));

      m_query.addBindBases(conditions.stream().map(c -> c.getBindBases()).collect(Collectors.toList()));
      m_query.append(queryContribution);
    }
    return new ExecutableImpl(m_query);
  }

  @Override
  public List<Object> getBindBases() {
    // TODO Auto-generated method stub
    return null;
  }

}
