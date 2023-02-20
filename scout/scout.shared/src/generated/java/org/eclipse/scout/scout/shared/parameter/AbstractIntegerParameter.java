package org.eclipse.scout.scout.shared.parameter;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.scout.shared.code.ParameterTypeCodeType;

public abstract class AbstractIntegerParameter extends AbstractParameter<Integer>{

  private static final long serialVersionUID = 1L;

  @Override
  public Long getConfiguredParameterTypeUid() {
    return ParameterTypeCodeType.IntegerCode.ID;
  }

  @Override
  protected Integer execFetchValue() throws ProcessingException {
    return BEANS.get(IParameterService.class).getIntegerValue(getConfiguredParameterName());
  }

  @Override
  protected void execStore() throws ProcessingException {
    BEANS.get(IParameterService.class).storeIntegerParameter(this);
  }

}
