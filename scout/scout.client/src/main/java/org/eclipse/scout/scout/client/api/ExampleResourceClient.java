package org.eclipse.scout.scout.client.api;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.rest.client.IRestResourceClient;

public class ExampleResourceClient implements IRestResourceClient {

  protected static final String RESOURCE_PATH = "example";

  protected ExampleRestClientHelper helper(){
    return BEANS.get(ExampleRestClientHelper.class);
  }


}
