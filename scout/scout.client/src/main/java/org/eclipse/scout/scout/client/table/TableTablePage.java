package org.eclipse.scout.scout.client.table;

import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.scout.shared.Icons;
import org.eclipse.scout.scout.shared.table.TableTablePageData;

@PageData(value = TableTablePageData.class)
public class TableTablePage extends AbstractPageWithTable {

  @Override
  protected void execLoadData(SearchFilter filter) {
    super.execLoadData(filter);
  }

  public class Table extends AbstractTable{
    @Override
    protected String getConfiguredDefaultIconId() {
      return Icons.AppLogo;
    }

    public AnimalNrColumn getAnimalNrColumn(){
      return getColumnSet().getColumnByClass(AnimalNrColumn.class);
    }

    @Order(10.0)
    public class AnimalNrColumn extends AbstractLongColumn {
      @Override
      protected boolean getConfiguredDisplayable() {
        return true;
      }

      @Override
      protected boolean getConfiguredPrimaryKey() {
        return true;
      }
    }
  }

}
