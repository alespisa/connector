package org.eclipse.scout.scout.server.sql.qdl;

import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.util.Assertions;
import org.eclipse.scout.scout.shared.entities.common.EntityQuery;
import org.eclipse.scout.scout.shared.entities.common.EntityTable;
import org.eclipse.scout.scout.shared.entities.common.IEntityQueryConstraint;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class EntityQueryMappers {

  @SuppressWarnings("rawtypes")
  private Map<Class<? extends IEntityQueryConstraint>, IEntityConstraintMapper<?, ?>> m_mappers = new HashMap<>();

  @PostConstruct
  public void init() {
    for (IEntityConstraintMapper<?, ?> mapper : BEANS.all(IEntityConstraintMapper.class)) {
      m_mappers.put(mapper.getEntityConstraintType(), mapper);
    }
  }

  @SuppressWarnings("unchecked")
  private <C extends IEntityQueryConstraint<?>> IEntityConstraintMapper<?, C> get(Class<C> constraintClass) {
    return (IEntityConstraintMapper<?, C>) m_mappers.get(constraintClass);
  }

  @SuppressWarnings("unchecked")
  public <T extends EntityTable, C extends IEntityQueryConstraint<T>> QDL.Condition getConditionFor(C constraint, EntityQuery<T> query) {
    IEntityConstraintMapper<T, C> mapper = (IEntityConstraintMapper<T, C>) get(constraint.getClass());
    Assertions.assertNotNull(mapper, "Couldn't find mapper for constraint {}", constraint.getClass().getName());
    return mapper.condition(constraint, query);
  }

}
