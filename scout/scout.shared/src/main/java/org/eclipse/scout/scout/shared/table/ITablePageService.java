package org.eclipse.scout.scout.shared.table;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface ITablePageService extends IService {

  TableTablePageData getTablePageData() throws Exception;

}
