package org.eclipse.scout.scout.server.sql.qdl;

import org.eclipse.scout.scout.shared.entities.common.Column;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InsertClauseImpl implements QDL.InsertClause{

  private final Query m_query;

  public InsertClauseImpl(Query query) {
    m_query = query;
  }

  @Override
  public QDL.InsertValuesClause columns(Column<?>... columns) {
    return columns(Arrays.asList(columns));
  }

  @Override
  public QDL.InsertValuesClause columns(List<Column<?>> columns) {
    columns = columns.stream().filter(Objects::nonNull).collect(Collectors.toList());

    if (columns.size() > 0) {
      m_query.append("(");
      String queryContribution = columns.stream().
        map(c -> c.name()).
        collect(Collectors.joining(", "));
      m_query.append(queryContribution + ") ");
    }
    return new InsertValueClauseImpl(m_query);
  }

}
