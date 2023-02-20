package org.eclipse.scout.scout.shared.parameter;

import org.eclipse.scout.rt.platform.BEANS;

import java.util.List;

public class PARAMETERS {

  private PARAMETERS() {
  }

  public static List<IParameter<?>> getAll() {
    return BEANS.get(IParameterService.class).getAllParameters();
  }

  public static <T> IParameter<T> get(Class<? extends IParameter<T>> parameterClass) {
    return BEANS.get(IParameterService.class).getParameter(parameterClass);
  }

  public static IParameter<?> get(String parameterName) {
    return BEANS.get(IParameterService.class).getParameter(parameterName);
  }

}
