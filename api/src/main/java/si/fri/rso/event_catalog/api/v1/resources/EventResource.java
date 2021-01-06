package si.fri.rso.event_catalog.api.v1.resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.*;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;


//import com.sun.org.apache.xpath.internal.operations.Bool;
import com.kumuluz.ee.logs.cdi.Log;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.eclipse.microprofile.metrics.Meter;
import org.eclipse.microprofile.metrics.ConcurrentGauge;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Metric;



import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import si.fri.rso.event_catalog.models.dtos.EventDto;
import si.fri.rso.event_catalog.models.dtos.EventSummary;
import si.fri.rso.event_catalog.services.db.EventsDbBean;
import si.fri.rso.event_catalog.services.exceptions.InternalServerException;
import si.fri.rso.event_catalog.services.exceptions.InvalidEntityException;
import si.fri.rso.event_catalog.services.exceptions.InvalidParameterException;

@Log
@Path("/catalog/events")
@RequestScoped
public class EventResource {

    @Inject
    private EventsDbBean eventBean;

    @Inject
    @Metric(name = "events_counter")
    private ConcurrentGauge eventsCounter;

    @Inject
    @Metric(name = "events_adding_meter")
    private Meter addMeter;


    @Operation(summary = "Add a cleaning event", tags = {"Event"},
            description = "Add a new cleaning event",
            responses = {
                    @ApiResponse(
                            description = "Cleaning event successfully added",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Validation error",
                            responseCode = "403"
                    )
            }
    )
    @Produces({MediaType.APPLICATION_JSON})
    @POST
    public Response postEvent(
            @Parameter(description = "Cleaning event start date.", required = true,example = "2020-08-16")
            @FormDataParam("eventStart") String eventStart,
            @Parameter(description = "Cleaning event end date.", required = true,example = "2020-08-17")
            @FormDataParam("eventEnd") String eventEnd,
            @Parameter(description = "Location of the cleaning event", required = true,example = "Ulica Rista Lekica I-29,Bar,Montenegro")
            @FormDataParam("address") String address,
            @Parameter(description = "Short description of the event, number of people reqiured, general information", required = true)
            @FormDataParam("description") String description,
            @Parameter(description = "Image of the location that has to be cleaned", required = true)
            @FormDataParam("fajl") InputStream uploadedInputStream,
            @FormDataParam("fajl") FormDataContentDisposition fileMetadata
    ) throws Exception {

        System.out.println(fileMetadata);
        SimpleDateFormat dat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dat.parse(eventEnd));
        byte[] bytes = new byte[0];
        try (uploadedInputStream) {
            bytes = uploadedInputStream.readAllBytes();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        EventDto event = new EventDto(null,dat.parse(eventStart),dat.parse(eventEnd),address,description, Base64.getEncoder().encodeToString(bytes),Long.valueOf(bytes.length),null);
        System.out.println(Base64.getEncoder().encodeToString(bytes));

        if((event.getEventStart() == null || event.getEventEnd() == null || event.getlocationId()==null || event.getDescription() == null)){
            return Response.status(403).build();
        }
        else {
            event = eventBean.createEvent(event);
        }
        addMeter.mark();
        eventsCounter.inc();
        return Response.status(201).entity(event).build();
    }





    @Operation(summary = "Get all cleaning events", tags = {"Event"},
            description = "Returns a list of all cleaning events",
            responses = {
                    @ApiResponse(
                            description = "Cleaning data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EventDto.class))
                            )
                    ),
                    @ApiResponse(
                            description = "Server error",
                            responseCode = "500"
                    )
            }
    )
    @Produces({MediaType.APPLICATION_JSON})
    @GET
    @Metered(name = "requests")
    public Response getEvents(){
//        List<EventDto> events = eventBean.findAll();
        List<EventDto> events = eventBean.findAll();
        return Response.status(200).entity(events).build();

    }

    @Operation(summary = "Get cleaning event by id", tags = {"Event"},
            description = "Returns a cleaning event with specific id if exists",
            responses = {
                    @ApiResponse(
                            description = "Cleaning event data transfer model",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EventDto.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Entity not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Server error",
                            responseCode = "500"
                    )
            }
    )
    @Produces({MediaType.APPLICATION_JSON})
    @GET
    @Path("/{eventId}")
    public Response getEvent(@Parameter(description = "Cleaning event ID.", required = true)
            @PathParam("eventId") Integer eventId) throws InvalidParameterException, InternalServerException {
        EventSummary event;
        try {
            event = eventBean.findById(eventId);
        }catch (EntityNotFoundException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }catch (InternalServerException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return  Response.status(Response.Status.OK).entity(event).build();

    }



    @Operation(summary = "Delete cleaning event", tags = {"Event"},
            description = "Deletes a cleaning event with specific id if exists",
            responses = {
                    @ApiResponse(
                            description = "Cleaning event successfully deleted",
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            description = "Entity not found",
                            responseCode = "404"
                    )
            }
    )
    @DELETE
    @Path("/{eventId}")
    public Response deleteEvent(@Parameter(description = "Cleaning event ID.", required = true)
            @PathParam("eventId") Integer eventId) throws InvalidParameterException, InternalServerException {
        Boolean deleted = eventBean.deleteById(eventId);

        if (deleted) {
            eventsCounter.dec();
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Update cleaning event", tags = {"Event"},
            description = "Updates a cleaning event with specific id if exists",
            responses = {
                    @ApiResponse(
                            description = "Cleaning event successfully updated",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Entity not found",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Server error",
                            responseCode = "500"
                    )
            }
    )
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @PUT
    @Path("/{eventId}")
    public Response updateEvent(@Parameter(description = "Cleaning event ID.", required = true)
                                @PathParam("eventId") Integer eventId,
                                @RequestBody(
                                        description = "DTO object with cleaning event.",
                                        required = true, content = @Content(
                                        schema = @Schema(implementation = EventDto.class)))
                                        EventDto event) throws InvalidParameterException, InternalServerException, InvalidEntityException {

        EventDto eventDto;
        try{
            eventDto = eventBean.putEvent(eventId,event);
        }catch (InvalidEntityException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }catch (InternalServerException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        return  Response.status(Response.Status.OK).entity(eventDto).build();
    }


}
