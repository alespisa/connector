package org.eclipse.scout.scout.shared.api;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IApiService extends IService {

  ApiFormData load(ApiFormData input);

}
