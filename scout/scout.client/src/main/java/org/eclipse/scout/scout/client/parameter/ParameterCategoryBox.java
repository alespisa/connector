package org.eclipse.scout.scout.client.parameter;

import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.platform.Order;

public class ParameterCategoryBox extends AbstractGroupBox {

  private String m_labelText;
  private TileParameterForm m_parameterForm;

  public ParameterCategoryBox(TileParameterForm form, String categoryName) {
    m_labelText = categoryName;
    m_parameterForm = form;
    setLabel(m_labelText);
  }

  @Override
  protected int getConfiguredGridW() {
    return 1;
  }

  public ParameterTableField getParameterTableField() {
    return getFieldByClass(ParameterTableField.class);
  }

  @Order(1000)
  public class ParameterTableField extends AbstractTableField<ParameterTableField.Table> {

    @Override
    protected int getConfiguredGridH() {
      return 4;
    }

    @Override
    protected boolean getConfiguredLabelVisible() {
      return false;
    }

    public class Table extends AbstractTable {

      @Override
      protected boolean getConfiguredHeaderVisible() {
        return false;
      }

      @Override
      protected boolean getConfiguredAutoResizeColumns() {
        return true;
      }

      @Override
      protected boolean getConfiguredMultiSelect() {
        return false;
      }

      public CodeColumn getCodeColumn() {
        return getColumnSet().getColumnByClass(CodeColumn.class);
      }

      public NameColumn getNameColumn() {
        return getColumnSet().getColumnByClass(NameColumn.class);
      }

      public ValueColumn getValueColumn() {
        return getColumnSet().getColumnByClass(ValueColumn.class);
      }

      public LabelColumn getLabelColumn() {
        return getColumnSet().getColumnByClass(LabelColumn.class);
      }

      @Order(0)
      public class NameColumn extends AbstractStringColumn {
        @Override
        protected boolean getConfiguredDisplayable() {
          return false;
        }
      }

      @Order(1000)
      public class LabelColumn extends AbstractStringColumn {
      }

      @Order(2500)
      public class CodeColumn extends AbstractLongColumn {
        @Override
        protected boolean getConfiguredDisplayable() {
          return false;
        }
      }

      @Order(3000)
      public class ValueColumn extends AbstractStringColumn {
      }
    }
  }
}
