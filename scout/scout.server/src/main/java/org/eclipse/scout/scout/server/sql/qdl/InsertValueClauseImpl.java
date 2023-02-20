package org.eclipse.scout.scout.server.sql.qdl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InsertValueClauseImpl implements QDL.InsertValuesClause{

  private final Query m_query;

  public InsertValueClauseImpl(final Query query) {
    m_query = query;
  }

  @Override
  public QDL.FromClause values(QueryFragment... values) {
    return values(Arrays.asList(values));
  }

  @Override
  public QDL.FromClause values(List<QueryFragment> values) {
    values = values.stream().map(v -> v == null ? new NullQueryFragment() : v).collect(Collectors.toList());

    if (values.size() > 0) {
      m_query.append("VALUES(");
      String queryContribution = values.stream().map(v -> v == null ? "null" : v.getFragmentString()).collect(Collectors.joining(", "));

      m_query.addBindBases(values.stream().map(v -> v.getBindBases()).collect(Collectors.toList()));
      m_query.append(queryContribution + ") ");
    }
    return new FormClauseImpl(m_query);
  }

  @Override
  public QDL.FromClause select(QDL.Executable exec) {
    return select(exec.getQueryString());
  }

  @Override
  public QDL.FromClause select(String queryString) {
    m_query.append(queryString);
    return new FormClauseImpl(m_query);
  }

  @Override
  public QDL.FromClause select(QueryFragment... values) {
    return select(Arrays.asList(values));
  }

  @Override
  public QDL.FromClause select(List<QueryFragment> values) {
    values = values.stream().map(v -> v == null ? new NullQueryFragment() : v).collect(Collectors.toList());

    if (values.size() > 0) {
      m_query.append("SELECT");
      String queryContribution = values.stream().map(v -> v == null ? "null" : v.getFragmentString()).collect(Collectors.joining(", "));

      m_query.addBindBases(values.stream().map(v -> v.getBindBases()).collect(Collectors.toList()));
      m_query.append(queryContribution);
    }
    return new FormClauseImpl(m_query);
  }


}
