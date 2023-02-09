package org.eclipse.scout.scout.server.rest;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.rest.IRestResource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("example")
public class RestResource implements IRestResource {

/*  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public ExampleEntityDo getExamlpeEntity(@PathParam("id") String id) {
    return BEANS.get(ExampleEntityDo.class)
      .withName("example-" + id)
      .withValues(1);
  } */

/*  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public ExampleEntityDo getExamlpeEntity(@PathParam("id") String id) {
    return BEANS.get(ExampleEntityDo.class);

  }*/

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public ExampleEntityDo getApiStringMethod(@PathParam("id") String id){
    return BEANS.get(ExampleEntityDo.class)
      .withTest("djds");
  }

}
