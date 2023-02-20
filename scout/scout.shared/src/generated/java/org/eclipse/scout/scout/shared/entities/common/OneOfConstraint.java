package org.eclipse.scout.scout.shared.entities.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OneOfConstraint<T extends EntityTable> implements IEntityQueryConstraint<T> {
  private List<IEntityQueryConstraint<T>> m_constraints = new ArrayList<>();

  private OneOfConstraint(List<IEntityQueryConstraint<T>> constraints) {
    m_constraints = constraints;
  }

  @SafeVarargs
  public static <T extends EntityTable> OneOfConstraint<T> oneOf(final IEntityQueryConstraint<T>... constraints) {
    return new OneOfConstraint<T>(Arrays.asList(constraints));
  }

  public static <T extends EntityTable> OneOfConstraint<T> oneOf(List<IEntityQueryConstraint<T>> constraints) {
    return new OneOfConstraint<T>(constraints);
  }

  public List<IEntityQueryConstraint<T>> getConstraints() {
    return m_constraints;
  }
}
