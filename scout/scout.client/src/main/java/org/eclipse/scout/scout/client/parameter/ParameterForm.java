package org.eclipse.scout.scout.client.parameter;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.client.ui.label.AbstractLabel;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.scout.shared.parameter.ParameterFormData;

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

  public MainBox getMainBox(){return getFieldByClass(MainBox.class);}

  public MainBox.TabBox getTabBox(){return getFieldByClass(MainBox.TabBox.class);}

  public MainBox.TabBox.PathField getPathField(){return getFieldByClass(MainBox.TabBox.PathField.class);}

  public class MainBox extends AbstractGroupBox{

  public class TabBox extends AbstractTabBox{

    public class PathLabel extends AbstractLabel{

    }
    public class PathField extends AbstractStringField{

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Parameter");
      }
    }

  }

}

}
