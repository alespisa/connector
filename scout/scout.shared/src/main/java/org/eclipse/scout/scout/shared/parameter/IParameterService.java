package org.eclipse.scout.scout.shared.parameter;

import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.service.IService;

public interface IParameterService extends IService {

  ParameterFormData store(ParameterFormData formData);

  boolean parameterExists(final String parameterName) throws ProcessingException;

  String getStringValue(final String parameterName) throws ProcessingException;

  void storeStringParameter(AbstractStringParameter abstractStringParameter) throws ProcessingException;

  ParameterFormData load(ParameterFormData formData) throws ProcessingException;

  ParameterFormData store(ParameterFormData formData) throws ProcessingException;

  <T> IParameter<T> getParameter(Class<? extends IParameter<T>> parameterClass);

  List<IParameter<?>> getAllParameters();

  Number getNumberValue(String configuredParameterName) throws ProcessingException;

  void storeNumberParameter(AbstractNumberParameter abstractNumberParameter) throws ProcessingException;

  IParameter<?> getParameter(String parameterName);

  Byte[] getBinaryValue(String parameterName) throws ProcessingException;

  void storeBinaryParameter(AbstractBinaryParameter param) throws ProcessingException;

  Boolean getBooleanValue(String parameterName) throws ProcessingException;

  void storeBooleanParameter(AbstractBooleanParameter param) throws ProcessingException;

  Integer getIntegerValue(String parameterName) throws ProcessingException;

  void storeIntegerParameter(AbstractIntegerParameter param) throws ProcessingException;

  Long getLongValue(String parameterName) throws ProcessingException;

  void storeLongValue(AbstractSmartParameter param) throws ProcessingException;

}
