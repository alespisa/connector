package org.eclipse.scout.scout.shared.interfaces.rest.consumer.connector;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

import java.util.Date;

@TunnelToServer
public interface IRestService extends IService {

  Long logRequest(String endpoint, Date evtRequest, String request);

  public void logResponse(Long isAbvLogNr, Date evtResponse, String response, String statusString, String statusMessage);

  void logResponse(Long isAbvLogNr, String responseStatus, String responseMessage);

}
