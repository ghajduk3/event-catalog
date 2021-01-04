package si.fri.rso.event_catalog.api.v1.resources;


import si.fri.rso.event_catalog.services.config.AppProperties;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/healthy")
@RequestScoped
public class HealthResource {

    @Inject
    private AppProperties appProperties;

    @POST
    @Path("/break")
    public Response breakResource(){
        appProperties.setBroken(true);

        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/recover")
    public Response recoverResource(){
        appProperties.setBroken(false);
        return Response.status(Response.Status.OK).build();
    }
}
