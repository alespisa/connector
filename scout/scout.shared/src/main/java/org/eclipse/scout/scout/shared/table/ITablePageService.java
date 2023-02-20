package org.eclipse.scout.scout.shared.table;

import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

import java.util.Map;

@TunnelToServer
public interface ITablePageService extends IService {

  TableTablePageData getTablePageData() throws Exception;

  long getMaxRowCount();

  Map<String, Object[][]> getParameterPageData(String searchTerm) throws ProcessingException;

}
