package org.eclipse.scout.scout.server.sql.qdl;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.scout.shared.entities.common.Table;

import java.util.List;

public interface IEntities extends IService {

  <T extends Table> T fetchInExistingServerContext(Class<T> clazz, Object... primaryKey);

  <T extends Table> List<T> fetchByConditionInExistingServerContext(Class<T> tableClass, T alias, QDL.Condition condition, boolean limited);

}
