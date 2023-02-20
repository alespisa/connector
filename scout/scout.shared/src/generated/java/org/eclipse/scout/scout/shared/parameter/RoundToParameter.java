package org.eclipse.scout.scout.shared.parameter;

import org.eclipse.scout.rt.platform.text.TEXTS;

public class RoundToParameter extends AbstractNumberParameter{

  private static final long serialVersionUID = 1L;
  public static final String ID = "RoundTo";

  @Override
  public String getConfiguredLabel() {
    return TEXTS.get("RoundTo");
  }

  @Override
  public String getConfiguredParameterName() {
    return ID;
  }

  @Override
  public Long getParameterCode() {
    return ParameterCodeType.RoundToParameterCode.ID;
  }

  @Override
  protected boolean getConfiguredVisible() {
    return true;
  }

  @Override
  public Number getConfiguredDefaultValue() {
    return 0.05;
  }

}
