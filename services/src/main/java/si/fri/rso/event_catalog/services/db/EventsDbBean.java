package si.fri.rso.event_catalog.services.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import si.fri.rso.event_catalog.models.dtos.EventDto;
import si.fri.rso.event_catalog.models.dtos.EventSummary;
import si.fri.rso.event_catalog.models.dtos.ImageDTO;
import si.fri.rso.event_catalog.models.dtos.LocationDto;
import si.fri.rso.event_catalog.models.entities.EventEntity;
import si.fri.rso.event_catalog.models.transformers.EventConverter;
import si.fri.rso.event_catalog.models.transformers.EventSummaryConverter;
import si.fri.rso.event_catalog.services.clients.ImageUpload;
import si.fri.rso.event_catalog.services.clients.LocationProcessing;
import si.fri.rso.event_catalog.services.config.ImageProperties;
import si.fri.rso.event_catalog.services.config.LocationProperties;
import si.fri.rso.event_catalog.services.dao.EventDAO;

import si.fri.rso.event_catalog.services.exceptions.InternalServerException;
import si.fri.rso.event_catalog.services.exceptions.InvalidEntityException;
import si.fri.rso.event_catalog.services.exceptions.InvalidParameterException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;



@RequestScoped
public class EventsDbBean {

    private Logger log = Logger.getLogger(EventsDbBean.class.getName());

    @Inject
    private EventDAO eventDao;


    @Inject
    private EventConverter eventConverter;

    @Inject
    private EventSummaryConverter eventSummaryConverter;

    @Inject
    private LocationProcessing locationProcessing;

    @Inject
    private ImageUpload imageUpload;



    public EventSummary createEvent(EventDto event) throws Exception {

        Integer locationId = locationProcessing.preprocessLocation(event.getlocationId());
        String imageUri = imageUpload.uploadImage(new ImageDTO(event.getUploadedInputStream(), event.getFileLength()));
        EventEntity ent = eventConverter.transformToEntity(event);
        System.out.println(imageUri);
        ent.setLocation_id(locationId);
        ent.setImage_id(imageUri);
        System.out.println(ent.getImage_id());
        System.out.println(ent.getLocation_id());
        EventSummary eventSummary = eventSummaryConverter.transformToDTO(eventDao.createNew(ent)) ;
        return eventSummary;
    }


    public EventSummary findById(Integer id) throws EntityNotFoundException, InternalServerException {
        EventEntity ent = eventDao.findById(id);
        EventSummary event = eventSummaryConverter.transformToDTO(ent);
        event.setLocationDetails(locationProcessing.getLocationById(event.getLocationId()));
        return event;
    }


    public List<EventSummary> findAll() {
        List<EventEntity> allEvents = eventDao.findAll();
        return eventSummaryConverter.transformToDTO(allEvents);
    }


    public Boolean deleteById(Integer id) throws InvalidParameterException, InternalServerException {
        Boolean resp = eventDao.deleteById(id);
        return resp;
    }

    public EventDto putEvent(Integer id, EventDto event) throws InternalServerException, InvalidEntityException {
        EventEntity entity = eventDao.update(eventConverter.transformToEntity(event), id);
        return eventConverter.transformToDTO(entity);
    }

}
