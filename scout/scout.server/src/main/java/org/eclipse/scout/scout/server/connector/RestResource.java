package org.eclipse.scout.scout.server.connector;

import org.eclipse.scout.rt.rest.IRestResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/example")
public class RestResource implements IRestResource {

  @GET
  public String sayHallo(){
    return "Hellow";
  }

/*  @GET
  @Path("/")
  @Produces(MediaType.TEXT_PLAIN)
  public Response getapimethod(){
    System.out.print("id");
    return Response.ok("Okay").build();
  }*/

/*  @GET
  @Path("{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public ExampleEntityDo getApiStringMethod(@PathParam("id") String id){
    System.out.print("id");
    return BEANS.get(ExampleEntityDo.class)
      .withTest("id");
  }*/

}
