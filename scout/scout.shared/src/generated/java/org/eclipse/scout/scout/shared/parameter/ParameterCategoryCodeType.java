package org.eclipse.scout.scout.shared.parameter;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.scout.shared.code.AbstractSQLCodeType;

public class ParameterCategoryCodeType extends AbstractSQLCodeType {

  private static final long serialVersionUID = 1L;
  public static final long ID = 1230000L;

  @Override
  public Long getId() {
    return ID;
  }

  @Override
  public boolean getConfiguredShowInAdministrationOutline() {
    return false;
  }

  @Order(1000)
  public static class GeneralCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1230001L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("General");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(2000)
  public static class AnisAmicusCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1230002L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ANISAMICUS");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(3000)
  public static class VatCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1230003L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Vat");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(4000)
  public static class IsAbvCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1230004L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ISABV");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(5000)
  public static class DunningCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1230005L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6000)
  public static class PlanningCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1230006L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Planning");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(7000)
  public static class TreatmentCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1230007L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Treatment");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(8000)
  public static class InvoiceCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1230008L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Invoice");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

}
