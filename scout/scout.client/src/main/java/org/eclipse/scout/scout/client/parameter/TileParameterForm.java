package org.eclipse.scout.scout.client.parameter;

import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.util.TriState;

public class TileParameterForm extends AbstractForm {

  public MainBox.GroupBox getGroupBox() {
    return getFieldByClass(MainBox.GroupBox.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  @Order(1000)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected TriState getConfiguredScrollable() {
      return TriState.FALSE;
    }

    @Order(1000)
    public class GroupBox extends AbstractGroupBox {

      @Override
      protected TriState getConfiguredScrollable() {
        return TriState.TRUE;
      }

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }

      @Override
      protected int getConfiguredGridColumnCount() {
        return 2;
      }
    }
  }

}
