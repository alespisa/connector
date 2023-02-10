package org.eclipse.scout.scout.server.interfaces.rest.consumer.connector;

import java.util.Date;

public interface IRestInterfaceLogger {

  Long logRequest(String endpoint, Date evtRequest, String request);

  void logResponse(String name, String responseStatus, String responseMessage);

  void logResponse(String name, Date evtResponse, String response, String responseStatus, String statusMessage);

}
