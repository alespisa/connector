package org.eclipse.scout.scout.client.parameter;


import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.bigdecimalfield.AbstractBigDecimalField;
import org.eclipse.scout.rt.client.ui.form.fields.booleanfield.AbstractBooleanField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.scout.client.parameter.ParameterForm.MainBox.GroupBox.IntegerValueField;
import org.eclipse.scout.scout.shared.code.ParameterTypeCodeType;
import org.eclipse.scout.scout.shared.parameter.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@FormData(value = ParameterFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ParameterForm extends AbstractForm {

  private static final Logger LOG = LoggerFactory.getLogger(ParameterForm.class);

  private String m_parameterName;
  private String m_parameterLabel;
  private Long m_parameterTypeUid;
  private Long m_parameterCode;
  private Object m_value;

  private boolean m_passwordUnlocked = false;

  public ParameterForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredCacheBounds() {
    return true;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Parameter");
  }

  @FormData
  public String getParameterName() {
    return m_parameterName;
  }

  @FormData
  public void setParameterName(String parameterName) {
    m_parameterName = parameterName;
  }

  @FormData
  public Long getParameterTypeUid() {
    return m_parameterTypeUid;
  }

  @FormData
  public void setParameterTypeUid(Long parameterTypeUid) {
    m_parameterTypeUid = parameterTypeUid;
    List<AbstractSmartParameter> params = BEANS.all(AbstractSmartParameter.class);
    for (AbstractSmartParameter param : params) {
      if (Objects.equals(param.getParameterCode(), getParameterCode())) {
        getSmartValueField().setCodeTypeClass(param.getConfiguredCodeType());
      }
    }
  }

  public Long getParameterCode() {
    return m_parameterCode;
  }

  public void setParameterCode(Long parameterCode) {
    m_parameterCode = parameterCode;
  }

  @FormData
  public Object getValue() {
    return m_value;
  }

  @FormData
  public void setValue(Object value) {
    m_value = value;
  }

  @FormData
  public String getParameterLabel() {
    return m_parameterLabel;
  }

  @FormData
  public void setParameterLabel(String m_parameterLabel) {
    this.m_parameterLabel = m_parameterLabel;
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public MainBox.CancelButton getCancelButton() {
    return getFieldByClass(MainBox.CancelButton.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public MainBox.OkButton getOkButton() {
    return getFieldByClass(MainBox.OkButton.class);
  }

  public MainBox.GroupBox.StringValueField getStringValueField() {
    return getFieldByClass(MainBox.GroupBox.StringValueField.class);
  }

  public MainBox.GroupBox.SmartValueField getSmartValueField() {
    return getFieldByClass(MainBox.GroupBox.SmartValueField.class);
  }

  public MainBox.GroupBox.PasswordField getPasswordField() {
    return getFieldByClass(MainBox.GroupBox.PasswordField.class);
  }

  public MainBox.GroupBox.ShowPasswordField getShowPasswordField() {
    return getFieldByClass(MainBox.GroupBox.ShowPasswordField.class);
  }

  public MainBox.GroupBox.NumberValueField getNumberValueField() {
    return getFieldByClass(MainBox.GroupBox.NumberValueField.class);
  }

  public IntegerValueField getIntegerValueField() {
    return getFieldByClass(IntegerValueField.class);
  }

  public MainBox.GroupBox.BooleanValueField getBooleanValueField() {
    return getFieldByClass(MainBox.GroupBox.BooleanValueField.class);
  }

  @Override
  public void importFormData(AbstractFormData source) throws ProcessingException {
    super.importFormData(source);

    ParameterFormData formData = ((ParameterFormData) source);
    Object value = formData.getValue();

    if (formData.getParameterTypeUid().equals(ParameterTypeCodeType.StringCode.ID)) {
      getStringValueField().setValue((String) ((ParameterFormData) source).getValue());
      getStringValueField().setLabel(getParameterLabel());
    }
    else if (formData.getParameterTypeUid().equals(ParameterTypeCodeType.NumberCode.ID)) {
      getStringValueField().setVisible(false);
      getNumberValueField().setVisible(true);
      getNumberValueField().setLabel(getParameterLabel());
      if (value != null) {
        getNumberValueField().setValue(new BigDecimal(((Number) value).doubleValue()));
      }
    }
    else if (formData.getParameterTypeUid().equals(ParameterTypeCodeType.IntegerCode.ID)) {
      getStringValueField().setVisible(false);
      getIntegerValueField().setVisible(true);
      getIntegerValueField().setLabel(getParameterLabel());
      if (value != null) {
        getIntegerValueField().setValue((Integer) value);
      }
    }
    else if (formData.getParameterTypeUid().equals(ParameterTypeCodeType.BooleanCode.ID)) {
      getStringValueField().setVisible(false);
      getBooleanValueField().setVisible(true);
      getBooleanValueField().setLabel(getParameterLabel());
      if (value != null) {
        getBooleanValueField().setValue((Boolean) value);
      }
    }
    else if (formData.getParameterTypeUid().equals(ParameterTypeCodeType.SmartCode.ID)) {
      getStringValueField().setVisible(false);
      getSmartValueField().setVisible(true);
      getSmartValueField().setLabel(getParameterLabel());
      if (value != null) {
        getSmartValueField().setValue((Long) value);
      }
    }
    else if(formData.getParameterTypeUid().equals(ParameterTypeCodeType.PasswordCode.ID)) {
      getStringValueField().setVisible(false);
      getPasswordField().setVisible(true);
      getShowPasswordField().setVisible(true);
      getPasswordField().setLabel(getParameterLabel());
      if(value != null) {
        getPasswordField().setValue((String) ((ParameterFormData) source).getValue());
        // Bug: hidden password not visible, setting focus to second field
        getShowPasswordField().requestFocus();
      }
    }
  }

  @Override
  public void exportFormData(AbstractFormData target) throws ProcessingException {
    super.exportFormData(target);
    if (getParameterTypeUid().equals(ParameterTypeCodeType.StringCode.ID)) {
      ((ParameterFormData) target).setValue(getStringValueField().getValue());
    }
    else if (getParameterTypeUid().equals(ParameterTypeCodeType.NumberCode.ID)) {
      ((ParameterFormData) target).setValue(getNumberValueField().getValue());
    }
    else if (getParameterTypeUid().equals(ParameterTypeCodeType.IntegerCode.ID)) {
      ((ParameterFormData) target).setValue(getIntegerValueField().getValue());
    }
    else if (getParameterTypeUid().equals(ParameterTypeCodeType.BooleanCode.ID)) {
      ((ParameterFormData) target).setValue(getBooleanValueField().getValue());
    }
    else if (getParameterTypeUid().equals(ParameterTypeCodeType.SmartCode.ID)) {
      ((ParameterFormData) target).setValue(getSmartValueField().getValue());
    }
    else if(getParameterTypeUid().equals(ParameterTypeCodeType.PasswordCode.ID)) {
      ((ParameterFormData) target).setValue(getPasswordField().getValue());
    }
  }

  @Order(10.0)
  @ClassId("e39b3b78-6862-4204-8bf6-2d8b0d148add")
  public class MainBox extends AbstractGroupBox {

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Order(10.0)
    @ClassId("20cb3d50-3163-4bfb-8a1b-8ecf28b4f2ce")
    public class GroupBox extends AbstractGroupBox {

      @Order(10.0)
      @ClassId("f06e1dfe-2f47-4840-b6ba-bd0eddacbb5d")
      public class StringValueField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Value");
        }

        @Override
        protected byte getConfiguredLabelPosition() {
          return LABEL_POSITION_TOP;
        }

        @Override
        protected boolean getConfiguredMultilineText() {
          return false;
        }

        @Override
        protected int getConfiguredGridH() {
          return 1;
        }
      }

      @Order(20.0)
      @ClassId("120b2c44-48bf-4fb0-8443-063ec5b1d844")
      public class NumberValueField extends AbstractBigDecimalField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Value");
        }

        @Override
        protected byte getConfiguredLabelPosition() {
          return LABEL_POSITION_TOP;
        }

        @Override
        protected int getConfiguredMaxFractionDigits() {
          return 5;
        }

        @Override
        protected int getConfiguredFractionDigits() {
          return 5;
        }

        @Override
        protected BigDecimal getConfiguredMaxValue() {
          return new BigDecimal("99999999999999.99999");
        }

        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }
      }

      @Order(30.0)
      @ClassId("6a42de8a-98d2-41a4-93ff-5ddac08d27e0")
      public class IntegerValueField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Value");
        }

        @Override
        protected byte getConfiguredLabelPosition() {
          return LABEL_POSITION_TOP;
        }

        @Override
        protected Integer getConfiguredMaxValue() {
          return Integer.MAX_VALUE;
        }

        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }
      }

      @Order(40.0)
      @ClassId("60c3bb08-7adb-48f3-a9dc-c56709a2919c")
      public class BooleanValueField extends AbstractBooleanField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Value");
        }

        @Override
        protected byte getConfiguredLabelPosition() {
          return LABEL_POSITION_TOP;
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }
      }

      @Order(50.0)
      public class SmartValueField extends AbstractSmartField<Long> {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Value");
        }

        @Override
        protected byte getConfiguredLabelPosition() {
          return LABEL_POSITION_TOP;
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }

        @Override
        protected Class<? extends ICodeType<?, Long>> getConfiguredCodeType() {
          return null;
        }
      }

      @Order(2000)
      public class PasswordField extends AbstractStringField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Value");
        }

        @Override
        protected byte getConfiguredLabelPosition() {
          return LABEL_POSITION_TOP;
        }

        @Override
        protected boolean getConfiguredMultilineText() {
          return false;
        }

        @Override
        protected boolean getConfiguredInputMasked() {
          return true;
        }

        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }

        @Override
        protected int getConfiguredGridH() {
          return 1;
        }
      }

      @Order(3000)
      public class ShowPasswordField extends AbstractBooleanField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ShowPassword");
        }

        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }

        @Override
        protected void execChangedValue() {
          getPasswordField().setInputMasked(!getValue());
        }

/*        @Override
        protected Boolean execValidateValue(Boolean rawValue) {
          if(rawValue && !m_passwordUnlocked) {
            ConfirmUserPasswordForm form = new ConfirmUserPasswordForm();
            form.startNew();
            form.waitFor();
            m_passwordUnlocked = form.wasValidationSuccessfull();
            return m_passwordUnlocked;
          }
          return rawValue;
        }*/
      }
    }

    @Order(20.0)
    @ClassId("3a9f4ae7-d66f-4274-b310-b15e65192107")
    public class OkButton extends AbstractOkButton {
    }

    @Order(30.0)
    @ClassId("8d63fbdc-e42b-43f9-a44c-e6005ac9d2ed")
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IParameterService service = BEANS.get(IParameterService.class);
      ParameterFormData formData = new ParameterFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateParameterPermission());

      if (getParameterCode() == ParameterCodeType.LastHourOfDayParameterCode.ID || getParameterCode() == ParameterCodeType.FirstHourOfDayParameterCode.ID) {
        getIntegerValueField().setMaxValue(24);
        getIntegerValueField().setMinValue(0);
      }

      // parameters that are directly connected to a code cannot have null-values because they need a label
      getNumberValueField().setMandatory(service.getParameter(getParameterName()) instanceof AbstractCodeNumberedValueParameter);
    }

    @Override
    protected void execStore() throws ProcessingException {
      IParameterService service = BEANS.get(IParameterService.class);
      ParameterFormData formData = new ParameterFormData();
      exportFormData(formData);
      formData = service.store(formData);
    }
  }
}
