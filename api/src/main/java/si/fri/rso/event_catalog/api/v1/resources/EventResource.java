package si.fri.rso.event_catalog.api.v1.resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.*;


import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import si.fri.rso.event_catalog.models.dtos.EventDto;
import si.fri.rso.event_catalog.services.db.EventsDbBean;


@Path("/catalog/events")
@RequestScoped
public class EventResource {

    @Inject
    private EventsDbBean eventBean;

    @GET
    public Response getAllEvents(){

     return Response
             .status(200)
             .entity("Eventi")
             .build();
    }
    @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
    @POST
    public Response postEvent(EventDto instance){
        EventDto dt = eventBean.createNew(instance);
        return Response.
                status(200)
                .entity(dt)
                .build();
    }

    @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
    @GET
    @Path("/{id}")
    public Response getEvent(@PathParam("id") Integer id){
        EventDto dt = eventBean.getById(id);
        return Response.
                status(200)
                .entity(dt)
                .build();
    }

}
