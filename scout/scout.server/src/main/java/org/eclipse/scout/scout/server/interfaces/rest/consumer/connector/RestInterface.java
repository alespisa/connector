package org.eclipse.scout.scout.server.interfaces.rest.consumer.connector;

import org.eclipse.scout.scout.server.interfaces.rest.consumer.AbstractRestInterface;
import org.eclipse.scout.scout.shared.interfaces.rest.consumer.connector.IRestInterface;

import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.util.Date;

public class RestInterface extends AbstractRestInterface implements IRestInterface {

  private String m_endpoint = "";
  private String m_jwtToken = null;
  private IRestInterfaceLogger m_logger = new RestInterfaceDatabaseLogger();

  public void setLogger(IRestInterfaceLogger logger) {
    m_logger = logger;
  }

  @Override
  public String printInServer(String text) {
    System.out.print(text);
    return text;
  }

  @Override
  public Long logRequest(String endpoint, Date evtRequest, String request) {

    //TOdo api
    //Safe request in Database...
    return null;
  }

  @Override
  protected void logRequest(StringBuilder sb, WriterInterceptorContext requestContext) {
    //TODO api
    //Log Request

  }

  @Override
  protected void logResponse(StringBuilder sb, ClientResponseContext responseContext) {
    //TODO api
    //Log Response

  }



}
