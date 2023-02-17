package org.eclipse.scout.scout.server.parameter;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

public abstract class AbstractTablePageService implements IService {

  protected <T extends AbstractTablePageData> T checkLimited(T pageData) {
    //TODO 1000 is the max row count value;
    Number limit = 1000;
    if (limit != null && limit.intValue() > 0) {
      if (pageData.getRowCount() > limit.intValue()) {
        pageData.setLimitedResult(true);
        pageData.removeRow(pageData.getRowCount()-1);
      }
    }
    return pageData;
  }

}
