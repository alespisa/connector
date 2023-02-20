package org.eclipse.scout.scout.server.sql.qdl;

import org.eclipse.scout.rt.platform.holders.IHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.eclipse.scout.scout.server.sql.qdl.QDL.bindHolder;

public class WhereClauseImpl implements QDL.WhereClause{

  private final Query m_query;
  private final List<Object> m_bindBases;


  public WhereClauseImpl(final Query query, Object... bindBases) {
    this.m_query = query;
    this.m_bindBases = Arrays.asList(bindBases);
  }

  @Override
  public Query getQuery() {
    return m_query;
  }

  @Override
  public QDL.Executable into(List<QueryFragment> binds) {
    binds = binds.stream().filter(Objects::nonNull).collect(Collectors.toList());

    if (binds.size() > 0) {
      m_query.append("INTO");
      String queryContribution = binds.stream().map(b -> b.getFragmentString()).collect(Collectors.joining(", "));
      m_query.append(queryContribution);
      m_query.addBindBases(binds.stream().map(b -> b.getBindBases()).collect(Collectors.toList()));
    }
    return new ExecutableImpl(m_query);
  }

  @Override
  public QDL.Executable into(QueryFragment... binds) {
    return into(Arrays.asList(binds));
  }

  @Override
  public QDL.Executable into(@SuppressWarnings("unchecked") Class<? extends IHolder<?>>... valueFields) {
    List<QueryFragment> binds = Arrays.asList(valueFields).stream().filter(Objects::nonNull).map(v -> bindHolder(v)).collect(Collectors.toList());
    return into(binds);
  }

  @Override
  public List<Object> getBindBases() {
    return m_bindBases;
  }

}
