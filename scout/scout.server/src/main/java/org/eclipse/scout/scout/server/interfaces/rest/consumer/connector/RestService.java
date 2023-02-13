package org.eclipse.scout.scout.server.interfaces.rest.consumer.connector;

import org.eclipse.scout.scout.shared.interfaces.rest.consumer.connector.IRestService;

import java.util.Date;

public class RestService implements IRestService {


  @Override
  public Long logRequest(String endpoint, Date evtRequest, String request) {
    return null;
  }

  @Override
  public void logResponse(Long isAbvLogNr, Date evtResponse, String response, String statusString, String statusMessage) {

  }

  @Override
  public void logResponse(Long isAbvLogNr, String responseStatus, String responseMessage) {

  }
}
