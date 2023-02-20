package org.eclipse.scout.scout.shared.code;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;

public class ParameterTypeCodeType extends AbstractSQLCodeType{

  private static final long serialVersionUID = 1L;

  public static final Long ID = 1000060L;

  public ParameterTypeCodeType() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredText() {
    return TEXTS.get("ParameterType");
  }

  @Override
  public Long getId() {
    return ID;
  }

  @Override
  public boolean getConfiguredShowInAdministrationOutline() {
    return false;
  }

  @Order(10.0)
  @ClassId("03572944-40fb-4254-99a5-9576d29a5a03")
  public static class NumberCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;

    public static final Long ID = 1000061L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Number0");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(20.0)
  @ClassId("b70d3e45-fe20-4cd3-b600-3609033eae98")
  public static class BooleanCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;

    public static final Long ID = 1000062L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Boolean");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(30.0)
  @ClassId("60369ae2-3b42-4817-9a26-8c67de7378b0")
  public static class TimeCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;

    public static final Long ID = 1000063L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Time");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(40.0)
  @ClassId("b579cb62-97f5-44d9-900a-fd03677cf0ce")
  public static class StringCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;

    public static final Long ID = 1000064L;
    public static final int INT_ID = 1000064;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("String");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(50.0)
  @ClassId("e72a8dd1-6825-441f-b234-a926d9f78c15")
  public static class BinaryCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;

    public static final Long ID = 1000065L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Binary");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(60.0)
  @ClassId("6c50cf7c-0c77-424d-9be5-2a42f1fe98c7")
  public static class IntegerCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;

    public static final Long ID = 1100065L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Integer");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(2000)
  public static class SmartCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final Long ID = 1100066L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("SmartCode");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(3000)
  public static class PasswordCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final Long ID = 1100067L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Password");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

}
