package org.eclipse.scout.scout.shared.entities.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotConstraint<T extends EntityTable> implements IEntityQueryConstraint<T> {
  private List<IEntityQueryConstraint<T>> m_constraints = new ArrayList<>();

  public NotConstraint(List<IEntityQueryConstraint<T>> constraints) {
    m_constraints = constraints;
  }

  @SafeVarargs
  public static <T extends EntityTable> NotConstraint<T> not(final IEntityQueryConstraint<T> ...constraints) {
    return new NotConstraint<T>(Arrays.asList(constraints));
  }

  public static <T extends EntityTable> NotConstraint<T> not(List<IEntityQueryConstraint<T>> constraints) {
    return new NotConstraint<T>(constraints);
  }

  public List<IEntityQueryConstraint<T>> getConstraints() {
    return m_constraints;
  }

}
