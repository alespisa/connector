package org.eclipse.scout.scout.shared.parameter;

import org.eclipse.scout.rt.platform.text.TEXTS;

public class MaxRowsParameter extends AbstractIntegerParameter{

  private static final long serialVersionUID = 1L;
  public static final String ID = "MaxRowNumber";

  @Override
  public String getConfiguredLabel() {
    return TEXTS.get("MaxRowNumber");
  }

  @Override
  public String getConfiguredParameterName() {
    return ID;
  }

  @Override
  protected boolean getConfiguredVisible() {
    return true;
  }

  @Override
  public Long getParameterCode() {
    return ParameterCodeType.MaxRowsParameterCode.ID;
  }

  @Override
  public Integer getConfiguredDefaultValue() {
    return 1000;
  }

}
