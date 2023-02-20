package org.eclipse.scout.scout.shared.entities.common;

import java.security.BasicPermission;

public abstract class BsiConnectorPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  public BsiConnectorPermission(String name) {
    super(name);
  }

  public abstract String getDescription();

}
