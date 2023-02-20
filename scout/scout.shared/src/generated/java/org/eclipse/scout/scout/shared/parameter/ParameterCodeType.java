package org.eclipse.scout.scout.shared.parameter;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.scout.shared.code.AbstractSQLCodeType;

public class ParameterCodeType extends AbstractSQLCodeType {

  private static final long serialVersionUID = 1L;
  public static final long ID = 161763L;

  @Override
  public Long getId() {
    return ID;
  }

  @Override
  public boolean getConfiguredShowInAdministrationOutline() {
    return false;
  }

  @Order(3000)
  @ClassId("cdbc20f3-8beb-413a-b8aa-d7362587c651")
  public static class BillSummaryParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161767L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InvoiceSummary");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(4000)
  @ClassId("e80cbd5d-d88a-4b36-9654-6fe98835277a")
  public static class CarriageReturnParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161768L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Zeilenumbruch");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(5000)
  @ClassId("050a8aeb-7080-4c7e-8ce5-5c319cd3a948")
  public static class CurrencyParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161770L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Currency");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(5250)
  @ClassId("c02b07a7-a685-4b59-bfc1-df5feaa9e18a")
  public static class TreatmentDurationCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 163558L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("TreatmentDuration");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(5500)
  @ClassId("909aaa52-5a4f-45f6-80c7-4d41875334df")
  public static class RoundToParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 163289L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("RoundTo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6000)
  @ClassId("a2fc90cb-af1c-4759-a777-5b3be90d3bfd")
  public static class Dunning1AddressParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161771L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("PrintDunningAddress1");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(7000)
  @ClassId("f1173e9c-4311-4602-ac4f-f62b5a0de8fc")
  public static class Dunning1ExtraChargesParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161772L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("AdditionalFeeForDunning1");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(8000)
  @ClassId("16683952-5baa-4e59-8935-42be0977d606")
  public static class Dunning1FooterParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161773L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning1Footer");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(9000)
  @ClassId("1aeb9125-1eb3-4408-8716-c8f141383f92")
  public static class Dunning1LogoParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161774L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("PrintLogoForDunning1");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(10000)
  @ClassId("81a7085a-afd5-4df6-a4aa-bad31cce6c68")
  public static class Dunning1PrintFooterParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161775L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning1PrintFooter");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(11000)
  @ClassId("4031a465-a038-4146-8227-e53402ad6300")
  public static class Dunning1PrintLogoParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161776L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning1PrintLogo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(12000)
  @ClassId("2f589f93-38f4-482f-a42c-486e820fa1ec")
  public static class Dunning1PrintPayementSlipParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161777L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning1PrintPaymentSlip");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(13000)
  @ClassId("3fc8d014-62dd-473c-b86d-4863604f5443")
  public static class Dunning1PrintSenderParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161778L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning1PrintSender");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(14000)
  @ClassId("abbd6ff5-de33-40e2-92f3-96e7ecc60222")
  public static class Dunning1SummaryParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161779L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning1Summary");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(15000)
  @ClassId("019d05c4-f9a3-4865-8d19-926a3195570e")
  public static class Dunning1TextParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161780L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Mahnung1Text");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(16000)
  @ClassId("df7dcf93-1222-4836-8b5c-4dc4a0861316")
  public static class Dunning1TitelParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161781L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning1Title");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(1700)
  @ClassId("23604da8-345f-4259-addf-a7064fc3562e")
  public static class Dunning1TermOfPaymentParameterCode extends AbstractCode<Long> {
    public static final long ID = 161801L;
    private static final long serialVersionUID = 1L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning1TermOfPayment");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(17000)
  @ClassId("7ed3fffe-e06a-4fc2-8253-0f1cff7bcc45")
  public static class Dunning2AddressParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161782L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning2PrintLogo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(18000)
  @ClassId("bcfc2f6e-f2c1-492b-818f-dd4d747b3f2d")
  public static class Dunning2ExtraChargesParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161783L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("FeeForDunning2");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(19000)
  @ClassId("1c743b9f-0136-47e6-93b8-b1b3dc7fd7c8")
  public static class Dunning2FooterParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161784L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning2Footer");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(20000)
  @ClassId("70fdf656-c258-426f-8cfa-c3cc1efc83dd")
  public static class Dunning2LogoParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161785L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning2Logo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(21000)
  @ClassId("5ce51148-1a31-46b8-a603-5f6bc6adac68")
  public static class Dunning2PrintFooterParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161786L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning2PrintFooter");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(22000)
  @ClassId("98af3e51-f3f5-48e1-a656-7b7a48930476")
  public static class Dunning2PrintLogoParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161787L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning2PrintLogo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(23000)
  @ClassId("73f9f824-d40e-4d98-9b89-9a53f2a6cb53")
  public static class Dunning2PrintPaymentSlipParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161788L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning2PrintPaymentSlip");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(24000)
  @ClassId("7dd9e164-0b4d-43af-becd-18e9f4977320")
  public static class Dunning2PrintSenderParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161789L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning2PrintSender");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(25000)
  @ClassId("1519ba6b-3d50-4618-94ce-76e357e5f8c4")
  public static class Dunning2SummaryParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161790L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning2Summary");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(26000)
  @ClassId("6c356050-dfef-46ae-a0f3-9f81e7b12ccf")
  public static class Dunning2TextParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161791L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning2Text");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(27000)
  @ClassId("25f98427-5631-4fc8-aefc-92822eaf0b04")
  public static class Dunning2TitleParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161792L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning2Title");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(28000)
  @ClassId("23604da8-345f-4259-addf-a7064fc3562e")
  public static class Dunning2TermOfPaymentParameterCode extends AbstractCode<Long> {
    public static final long ID = 161801L;
    private static final long serialVersionUID = 1L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning2TermOfPayment");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(28000)
  @ClassId("63918227-7507-4850-b895-eedebece7393")
  public static class Dunning3ExtraChargesParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161793L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("FeeForDunning3");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(29000)
  @ClassId("3526a25c-e812-4202-bef4-edc3d8ef0da4")
  public static class Dunning3FooterParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161794L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning3Footer");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(30000)
  @ClassId("0af8f607-44dd-4e95-bdab-d67dc0e73a27")
  public static class Dunning3LogoParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161795L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning3Logo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(31000)
  @ClassId("34165df0-5acb-4135-bce2-be98465c1106")
  public static class Dunning3PrintFooterParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161796L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning3PrintFooter");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(32000)
  @ClassId("d6f29831-b800-403a-9115-447f5179875d")
  public static class Dunning3PrintLogoParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161797L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning3PrintLogo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(33000)
  @ClassId("c8984259-659f-4de8-a1cf-c3e0e9c1ca08")
  public static class Dunning3PrintPaymentSlipParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161798L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning3PrintPaymentSlip");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(34000)
  @ClassId("e102e40e-f92b-4924-9c1c-5b9f47fd038e")
  public static class Dunning3PrintSenderParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161799L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning3PrintSender");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(35000)
  @ClassId("74fb788c-b195-4b67-a53f-97b0c8b856e1")
  public static class Dunning3SummaryParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161800L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning3Summary");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(36000)
  @ClassId("23604da8-345f-4259-addf-a7064fc3562e")
  public static class Dunning3TermOfPaymentParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161801L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning3TermOfPayment");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(37000)
  @ClassId("1a651c74-a7b1-45c7-9268-b441233b69c1")
  public static class Dunning3TextParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161802L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning3Text");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(38000)
  @ClassId("1c47109a-166b-4f27-a767-68ed9d9a32b2")
  public static class Dunning3TitelParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161804L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning3Title");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(39000)
  @ClassId("87357e74-9f2d-4ae5-b61a-ea1a6af15883")
  public static class FirstHourOfDayParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161805L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("FirstHourOfDay");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(40000)
  @ClassId("6d494d87-3dfe-4efe-81d6-6d27a28a05c1")
  public static class FooterParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161806L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Footer");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(41000)
  @ClassId("606d3623-5856-415e-952b-b10bc938a8d4")
  public static class InvoiceLeftSlipSenderPositionParameterXCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161807L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InvoiceLeftSlipSenderPositionX");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(42000)
  @ClassId("855ea733-ffc8-4400-985e-f14a4fff30ca")
  public static class InvoiceLogoParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161808L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InvoiceLogo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(43000)
  @ClassId("b0f3ad3e-73b1-4d18-8289-f024ca67491e")
  public static class InvoicePrintFooterParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161809L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("RechnungFusszeileDrucken");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(44000)
  @ClassId("6576ce60-4b15-4449-b757-d59cc43ff1ca")
  public static class InvoicePrintLogoParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161810L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InvoicePrintLogo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(45000)
  @ClassId("82119485-11e9-4eb3-9a34-aa2e1b270748")
  public static class InvoicePrintPaymentSlipParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161811L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InvoicePrintPaymentSlipAddress");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(46000)
  @ClassId("807206b3-1a55-4ecd-9d2c-7eb926de7278")
  public static class InvoicePrintSenderParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161812L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InvoicePrintSender");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(47000)
  @ClassId("8fda8f13-331e-4f84-b839-390d01e021d3")
  public static class InvoiceReceiverPositionParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161813L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InvoiceReceiverPosition");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(48000)
  @ClassId("6373ff44-9406-41f7-a87c-948fcf829139")
  public static class InvoiceRightSlipSenderPositionParameterXCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161814L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InvoiceRightSlipSenderPositionX");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(49000)
  @ClassId("7743b17f-db49-414f-8e57-ee07a4a7166d")
  public static class InvoiceTextParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161815L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InvoiceText");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(50000)
  @ClassId("b1ba0c96-c496-4fc1-9ac5-1ac87478f7af")
  public static class InvoiceTitelParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161816L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InvoiceTitle");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(51000)
  @ClassId("d68f7ceb-cf09-4245-b44e-eb0c7be024a7")
  public static class InvoiceTopAddrYParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161817L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InvoiceTopAddressYOffset");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(52000)
  @ClassId("3cae6414-2c01-49ac-bbc3-10ff6f4636ff")
  public static class LastHourOfDayParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161818L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("LastHourOfDay");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(53000)
  @ClassId("7cbffc46-14e8-473a-9a9c-7ac79a2f7048")
  public static class MaxRowsParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161819L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("MaxRowNumber");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(54000)
  @ClassId("8712b2d9-fcab-42a0-b2b0-2a448b943875")
  public static class MedicalHistoryAnisParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161820L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("MedicalHistoryPrintANISData");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }


  @Order(56000)
  @ClassId("da6d93d7-1dfa-4802-ae96-2308efaf3d81")
  public static class MedicalHistoryLogoParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161822L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("MedicalHistoryLogo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(57000)
  @ClassId("de0cbb3a-f5e7-47b1-a02c-ed2f3b4200ee")
  public static class MedicalHistoryPrintLogoParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161823L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("MedicalHistoryPrintLogo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(58000)
  @ClassId("3637fa1e-fe5e-4bc6-a0c5-b1f4ad9bdcea")
  public static class MedicalHistoryPrintSenderParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161825L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("MedicalHistoryPrintSender");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(59000)
  @ClassId("7ce9ddb8-61dc-4aef-94e0-4ae1eb354a4d")
  public static class PraxisNoParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161826L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("PraxisNo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(60000)
  @ClassId("38878a85-fb58-4865-b579-3c8146c92502")
  public static class ReceiptFooterParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161827L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ReceiptFooter");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(61000)
  @ClassId("8bdd7dfa-0176-4073-bbe8-bf2ad710f7cc")
  public static class ReceiptLogoParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161828L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ReceiptLogo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(62000)
  @ClassId("338ac60b-d5e2-4e74-ac03-4fab4f1c9962")
  public static class ReceiptLogoPrintParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161829L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ReceiptPrintLogo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(63000)
  @ClassId("e1b8abe2-2c62-4884-a133-2118ba7c3b28")
  public static class ReceiptPrintFooterParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161830L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ReceiptPrintFooter");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(64000)
  @ClassId("939d4b7d-361c-4286-b05e-c2f85f074cf6")
  public static class ReceiptPrintSenderParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161832L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ReceiptPrintSender");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(65000)
  @ClassId("6ece1476-9016-48a6-bdd0-fcc439b36577")
  public static class ReceiptSummaryParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161833L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ReceiptSummary");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(66000)
  @ClassId("5aaa7c2a-de54-454b-bee8-6bad5e86ad02")
  public static class ReceiptTextParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161834L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ReceiptText");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(67000)
  @ClassId("56bc5009-55a3-427f-8c08-1fe4822a44b1")
  public static class SenderParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161835L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Sender");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(68000)
  @ClassId("9d5996ff-3cc7-4823-9526-52a89eec1257")
  public static class ShowMapParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161836L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("AddressShowMap");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(69000)
  @ClassId("9e8c1b56-7390-4835-88b4-53ebfdafce2e")
  public static class Vat1NumberValueParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161837L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Vat1");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(70000)
  @ClassId("1251b40c-9f80-44b8-bb33-aaebe92fdf1c")
  public static class Vat2NumberValueParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161839L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Vat2");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(71000)
  @ClassId("c5947026-4986-4031-8e82-200bcb14609c")
  public static class Vat3NumberValueParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161840L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Vat3");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(72000)
  @ClassId("0c0b2e23-f874-4cf7-88f8-32a3a7c35cac")
  public static class Vat4NumberValueParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161841L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Vat4");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(73000)
  @ClassId("c45f54a2-8780-476c-9b21-f073397abe15")
  public static class Vat5NumberValueParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161842L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Vat5");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(74000)
  @ClassId("30e08e74-de69-458c-96d2-b4a5112484fb")
  public static class ReminderOrganizerTimeSpanParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161843L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ReminderOrganizerTimeSpan");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(75000)
  @ClassId("86c7dcab-5821-4604-bbed-d62c7ad80c55")
  public static class LabelLogoParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161844L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("LabelLogo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(76000)
  @ClassId("f34a1489-110e-4096-8b85-e00454510e7f")
  public static class LabelSenderParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161845;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("LabelSender");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(77000)
  @ClassId("045b0cb9-d436-4e7c-85c9-164f29b20a1b")
  public static class InvoicePrintSalutationParamterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161846;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("PrintSalutation");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(78000)
  @ClassId("164302f5-4003-4db2-ad6a-99dd89c18268")
  public static class Dunning1PrintSalutationParamterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161847;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning1PrintSalutation");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(79000)
  @ClassId("e64615f9-ca98-435d-8ab0-d7ca47581fef")
  public static class Dunning2PrintSalutationParamterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161848;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning2PrintSalutation");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(80000)
  @ClassId("e19b65e3-21a6-4eb1-be55-56f56c62f2cb")
  public static class Dunning3PrintSalutationParamterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161849;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Dunning3PrintSalutation");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(81000)
  @ClassId("60dc0972-165a-4aa9-95d8-33ba187bb521")
  public static class ReceiptPrintSalutationParamterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161850;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ReceiptPrintSalutation");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(82000)
  @ClassId("b03379cb-ad01-4d20-8b32-d7aba75500f5")
  public static class TreatmentReminderPrintLogoCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161851;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("TreatmentReminderPrintLogo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(83000)
  @ClassId("75dab02e-1f74-4fbb-be3a-ff90789b319c")
  public static class TreatmentReminderLogoCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161852;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("TreatmentReminderLogo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(84000)
  @ClassId("577e8f1b-cc68-467a-b2fc-3e3321f41d11")
  public static class TreatmentReminderPrintSenderCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161853;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("TreatmentReminderPrintSender");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(85000)
  @ClassId("54a1ee7f-0315-4e94-80e8-3e1b9fc5bd92")
  public static class TreatmentReminderTitleCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161854;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("TreatmentReminderTitle");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(86000)
  @ClassId("5919dd95-9e18-401d-8125-1b77d232f4c6")
  public static class TreatmentReminderPrintSalutationCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161855;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("TreatmentReminderPrintSalutation");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(87000)
  @ClassId("55b9ba05-b52b-4e6a-a07e-78f22cef86c6")
  public static class TreatmentReminderTextCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161856;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("TreatmentReminderText");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(88000)
  @ClassId("b7bd1c78-8ad6-4661-bbfc-d75bdf08a62b")
  public static class TreatmentReminderNextActionCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161857;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("TreatmentReminderNextAction");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(89000)
  @ClassId("77097a61-21f9-494d-a1f7-f6a40ae96c43")
  public static class TreatmentReminderGreetingsCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161858;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("TreatmentReminderGreetings");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(90000)
  public static class TreatmentReminderCollectiveServiceNameCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161859;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("CollectiveServiceName");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(91000)
  public static class IsAbvUidCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161860;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ISABVCode");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(92000)
  public static class IsAbvAppendixCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161861;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ISABVAppendix");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(93000)
  public static class IsAbvTokenCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161862;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ISABVToken");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(94000)
  public static class IsAbvUrlCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161863;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ISABVUrl");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(95000)
  public static class DisableRetailUnitParameterCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161864;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("DisableRetailUnit");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(96000)
  public static class UseCurrentDateCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161864;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("UseCurrentDate");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(2500)
  @ClassId("82b2e2eb-03bd-4da2-b4d1-269af88c230c")
  public static class AnisAmicusPersonIdCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161865L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("AnisAmicusPersonen-ID");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(2550)
  @ClassId("7ce1cf67-1715-47b8-9ec0-6f360b6e3995")
  public static class AnisAmicusPasswordCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161866l;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("AnisAmicusPasswort");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(2600)
  @ClassId("096f2fec-5dbc-4e0c-8d29-1a55b31e92a3")
  public static class AnisAmicusManufacturerKeyCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1618663L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("AnisAmicusManufacturer-Key");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(2650)
  @ClassId("43364e0f-aa49-47f3-a305-1d03731f6c29")
  public static class AmicusUrlCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1618662l;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("AmicusUrl");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(2655)
  public static class AnisUrlCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1618664L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("AnisUrl");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(3327)
  public static class ActivateAnisCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1618665L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("AnisActive");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(4000)
  @ClassId("69ca8d4a-db1f-4019-ad59-5aaa9eb66db6")
  public static class AmicusTokenCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1618663l;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("AmicusToken");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(5000)
  public static class IbanNumberCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1618666l;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("IBANNumber");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6000)
  public static class ESRIdentificationNumberCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1618667l;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ESRIdentificationNumber");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6001)
  public static class SenderNameCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1618668l;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("SenderName");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6002)
  public static class SenderAddressCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1618669l;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("SenderAddress");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6004)
  public static class SenderZipCityCountryCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 1618670l;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("SenderZipCityCountry");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6005)
  public static class InternationalCertificateTitleCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161865L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InternationalCertificateTitle");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6006)
  public static class InternationalCertificateTextCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161866L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InternationalCertificateText");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6007)
  public static class InternationalCertificateTravelTextCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161867L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InternationalCertificateTravelText");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6008)
  public static class InternationalCertificateCommentTextCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161868L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InternationalCertificateCommentText");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6009)
  public static class InternationalCertificateLogoCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161869L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InternationalCertificateLogo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6010)
  public static class InternationalCertificatePrintSenderCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161870L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InternationalCertificatePrintSender");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6011)
  public static class InternationalCertificatePrintLogoCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161871L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InternationalCertificatePrintLogo");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6012)
  public static class InternationalCertificatePrintSalutationCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161872L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InternationalCertificatePrintSalutation");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6013)
  public static class InternationalCertificatePrintCurrentDateCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161873L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("InternationalCertificatePrintCurrentDate");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6014)
  public static class RadiographyFormat extends AbstractCode<Long> {
    public static final long ID = 161874L;
    private static final long serialVersionUID = 1L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("RadiographyFormat");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

  @Order(6015)
  @ClassId("12326d4c-0eba-437e-81bf-5f4ea343ccc4")
  public static class RadiographyEncodingCode extends AbstractCode<Long> {
    private static final long serialVersionUID = 1L;
    public static final long ID = 161875L;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Radiography-Encoding");
    }

    @Override
    public Long getId() {
      return ID;
    }
  }

}
