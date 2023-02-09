package org.eclipse.scout.scout.client.parameter;

import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.TriState;
import org.eclipse.scout.scout.shared.Icons;

public class TileParameterForm extends AbstractForm {

  public MainBox.GroupBox getGroupBox() {
    return getFieldByClass(MainBox.GroupBox.class);
  }

  public MainBox.SearchBox.SearchField getSearchField() {
    return getFieldByClass(MainBox.SearchBox.SearchField.class);
  }

  public MainBox.SearchBox.IconField getIconField() {
    return getFieldByClass(MainBox.SearchBox.IconField.class);
  }

  public MainBox.SearchBox getSearchBox() {
    return getFieldByClass(MainBox.SearchBox.class);
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

    @Order(0)
    public class SearchBox extends AbstractGroupBox {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Search");
      }

      @Override
      protected int getConfiguredGridColumnCount() {
        return 10;
      }

      @Order(0)
      public class IconField extends AbstractImageField {

        @Override
        protected void execInitField() {
          setImageId(Icons.Search);
        }

        @Override
        protected boolean getConfiguredAutoFit() {
          return true;
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected double getConfiguredGridWeightX() {
          return 0;
        }

        @Override
        protected int getConfiguredVerticalAlignment() {
          return 0;
        }

        @Override
        protected int getConfiguredHorizontalAlignment() {
          return 1;
        }

        @Override
        protected int getConfiguredWidthInPixel() {
          return 65;
        }
      }

      @Order(1000)
      public class SearchField extends AbstractStringField {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("SearchAfterParameter");
        }

        @Override
        protected boolean getConfiguredFillHorizontal() {
          return true;
        }

        @Override
        protected byte getConfiguredLabelPosition() {
          return LABEL_POSITION_ON_FIELD;
        }

      }
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
