package org.eclipse.scout.scout.server.sql.qdl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UpdateClauseImpl implements QDL.UpdateClause{

  private Query m_query;
  private final List<Object> m_bindBases;

  public UpdateClauseImpl(Query query, Object... bindBases) {
    m_query = query;
    this.m_bindBases = Arrays.asList(bindBases);
  }

  @Override
  public Query getQuery() {
    return m_query;
  }

  @Override
  public QDL.FromClause set(QDL.Assignment... assignments) {
    return set(Arrays.asList(assignments));
  }

  @Override
  public QDL.FromClause set(List<QDL.Assignment> assignments) {
    createSetStatement(assignments);
    return new FormClauseImpl(m_query);
  }

  @Override
  public QDL.QuerySpecification setWithFrom(QDL.Assignment... assignments) {
    createSetStatement(Arrays.asList(assignments));
    return new QuerySpecificationImpl(m_query);
  }

  @Override
  public List<Object> getBindBases() {
    return m_bindBases;
  }

  public void createSetStatement(List<QDL.Assignment> assignments){
    m_query.append("SET");
    assignments = assignments.stream().filter(Objects::nonNull).collect(Collectors.toList());

    String queryContribution = assignments.stream().
      map(c -> c.getQueryContribution()).
      collect(Collectors.joining(", "));

    m_query.addBindBases(assignments.stream().map(c -> c.getBindBases()).collect(Collectors.toList()));
    m_query.append(queryContribution);
  }

}
