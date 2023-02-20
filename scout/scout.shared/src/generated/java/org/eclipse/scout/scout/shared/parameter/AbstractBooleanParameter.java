package org.eclipse.scout.scout.shared.parameter;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.scout.shared.code.ParameterTypeCodeType;

public abstract class AbstractBooleanParameter extends AbstractParameter<Boolean>{
  private static final long serialVersionUID = 1L;

  @Override
  public Long getConfiguredParameterTypeUid() {
    return ParameterTypeCodeType.BooleanCode.ID;
  }

  @Override
  protected Boolean execFetchValue() throws ProcessingException {
    return BEANS.get(IParameterService.class).getBooleanValue(getConfiguredParameterName());
  }

  @Override
  protected void execStore() throws ProcessingException {
    BEANS.get(IParameterService.class).storeBooleanParameter(this);
  }

}
