package org.eclipse.scout.scout.server.sql.qdl;

import org.eclipse.scout.scout.shared.entities.common.Table;

import java.util.Arrays;
import java.util.stream.Collectors;

public class QuerySpecificationImpl implements QDL.QuerySpecification {

  private final Query m_query;

  public QuerySpecificationImpl(final Query query) {
    m_query = query;
  }

  @Override
  public QDL.FromClause from(Table... tables) {
    m_query.append("FROM");

    String queryContribution = Arrays.asList(tables).stream()
      .map(t -> t.name().toLowerCase() + " " + t.alias())
      .collect(Collectors.joining(", "));

    m_query.append(queryContribution);
    return new FormClauseImpl(m_query);
  }

  @Override
  public QDL.FromClause withoutFrom() {
    return new FormClauseImpl(m_query);
  }

}
