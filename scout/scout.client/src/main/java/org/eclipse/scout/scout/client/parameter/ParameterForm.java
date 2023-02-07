package org.eclipse.scout.scout.client.parameter;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.root.AbstractContextMenu;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.client.ui.label.AbstractLabel;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.scout.client.parameter.codetype.DatatypeCodeType;
import org.eclipse.scout.scout.shared.parameter.ParameterFormData;

import javax.swing.*;

@FormData(value = ParameterFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ParameterForm extends AbstractForm {

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return Boolean.TRUE;
  }

  @Override
  protected int getConfiguredModalityHint() {
    return MODALITY_HINT_MODELESS;
  }

  @Override
  protected String getConfiguredIconId() {
    return AbstractIcons.World;
  }

  public MainBox getMainBox(){
    return getFieldByClass(MainBox.class);
  }

  public MainBox.TabBox.PathField getPathField(){
    return getFieldByClass(MainBox.TabBox.PathField.class);
  }

  public class MainBox extends AbstractGroupBox{

    @Order(40)
    public class SubmitButton extends AbstractButton {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Submit");
      }
    }

    @Order(10)
    public class TabBox extends AbstractGroupBox {

      @Override
      protected boolean getConfiguredVisible() {
        return Boolean.TRUE;
      }

      @Order(30)
      public class PathField extends AbstractStringField {

        @Override
        public String getLabel() {
          return TEXTS.get("Path");
        }
      }

      public class DataTapeSmartField extends AbstractSmartField{

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Datatype");
        }

        @Override
        protected Class<? extends ICodeType> getConfiguredCodeType() {
          return DatatypeCodeType.class;
        }
      }

    }
  }
}
