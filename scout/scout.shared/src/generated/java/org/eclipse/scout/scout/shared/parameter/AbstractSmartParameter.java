package org.eclipse.scout.scout.shared.parameter;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;

public class AbstractSmartParameter extends AbstractParameter<Long>{

  private static final long serialVersionUID = 1L;

  public Long getConfiguredParameterTypeUid() {
    return ParameterTypeCodeType.SmartCode.ID;
  }

  protected Long execFetchValue() throws ProcessingException {
    return BEANS.get(IParameterService.class).getLongValue(getConfiguredParameterName());
  }

  @Override
  protected void execStore() throws ProcessingException {
    BEANS.get(IParameterService.class).storeLongValue(this);
  }

  public abstract Class<? extends ICodeType<?, Long>> getConfiguredCodeType();

}
