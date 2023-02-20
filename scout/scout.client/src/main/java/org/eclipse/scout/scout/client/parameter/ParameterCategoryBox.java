package org.eclipse.scout.scout.client.parameter;

import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.scout.shared.Icons;
import org.eclipse.scout.scout.shared.code.ParameterTypeCodeType;

import java.util.HashSet;
import java.util.Set;

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

      @Override
      protected Class<? extends IMenu> getConfiguredDefaultMenu() {
        return EditParameterMenu.class;
      }

      public CodeColumn getCodeColumn() {
        return getColumnSet().getColumnByClass(CodeColumn.class);
      }

      public NameColumn getNameColumn() {
        return getColumnSet().getColumnByClass(NameColumn.class);
      }

      public TypeColumn getTypeColumn() {
        return getColumnSet().getColumnByClass(TypeColumn.class);
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

      @Order(2000)
      public class TypeColumn extends AbstractSmartColumn<Long> {
        @Override
        protected Class<? extends ICodeType<?, Long>> getConfiguredCodeType() {
          return ParameterTypeCodeType.class;
        }
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

      public class EditParameterMenu extends AbstractMenu {

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("EditParameter_");
        }

        @Override
        protected String getConfiguredIconId() {
          return Icons.DiagramArea;
        }

        @Override
        protected Set<? extends IMenuType> getConfiguredMenuTypes() {
          return new HashSet<IMenuType>();
        }

        @Override
        protected void execAction() throws ProcessingException {
          ParameterForm form = new ParameterForm();
          form.setParameterName(getTable().getNameColumn().getSelectedValue());
          form.setParameterTypeUid(getTable().getTypeColumn().getSelectedValue());
          form.setParameterLabel(getTable().getLabelColumn().getSelectedValue());
          form.setParameterCode(getTable().getCodeColumn().getSelectedValue());
          form.startModify();
          form.waitFor();
          m_parameterForm.loadData(m_parameterForm.getSearchField().getValue());
        }
      }
    }
  }
}
