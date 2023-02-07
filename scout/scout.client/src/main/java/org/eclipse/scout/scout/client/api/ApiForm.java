package org.eclipse.scout.scout.client.api;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.scout.shared.api.ApiFormData;
import org.eclipse.scout.scout.shared.api.IApiService;

@FormData(value = ApiFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ApiForm extends AbstractForm {

  public ApiForm(){
    setHandler(new ViewHandler());
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("api");
  }

  @Override
  protected String getConfiguredIconId() {
    return AbstractIcons.Star;
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public MainBox.MessageBox getMessageBox() {
    return getFieldByClass(MainBox.MessageBox.class);
  }

  public MainBox.SubmitButton getSubmitButton(){
    return getFieldByClass(MainBox.SubmitButton.class);
  }

  public MainBox.MessageValueStringField getMessageValueStringField(){
    return getFieldByClass(MainBox.MessageValueStringField.class);
  }
@Order(10)
  public class MainBox extends AbstractGroupBox{

    @Override
    protected boolean getConfiguredVisible() {
      return Boolean.TRUE;
    }

    @Override
    protected boolean getConfiguredEnabled() {
      return Boolean.TRUE;
    }

    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("api");
    }

    @Order(10)
    public class MessageBox extends AbstractStringField{

      @Override
      protected boolean getConfiguredVisible() {
        return Boolean.TRUE;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("api");
      }

    }

    public class MessageValueStringField extends AbstractStringField{

      @Override
      protected boolean getConfiguredEnabled() {
        return Boolean.FALSE;
      }

      @Override
      protected boolean getConfiguredVisible() {
        return Boolean.TRUE;
      }
    }

    @Order(20)
    public class SubmitButton extends AbstractButton {
      @Override
      public String getLabel() {
        return TEXTS.get("api");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return Boolean.TRUE;
      }

      @Override
      protected void execClickAction() {
          IApiService service = BEANS.get(IApiService.class);
          ApiFormData formData = new ApiFormData();
          exportFormData(formData);
          formData = service.load(formData);
          importFormData(formData);

      }
    }

  }

  public class ViewHandler extends AbstractFormHandler {

    /*
    @Override
    protected void execLoad() {
      IApiService service = BEANS.get(IApiService.class);
      ApiFormData formData = new ApiFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
    }
    */
  }

}
