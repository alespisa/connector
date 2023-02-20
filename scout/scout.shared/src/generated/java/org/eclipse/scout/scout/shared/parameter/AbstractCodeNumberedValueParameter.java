package org.eclipse.scout.scout.shared.parameter;

public abstract class AbstractCodeNumberedValueParameter extends AbstractNumberParameter{

  private static final long serialVersionUID = 1L;

  public abstract long getCodeId();

  public abstract long getCodeTypeId();

}
