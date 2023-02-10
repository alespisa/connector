package org.eclipse.scout.scout.server.interfaces.rest.consumer.connector;

import java.util.Date;

public class RestInterfaceDatabaseLogger implements IRestInterfaceLogger{


  @Override
  public Long logRequest(String endpoint, Date evtRequest, String request) {
    return null;
  }

  @Override
  public void logResponse(String name, String responseStatus, String responseMessage) {

  }

  @Override
  public void logResponse(String name, Date evtResponse, String response, String responseStatus, String statusMessage) {

  }
}
