package com.timpo.dwbarebones.resources;

import com.timpo.dwbarebones.models.Model;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class BarebonesResource {

  @GET
  public Response index() {
    return Response.ok(new Model("some-id", Integer.MAX_VALUE)).build();
  }
}
