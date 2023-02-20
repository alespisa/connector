package org.eclipse.scout.scout.shared.entities.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Join {

  static final class NullEntityProvider implements EntityProvider<Table, Table> {
    @Override
    public Table getEntity(Table from) {
      return null;
    }
  }

  /**
   * Joins a Table in a 1-n or n-1 relationship.<br />
   * Since primary keys are resolved automatically, by the @PrimaryKey Annotation, only the foreign key must be provided.
   * <p>
   * <b>n-1 Relationship (single Entity is returned by the annotated method)</b>: the foreign key is in the Table where
   * the join is defined.<br />
   * <b>1-n Relationship (java.util.List is returned by the annotated method)</b>: the foreign key is in the Table that is
   * returned by the join method.
   * </p>
   * <p>
   * Joined entity will be cached inside the entity and not invalidated during the live of an entity.
   * </p>
   *
   * @return the joined entity / entities
   */
  String foreignKeyColumn() default "";

  Class<? extends EntityProvider<?, ?>> entityProvider() default NullEntityProvider.class;
}
