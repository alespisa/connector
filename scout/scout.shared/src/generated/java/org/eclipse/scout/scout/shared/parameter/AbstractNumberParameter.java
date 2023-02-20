package org.eclipse.scout.scout.shared.parameter;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.scout.shared.code.ParameterTypeCodeType;

public abstract class AbstractNumberParameter extends AbstractParameter<Number>{

  private static final long serialVersionUID = 1L;

  @Override
  public Long getConfiguredParameterTypeUid() {
    return ParameterTypeCodeType.NumberCode.ID;
  }

  @Override
  protected Number execFetchValue() throws ProcessingException {
    return BEANS.get(IParameterService.class).getNumberValue(getConfiguredParameterName());
  }

  @Override
  protected void execStore() throws ProcessingException {
    BEANS.get(IParameterService.class).storeNumberParameter(this);
  }

}
