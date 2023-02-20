package org.eclipse.scout.scout.shared.code;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;

public class StatusCodeType extends AbstractSQLCodeType{

  private static final long serialVersionUID = 1L;
  public static final Long ID = 100L;

  public StatusCodeType() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredText() {
    return TEXTS.get("Status");
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
  public static class ActiveCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final Long ID = 101L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Active");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(20.0)
  public static class DeletedCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final Long ID = 102L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Deleted");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(30.0)
  public static class ReadyForDeletionCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final Long ID = 103L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ReadyForDeletion");
    }

    @Override
    public Long getId() {
      return ID;
    }

    @Override
    protected boolean getConfiguredActive() {
      return false;
    }
  }

  @Order(2000)
  public static class CascadeDeletedCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final Long ID = 104L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Deleted");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

}
