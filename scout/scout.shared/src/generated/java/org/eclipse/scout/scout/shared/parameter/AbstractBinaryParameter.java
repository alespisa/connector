package org.eclipse.scout.scout.shared.parameter;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.scout.shared.code.ParameterTypeCodeType;
import org.eclipse.scout.scout.shared.parameter.*;

public abstract class AbstractBinaryParameter extends AbstractParameter<Byte[]>{

  private static final long serialVersionUID = 1L;

  private String m_fileName;

  @Override
  public Byte[] execFetchValue() throws ProcessingException {
    setFileName(BEANS.get(IParameterService.class).getStringValue(getConfiguredParameterName()));
    return BEANS.get(IParameterService.class).getBinaryValue(getConfiguredParameterName());
  }

  @Override
  public void execStore() throws ProcessingException {
    BEANS.get(IParameterService.class).storeBinaryParameter(this);
  }

  @Override
  public Long getConfiguredParameterTypeUid() {
    return ParameterTypeCodeType.BinaryCode.ID;
  }

  public String getFileName() {
    return m_fileName;
  }

  public void setFileName(String fileName) {
    m_fileName = fileName;
  }

}
