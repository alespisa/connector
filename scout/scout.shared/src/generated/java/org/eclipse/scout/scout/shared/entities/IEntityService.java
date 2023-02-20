package org.eclipse.scout.scout.shared.entities;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.scout.shared.entities.common.EntityQuery;
import org.eclipse.scout.scout.shared.entities.common.EntityTable;
import org.eclipse.scout.scout.shared.entities.common.Table;

import java.util.List;

public interface IEntityService extends IService {

  <T extends Table> T getEntity(Class<T> entity, Object... primaryKey);

  <T extends Table> List<T> getAllEntites(Class<T> entity);

  <T extends Table> List<T> getAllEntitesLimited(Class<T> entity);

  <T extends Table> List<T> getEntitesByColumnValue(Class<T> entity, Object columnValue, String physicalColumnName);

  <T extends Table> T createAlias(Class<T> entity);

  <T extends EntityTable> T getEntityAliasInternal(Class<T> entityTable, boolean joined);

  <T extends EntityTable> List<T> fetch(EntityQuery<T> query);

}
