package org.eclipse.scout.scout.shared.entities.common;

import java.io.Serializable;

public interface Table extends Serializable {

  String name();

  String alias();

  <T> T getValueByColumnMethodName(String columnMethodName);

  <T extends Table> Class<T> getTableClass();

}
