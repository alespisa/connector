package org.eclipse.scout.scout.server.sql.qdl;

import org.eclipse.scout.scout.shared.entities.common.Table;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FormClauseImpl implements QDL.FromClause{

  private final Query m_query;

  public FormClauseImpl(final Query query){
    m_query = query;
  }

  @Override
  public Query getQuery() {
    return m_query;
  }

  @Override
  public QDL.WhereClause where(QDL.Condition... conditions) {
    return where(Arrays.asList(conditions));
  }

  @Override
  public QDL.WhereClause where(Collection<QDL.Condition> conditionList, QDL.Condition... conditions) {
    conditionList.addAll(Arrays.asList(conditions));
    return where(conditionList);
  }

  @Override
  public QDL.WhereClause where(Collection<QDL.Condition> conditions) {
    conditions = conditions.stream().filter(Objects::nonNull).collect(Collectors.toList());

    if (conditions.size() > 0) {
      m_query.append("WHERE");

      String queryContribution = conditions.stream().map(c -> c.getQueryContribution()).collect(Collectors.joining(" AND "));

      m_query.addBindBases(conditions.stream().map(c -> c.getBindBases()).collect(Collectors.toList()));
      m_query.append(queryContribution);
    }
    return new WhereClauseImpl(m_query);
  }

  @Override
  public QDL.JoinCondition leftJoin(Table table) {
    m_query.append("LEFT JOIN");
    m_query.append(table.name().toLowerCase() + " " + table.alias());
    return new JoinConditionImpl(m_query);
  }

  @Override
  public QDL.JoinCondition leftOuterJoin(Table table) {
    m_query.append("LEFT OUTER JOIN");
    m_query.append(table.name().toLowerCase() + " " + table.alias());
    return new JoinConditionImpl(m_query);
  }

  @Override
  public QDL.JoinCondition fullOuterJoin(Table table) {
    m_query.append("FULL OUTER JOIN");
    m_query.append(table.name().toLowerCase() + " " + table.alias());
    return new JoinConditionImpl(m_query);
  }

  @Override
  public QDL.JoinCondition rightJoin(Table table) {
    m_query.append("RIGHT JOIN");
    m_query.append(table.name().toLowerCase() + " " + table.alias());
    return new JoinConditionImpl(m_query);
  }

  @Override
  public QDL.JoinCondition innerJoin(Table table) {
    m_query.append("INNER JOIN");
    m_query.append(table.name().toLowerCase() + " " + table.alias());
    return new JoinConditionImpl(m_query);
  }

  @Override
  public List<Object> getBindBases() {
    // TODO Auto-generated method stub
    return null;
  }

}
