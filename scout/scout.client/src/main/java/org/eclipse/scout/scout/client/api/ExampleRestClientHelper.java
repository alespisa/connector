package org.eclipse.scout.scout.client.api;

import org.apache.http.client.config.CookieSpecs;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.rest.client.AbstractRestClientHelper;
import org.eclipse.scout.rt.rest.client.RestClientProperties;
import org.eclipse.scout.rt.rest.error.ErrorDo;
import org.eclipse.scout.rt.rest.error.ErrorResponse;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class ExampleRestClientHelper extends AbstractRestClientHelper {
  @Override
  protected String getBaseUri() {
    return "http://localhost:8080/";
  }

  @Override
  protected void configureClientBuilder(ClientBuilder clientBuilder) {
    super.configureClientBuilder(clientBuilder);
    clientBuilder.property(RestClientProperties.COOKIE_SPEC, CookieSpecs.STANDARD);
  }

  @Override
  protected RuntimeException transformException(RuntimeException e, Response response) {
    if (response != null && response.hasEntity()){
      ErrorDo error = response.readEntity(ErrorResponse.class).getError();
      throw new VetoException(error.getMessage())
        .withTitle(error.getTitle());
    }
    return e;
  }
}
