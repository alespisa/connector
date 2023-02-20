package org.eclipse.scout.scout.shared.parameter;

import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.scout.shared.entities.common.BsiConnectorPermission;

public class UpdateParameterPermission extends BsiConnectorPermission {

  private static final long serialVersionUID = 1L;

  public UpdateParameterPermission() {
    super("UpdateParameter");
  }

  @Override
  public String getDescription() {
    return TEXTS.get("UpdateParameterPermission");
  }

}
