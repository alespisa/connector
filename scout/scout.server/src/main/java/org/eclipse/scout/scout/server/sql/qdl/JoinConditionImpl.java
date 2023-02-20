package org.eclipse.scout.scout.server.sql.qdl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class JoinConditionImpl implements QDL.JoinCondition{

  private final Query m_query;

  public JoinConditionImpl(final Query query) {
    m_query = query;
  }

  @Override
  public QDL.FromClause on(QDL.Condition... conditions) {
    return on(Arrays.asList(conditions));
  }

  @Override
  public QDL.FromClause on(Collection<QDL.Condition> conditions) {
    conditions = conditions.stream().filter(Objects::nonNull).collect(Collectors.toList());

    if (conditions.size() > 0) {
      m_query.append("ON");

      String queryContribution = conditions.stream().
        map(c -> c.getQueryContribution()).
        collect(Collectors.joining(" AND "));

      m_query.addBindBases(conditions.stream().map(c -> c.getBindBases()).collect(Collectors.toList()));
      m_query.append(queryContribution);
    }
    return new FormClauseImpl(m_query);
  }

}
