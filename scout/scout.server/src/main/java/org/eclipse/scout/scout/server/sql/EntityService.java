package org.eclipse.scout.scout.server.sql;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.scout.server.sql.qdl.Entities;
import org.eclipse.scout.scout.server.sql.qdl.EntityQueryMappers;
import org.eclipse.scout.scout.server.sql.qdl.QDL;
import org.eclipse.scout.scout.shared.entities.IEntityService;
import org.eclipse.scout.scout.shared.entities.common.EntityQuery;
import org.eclipse.scout.scout.shared.entities.common.EntityTable;
import org.eclipse.scout.scout.shared.entities.common.IEntityQueryConstraint;
import org.eclipse.scout.scout.shared.entities.common.Table;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.eclipse.scout.scout.server.sql.qdl.QDL.eq;

public class EntityService implements IEntityService{

  @Override
  public <T extends EntityTable> T getEntityAliasInternal(Class<T> tableClass, boolean joined) {
    if (joined) {
      return TableAliases.getOrCreate(tableClass, Entities.JOINED_ENTITY_ALIAS_STRING);
    }
    return TableAliases.getOrCreate(tableClass);
  }

  @Override
  public <T extends EntityTable> List<T> fetch(EntityQuery<T> query) {
    EntityQueryMappers mappers = BEANS.get(EntityQueryMappers.class);

    List<IEntityQueryConstraint<T>> constraints = query.getConstraints();

    QDL.Condition condition = QDL.and(constraints.stream()
      .map(c -> mappers.getConditionFor(c, query))
      .filter(Objects::nonNull)
      .collect(Collectors.toList()));

    if (condition == null) {
      condition = eq("1", "1");
    }

    return Entities.fetchByCondition(query.getTableClass(), query.getAlias(), condition, query.isLimited());
  }

  @Override
  public <T extends Table> T getEntity(Class<T> entity, Object... primaryKey) {
    return Entities.fetch(entity, primaryKey);
  }

  @Override
  public <T extends Table> List<T> getAllEntites(Class<T> entity) {
    return Entities.fetchAll(entity);
  }

  @Override
  public <T extends Table> List<T> getAllEntitesLimited(Class<T> entity) {
    return Entities.fetchAllLimited(entity);
  }

  @Override
  public <T extends Table> List<T> getEntitesByColumnValue(Class<T> entity, Object columnValue, String physicalColumnName) {
    return Entities.fetchByColumnValue(entity, columnValue, physicalColumnName);
  }

  @Override
  public <T extends Table> T createAlias(Class<T> entity) {
    return TableAliases.getOrCreate(entity);
  }

}
