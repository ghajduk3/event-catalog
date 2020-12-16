package si.fri.rso.event_catalog.services.db;

import si.fri.rso.event_catalog.models.dtos.EventDto;
import si.fri.rso.event_catalog.models.dtos.ImageDTO;
import si.fri.rso.event_catalog.models.entities.EventEntity;
import si.fri.rso.event_catalog.models.transformers.EventConverter;
import si.fri.rso.event_catalog.services.dao.EventDAO;
import si.fri.rso.event_catalog.services.dao.GenericDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import java.io.*;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
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

    public Integer uploadImage(String inputStream, Long fileSize) {
        Integer result;
        ImageDTO image = new ImageDTO(inputStream,fileSize);
        System.out.print(fileSize);
        Entity ent = Entity.entity(image,MediaType.APPLICATION_JSON);
        System.out.println(ent.getAnnotations());
        System.out.println(ent.toString());
        try {
            result = httpClient
                    .target(baseUrl)
                    .request()
                    .post(Entity.entity(image,MediaType.APPLICATION_JSON),Integer.class);


        }catch(WebApplicationException | ProcessingException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException(e);
        }
        return result;
    }

    public EventDto createEvent(EventDto event) throws Exception {

        Integer imageId = uploadImage(event.getUploadedInputStream(),event.getFileLength());
        System.out.println("----- Image id ------");
        System.out.println(imageId);
//        EventEntity ent = eventConverter.transformToEntity(event);
//        ent.setImage_id(imageId);
//        System.out.println(ent.getDescription());
//        ent = eventDao.createNew(ent);

//        return eventConverter.transformToDTO(ent);
        return event;
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

