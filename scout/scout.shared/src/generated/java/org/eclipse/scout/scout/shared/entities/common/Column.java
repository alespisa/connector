package org.eclipse.scout.scout.shared.entities.common;

import java.util.List;

/**
 * @param <T>: JavaType
 */
public interface Column<T> {

  String name();

  Table table();

  String fullyQualifiedName();

  List<Object> getBindBases();

  T value();

}
