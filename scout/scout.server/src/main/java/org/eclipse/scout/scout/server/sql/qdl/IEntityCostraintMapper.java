package org.eclipse.scout.scout.server.sql.qdl;

import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.scout.shared.entities.common.EntityQuery;
import org.eclipse.scout.scout.shared.entities.common.EntityTable;
import org.eclipse.scout.scout.shared.entities.common.IEntityQueryConstraint;

@ApplicationScoped
interface IEntityConstraintMapper<T extends EntityTable, C extends IEntityQueryConstraint<T>> {

  Class<C> getEntityConstraintType();

  QDL.Condition condition(C constraint, EntityQuery<T> query);
}
