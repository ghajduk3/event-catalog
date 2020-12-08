package si.fri.rso.event_catalog.services.db;

import si.fri.rso.event_catalog.models.dtos.EventDto;
import si.fri.rso.event_catalog.models.entities.EventEntity;
import si.fri.rso.event_catalog.models.transformers.EventConverter;
import si.fri.rso.event_catalog.services.dao.EventDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import java.io.*;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;


@RequestScoped
public class EventsDbBean {


    @Inject
    private EventDAO eventDao;

    @Inject
    private EventConverter eventConverter;

//    @Transactional
    public EventDto createEvent(EventDto event) throws Exception {
        System.out.println("Usao sam u createNewwww");
        EventEntity ent = eventConverter.transformToEntity(event);
        System.out.println(ent.getDescription());
        ent = eventDao.createNew(ent);

        return event;
    }

//    @Transactional
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

