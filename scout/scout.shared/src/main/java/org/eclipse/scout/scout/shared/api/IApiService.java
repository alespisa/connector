package org.eclipse.scout.scout.shared.api;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.scout.shared.table.TableTablePageData;

@TunnelToServer
public interface IApiService extends IService {

  ApiFormData load(ApiFormData input);

  TableTablePageData load(TableTablePageData input);

}
