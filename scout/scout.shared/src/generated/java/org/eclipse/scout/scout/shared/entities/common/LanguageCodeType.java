package org.eclipse.scout.scout.shared.entities.common;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.scout.shared.code.AbstractSQLCodeType;

public class LanguageCodeType extends AbstractSQLCodeType{

  private static final long serialVersionUID = 1L;

  public static final Long ID = 1000L;

  public LanguageCodeType() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredText() {
    return TEXTS.get("Language");
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
  @ClassId("2de1bfb7-1607-4713-b549-1743ed89b190")
  public static class EnglishCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;

    public static final Long ID = 1001L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("English");
    }

    @Override
    public Long getId() {
      return ID;
    }

    @Override
    protected String getConfiguredExtKey() {
      return "en";
    }
  }

  @Order(20.0)
  @ClassId("56fc553b-0d6a-4703-97e8-593477d11333")
  public static class GermanCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;

    public static final Long ID = 1002L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("German");
    }

    @Override
    public Long getId() {
      return ID;
    }

    @Override
    protected String getConfiguredExtKey() {
      return "de-CH";
    }

  }

  @Order(20.0)
  @ClassId("4db1dbcb-4546-4a9e-b52d-f914e0f80b8a")
  public static class FrenchCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;

    public static final Long ID = 1003L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("French");
    }

    @Override
    public Long getId() {
      return ID;
    }

    @Override
    protected String getConfiguredExtKey() {
      return "fr";
    }
  }

  @Order(20.0)
  @ClassId("8298abe0-5b6b-42e8-9741-2e4786fed993")
  public static class ItalianCode extends AbstractCode<Long> {

    private static final long serialVersionUID = 1L;

    public static final Long ID = 1004L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Italian");
    }

    @Override
    public Long getId() {
      return ID;
    }

    @Override
    protected String getConfiguredExtKey() {
      return "it";
    }
  }

}
