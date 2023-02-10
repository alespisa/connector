package org.eclipse.scout.scout.server.interfaces.rest.consumer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.eclipse.scout.rt.rest.client.IRestClientConfigFactory;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.core.Configuration;

public class ConnectorRestClientConfig implements IRestClientConfigFactory {
/*  @Override
  public Configuration createClientConfig() {
    return null;
  }*/

  @Override
  public Configuration createClientConfig() {
    final JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return new ClientConfig(jacksonJsonProvider);
  }

}
