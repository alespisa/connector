package org.eclipse.scout.scout.server.interfaces.rest.consumer;

import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.util.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.util.List;

public abstract class AbstractRestInterface {

  public static Logger LOG = LoggerFactory.getLogger(AbstractRestInterface.class);

  protected abstract void logRequest(StringBuilder sb, WriterInterceptorContext requestContext);

  protected abstract void logResponse(StringBuilder sb, ClientResponseContext responseContext);

  protected Invocation.Builder buildRequest(String BaseUrl, List<String> paths, MultivaluedMap<String, Object> headers){

    if (BaseUrl.isEmpty()){
      throw new ProcessingException("the baseUrl musst not be null");
    }
    return buildRequest(BaseUrl, paths, headers, null);
  }

  protected Invocation.Builder buildRequest(String baseUrl, List<String>  paths, MultivaluedMap<String, Object> headers, List<NVPair> queryParams) {
    WebTarget target = buildWebTarget(baseUrl, paths, headers, queryParams);
    return buildRequestByWebTargetAndHeaders(target, headers);
  }

  protected Invocation.Builder buildRequestByWebTargetAndHeaders(WebTarget target, MultivaluedMap<String, Object> headers) {

    return target
      .request()
      .headers(headers);
  }

  protected WebTarget buildWebTarget(String baseUrl, List<String> paths, MultivaluedMap<String, Object> headers, List<NVPair> queryParams) {
    ClientBuilder clientBuilder = ClientBuilder.newBuilder();

    Client client = clientBuilder.build();
/*    client.register(new EntityLoggingFilter());*/

    WebTarget target = client.target(baseUrl);

    Assertions.assertNotNull(paths, "'paths' should not be <null>");
    Assertions.assertNotEquals(paths.size(), 0, "'paths' should not be empty");
    for (String path : paths) {
      target = target.path(path);
    }

    if (queryParams != null) {
      for (NVPair nvPair : queryParams) {
        target = target.queryParam(nvPair.getName(), nvPair.getValue());
      }
    }

    return target;
  }

}
