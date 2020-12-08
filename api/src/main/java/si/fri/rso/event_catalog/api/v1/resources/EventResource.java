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
import java.util.List;


import com.sun.org.apache.xpath.internal.operations.Bool;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import si.fri.rso.event_catalog.models.dtos.EventDto;
import si.fri.rso.event_catalog.services.db.EventsDbBean;


@Path("/catalog/events")
@RequestScoped
public class EventResource {

    @Inject
    private EventsDbBean eventBean;

    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @POST
    public Response postEvent(EventDto event) throws Exception {

        if((event.getEventStart() == null || event.getEventEnd() == null || event.getAddress()==null || event.getDescription() == null)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
           event = eventBean.createEvent(event);
        }
        return Response.status(200).entity(event).build();
    }
    @Produces({MediaType.APPLICATION_JSON})
    @GET
    public Response getEvent(){
        List<EventDto> events = eventBean.findAll();
        return Response.status(200).entity(events).build();

    }

    @Produces({MediaType.APPLICATION_JSON})
    @GET
    @Path("/{eventId}")
    public Response getEvent(@PathParam("eventId") Integer eventId){
        EventDto event = eventBean.findById(eventId);
        return  Response.status(200).entity(event).build();

    }

    @DELETE
    @Path("/{eventId}")
    public Response deleteEvent(@PathParam("eventId") Integer eventId){
        Boolean deleted = eventBean.deleteById(eventId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Produces({MediaType.APPLICATION_JSON})
    @GET
    @Path("/{eventId}")
    public Response updateEvent(@PathParam("eventId") Integer eventId,EventDto event){
        EventDto dto = eventBean.putEvent(eventId,event);
        return  Response.status(200).entity(event).build();
    }









}
