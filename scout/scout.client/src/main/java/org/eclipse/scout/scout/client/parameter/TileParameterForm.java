package org.eclipse.scout.scout.client.parameter;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.TriState;
import org.eclipse.scout.scout.shared.Icons;
import org.eclipse.scout.scout.shared.parameter.TileParameterFormData;
import org.eclipse.scout.scout.shared.table.ITablePageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@FormData(value = TileParameterFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class TileParameterForm extends AbstractForm {

  private static final Logger LOG = LoggerFactory.getLogger(TileParameterForm.class);

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Parameter");
  }

  @Override
  protected void execInitForm() {
    loadData("");
  }

  public void loadData(String searchTerm) {
    for(IFormField field : getGroupBox().getFields()) {
      getGroupBox().removeField(field);
    }
    Map<String, Object[][]> formData = BEANS.get(ITablePageService.class).getParameterPageData(searchTerm);
    if(formData.size() < 1) {
      getSearchField().addErrorStatus(TEXTS.get("NoResultsFor0", getSearchField().getValue()));
    } else {
      getSearchField().clearErrorStatus();
      getSearchField().setTooltipText(countSearchResluts(formData) + " " + TEXTS.get("ResultsFound"));
    }
    for(Map.Entry<String, Object[][]> entry : formData.entrySet()) {
      ParameterCategoryBox box = new ParameterCategoryBox(this, entry.getKey());
      for(int i = 0; i < entry.getValue().length; i++) {
        ITableRow row = box.getParameterTableField().getTable().addRow(box.getParameterTableField().getTable().createRow());
        box.getParameterTableField().getTable().getNameColumn().setValue(row, (String) entry.getValue()[i][0]);
        box.getParameterTableField().getTable().getLabelColumn().setValue(row, (String) entry.getValue()[i][1]);
        box.getParameterTableField().getTable().getTypeColumn().setValue(row, (long) entry.getValue()[i][2]);
        box.getParameterTableField().getTable().getValueColumn().setValue(row, (String) entry.getValue()[i][3]);
        box.getParameterTableField().getTable().getCodeColumn().setValue(row, (long) entry.getValue()[i][4]);

      }
      getGroupBox().addField(box);
    }
  }

  private int countSearchResluts(Map<String, Object[][]> data) {
    int results = 0;
    for(Map.Entry<String, Object[][]> entry : data.entrySet()) {
      results += entry.getValue().length;
    }
    return results;
  }

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

        @Override
        protected void execChangedValue() {
          loadData(getValue());
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
