package org.eclipse.scout.scout.shared.interfaces.rest.consumer.connector;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

import java.util.Date;

@TunnelToServer
public interface IRestInterface extends IService {

  String printInServer(String text);


  //Todo api
  Long logRequest(String endpoint, Date evtRequest, String request);

}
