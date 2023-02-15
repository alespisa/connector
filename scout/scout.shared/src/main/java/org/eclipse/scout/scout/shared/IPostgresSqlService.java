package org.eclipse.scout.scout.shared;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IPostgresSqlService extends IService {

  String getDatabaseName();

}
