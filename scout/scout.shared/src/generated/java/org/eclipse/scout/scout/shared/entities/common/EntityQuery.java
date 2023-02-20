package org.eclipse.scout.scout.shared.entities.common;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.util.TriState;
import org.eclipse.scout.scout.shared.entities.IEntityService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class EntityQuery<T extends EntityTable>{

  private final Class<T> m_tableClass;
  private T m_alias;

  private boolean m_limited = false;
  private List<IEntityQueryConstraint<T>> m_constraints = new ArrayList<>();

  public EntityQuery(Class<T> tableClass) {
    m_tableClass = tableClass;
    m_alias = BEANS.get(IEntityService.class).getEntityAliasInternal(m_tableClass, false);
  }

  public EntityQuery(Class<T> tableClass, boolean joined) {
    m_tableClass = tableClass;
    m_alias = BEANS.get(IEntityService.class).getEntityAliasInternal(m_tableClass, joined);
  }

  public static <T extends EntityTable> EntityQuery<T> create(Class<T> entity) {
    return new EntityQuery<T>(entity);
  }
  public static <T extends EntityTable> EntityQuery<T> create(Class<T> entity, boolean joined) {
    return new EntityQuery<T>(entity, joined);
  }

  // --- builder ---

  public EntityQuery<T> withLimited(boolean limited) {
    m_limited = limited;
    return this;
  }

  public EntityQuery<T> withActive(TriState active) {
    m_constraints.add(ActiveConstraint.of(active));
    return this;
  }

  public EntityQuery<T> withActive(boolean active) {
    m_constraints.add(ActiveConstraint.of(TriState.parse(active)));
    return this;
  }

  public EntityQuery<T> withConstraint(IEntityQueryConstraint<T> constraint) {
    m_constraints.add(constraint);
    return this;
  }

  public EntityQuery<T> withConstraints(List<IEntityQueryConstraint<T>> constraints) {
    m_constraints.addAll(constraints);
    return this;
  }

  @SafeVarargs
  public final EntityQuery<T> withConstraints(IEntityQueryConstraint<T>... constraints) {
    for (IEntityQueryConstraint<T> constraint : constraints) {
      m_constraints.add(constraint);
    }
    return this;
  }

  public <V> EntityQuery<T> withColumnValue(Function<? super T, ? extends Column<V>> columnMapper, V value) {
    m_constraints.add(ColumnValueEqConstraint.columnValueEq(columnMapper, value));
    return this;
  }

  public <V> EntityQuery<T> withColumnValueIsNot(Function<? super T, ? extends Column<V>> columnMapper, V value) {
    m_constraints.add(ColumnValueIsNotConstraint.columnValueIsNot(columnMapper, value));
    return this;
  }

  public <V> EntityQuery<T> withColumnValueOtherThan(Function<? super T, ? extends Column<V>> columnMapper, V value) {
    m_constraints.add(ColumnValueOtherThanConstraint.columnValueOtherThan(columnMapper, value));
    return this;
  }

  public EntityQuery<T> withColumnValueContaining(Function<? super T, ? extends Column<String>> columnMapper, String value) {
    m_constraints.add(ColumnValueContainsConstraint.columnValueContains(columnMapper, value));
    return this;
  }

  public EntityQuery<T> withColumnValueStartingWith(Function<? super T, ? extends Column<String>> columnMapper, String value) {
    m_constraints.add(ColumnValueStartsWithConstraint.columnValueStartsWith(columnMapper, value));
    return this;
  }

  public EntityQuery<T> withColumnValueStartingWithOptional(Function<? super T, ? extends Column<String>> columnMapper, String value) {
    if(value != null) m_constraints.add(ColumnValueStartsWithConstraint.columnValueStartsWith(columnMapper, value));
    return this;
  }

  public <V>EntityQuery<T> withColumnValueIsGreaterThanOrEquals(Function<? super T, ? extends Column<V>> columnMapper, V value) {
    m_constraints.add(ColumnValueIsGreaterThanOrEqualsConstraint.columnValueIsGreaterThanOrEquals(columnMapper, value));
    return this;
  }

  public <V>EntityQuery<T> withColumnValueIsSmallerThanOrEquals(Function<? super T, ? extends Column<V>> columnMapper, V value) {
    m_constraints.add(ColumnValueIsSmallerThanOrEqualsConstraint.columnValueIsSmallerThanOrEquals(columnMapper, value));
    return this;
  }

  @SafeVarargs
  public final <Q, R extends Column<Q>> EntityQuery<T> withOneOf(IEntityQueryConstraint<T>... constraints) {
    m_constraints.add(OneOfConstraint.oneOf(constraints));
    return this;
  }

  public final <Q, R extends Column<Q>> EntityQuery<T> withOneOf(List<IEntityQueryConstraint<T>> constraints) {
    m_constraints.add(OneOfConstraint.oneOf(constraints));
    return this;
  }

  @SafeVarargs
  public final<Q, R extends Column<Q>> EntityQuery<T> withNot(IEntityQueryConstraint<T> ...constraints) {
    m_constraints.add(NotConstraint.not(constraints));
    return this;
  }

  public final<Q, R extends Column<Q>> EntityQuery<T> withNot(List<IEntityQueryConstraint<T>> constraints) {
    m_constraints.add(NotConstraint.not(constraints));
    return this;
  }

  public List<T> fetch() {
    return BEANS.get(IEntityService.class).fetch(this);
  }

  public Stream<T> stream() {
    return fetch().stream();
  }

  // --- getter ---

  public Class<T> getTableClass() {
    return m_tableClass;
  }

  public T getAlias() {
    return m_alias;
  }

  public boolean isLimited() {
    return m_limited;
  }

  public List<IEntityQueryConstraint<T>> getConstraints() {
    return m_constraints;
  }

}
