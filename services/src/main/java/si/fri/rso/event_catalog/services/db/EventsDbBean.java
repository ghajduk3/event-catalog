package si.fri.rso.event_catalog.services.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import si.fri.rso.event_catalog.models.dtos.EventDto;
import si.fri.rso.event_catalog.models.dtos.ImageDTO;
import si.fri.rso.event_catalog.models.entities.EventEntity;
import si.fri.rso.event_catalog.models.transformers.EventConverter;
import si.fri.rso.event_catalog.services.dao.EventDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import java.util.List;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;



@RequestScoped
public class EventsDbBean {


    @Inject
    private EventDAO eventDao;

    @Inject
    private EventConverter eventConverter;
    private Client httpClient;

    private String baseUrl;



    @PostConstruct
    private void init(){
        httpClient = ClientBuilder.newClient();
        baseUrl = "http://127.0.0.1:8081/v1/upload";
    }

    public Integer uploadImage(ImageDTO image) {
        ObjectMapper objectMapper = new ObjectMapper();
        String result;
        String imageAsString;
        try {
            imageAsString = objectMapper.writeValueAsString(image);
        }catch (Exception e){
            throw new InternalServerErrorException(e);
        }
        System.out.println(imageAsString);

        Entity ent = Entity.entity(imageAsString,MediaType.APPLICATION_JSON_TYPE);

        System.out.println(ent.getEntity());
        System.out.println(ent.toString());
        try {
            result = httpClient
                    .target(baseUrl)
                    .request()
                    .post(ent,String.class);


        }catch(WebApplicationException | ProcessingException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException(e);
        }
        return Integer.parseInt(result);
    }

    public EventDto createEvent(EventDto event) throws Exception {

        Integer imageId = uploadImage(new ImageDTO(event.getUploadedInputStream(),event.getFileLength()));
        System.out.println("----- Image id ------");
        System.out.println(imageId);
        EventEntity ent = eventConverter.transformToEntity(event);
        ent.setImage_id(imageId);
        System.out.println(ent.getDescription());
        ent = eventDao.createNew(ent);
        return eventConverter.transformToDTO(ent);
    }


    public EventDto findById(Integer id){
        EventEntity ent  = eventDao.findById(id);
        return eventConverter.transformToDTO(ent);
    }

    public List<EventDto> findAll(){
        List<EventEntity> allEvents = eventDao.findAll();
        return eventConverter.transformToDTO(allEvents);
    }

    public Boolean deleteById(Integer id){
        Boolean resp = eventDao.deleteById(id);
        return resp;
    }

    public EventDto putEvent(Integer id, EventDto event){
        EventEntity entity = eventDao.update(eventConverter.transformToEntity(event),id);
        return eventConverter.transformToDTO(entity);
    }

    }

