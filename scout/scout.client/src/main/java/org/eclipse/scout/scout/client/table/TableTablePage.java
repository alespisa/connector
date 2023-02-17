package org.eclipse.scout.scout.client.table;

import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.scout.shared.Icons;
import org.eclipse.scout.scout.shared.table.ITablePageService;
import org.eclipse.scout.scout.shared.table.TableTablePageData;

@PageData(value = TableTablePageData.class)
public class TableTablePage extends AbstractPageWithTable {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("TablePage");
  }

  @Override
  protected void execLoadData(SearchFilter filter) throws ProcessingException {
    TableTablePageData pageData = null;
    try {
      BEANS.get(ITablePageService.class).getMaxRowCount();
      pageData = BEANS.get(ITablePageService.class).getTablePageData();
    } catch (Exception e) {
      e.printStackTrace();
    }
    importPageData(pageData);
  }

  public class Table extends AbstractTable{

    @Override
    protected String getConfiguredDefaultIconId() {
      return Icons.AppLogo;
    }

    public IdNrColumn getIdNrColumn(){
      return getColumnSet().getColumnByClass(IdNrColumn.class);
    }

    public NameColumn getNameColumn(){
      return getColumnSet().getColumnByClass(NameColumn.class);
    }

    public SurnameColumn getSurnameColumn(){
      return getColumnSet().getColumnByClass(SurnameColumn.class);
    }
    

    @Order(10.0)
    public class IdNrColumn extends AbstractLongColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("ID");
      }

      @Override
      protected boolean getConfiguredAutoOptimizeWidth() {
        return Boolean.TRUE;
      }

      @Override
      protected boolean getConfiguredDisplayable() {
        return true;
      }

      @Override
      protected boolean getConfiguredPrimaryKey() {
        return true;
      }
    }

    @Order(20.0)
    public class NameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Name");
      }

      @Override
      protected boolean getConfiguredAutoOptimizeWidth() {
        return Boolean.TRUE;
      }

      @Override
      protected boolean getConfiguredDisplayable() {
        return true;
      }

      @Override
      protected boolean getConfiguredPrimaryKey() {
        return true;
      }
    }

    @Order(30.0)
    public class SurnameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Surname");
      }

      @Override
      protected boolean getConfiguredAutoOptimizeWidth() {
        return Boolean.TRUE;
      }

      @Override
      protected boolean getConfiguredDisplayable() {
        return true;
      }

      @Override
      protected boolean getConfiguredPrimaryKey() {
        return true;
      }
    }

    @Order(40.0)
    public class AgeColumn extends AbstractLongColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Age");
      }

      @Override
      protected boolean getConfiguredAutoOptimizeWidth() {
        return Boolean.TRUE;
      }

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
