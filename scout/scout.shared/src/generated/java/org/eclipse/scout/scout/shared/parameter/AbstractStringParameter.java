package org.eclipse.scout.scout.shared.parameter;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.scout.shared.code.ParameterTypeCodeType;

public abstract class AbstractStringParameter extends AbstractParameter<String>{

  private static final long serialVersionUID = 1L;

  @Override
  public String execFetchValue() throws ProcessingException {
    return BEANS.get(IParameterService.class).getStringValue(getConfiguredParameterName());
  }

  @Override
  public void execStore() throws ProcessingException {
    BEANS.get(IParameterService.class).storeStringParameter(this);
  }

  @Override
  public Long getConfiguredParameterTypeUid() {
    return ParameterTypeCodeType.StringCode.ID;
  }

}
