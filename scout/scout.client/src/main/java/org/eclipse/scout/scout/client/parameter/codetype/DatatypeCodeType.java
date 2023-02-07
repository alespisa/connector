package org.eclipse.scout.scout.client.parameter.codetype;

import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

public class DatatypeCodeType extends AbstractCodeType{

  private static final Integer ID = 100;

  @Override
  public Integer getId() {
    return ID;
  }

  public class HL7 extends AbstractCode<String> {
    private static final String ID = "HL7";
  }

}
