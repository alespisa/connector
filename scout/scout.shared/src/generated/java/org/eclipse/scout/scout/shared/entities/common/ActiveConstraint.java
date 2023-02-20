package org.eclipse.scout.scout.shared.entities.common;

import org.eclipse.scout.rt.platform.util.TriState;

public class ActiveConstraint<T extends EntityTable> implements IEntityQueryConstraint<T> {

  private TriState m_active;

  private ActiveConstraint(TriState active) {
    m_active = active;
  }

  public static <T extends EntityTable> ActiveConstraint<T> of(TriState active) {
    return new ActiveConstraint<T>(active);
  }

  public TriState getActive() {
    return m_active;
  }

  public void setActive(TriState active) {
    m_active = active;
  }

}
