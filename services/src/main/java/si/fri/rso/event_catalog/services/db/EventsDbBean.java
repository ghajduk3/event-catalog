package si.fri.rso.event_catalog.services.db;

import si.fri.rso.event_catalog.models.dtos.EventDto;
import si.fri.rso.event_catalog.models.entities.EventEntity;
import si.fri.rso.event_catalog.models.transformers.EventConverter;

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

//    @PersistenceContext(name="event-catalog-jpa")
    @Inject
    private EntityManager em;

    @Transactional
    public EventDto createNew(EventDto event){
        System.out.println("Usao sam u createNewwww");
        System.out.println(event.getImage_id());
        EventEntity ent = EventConverter.toEntity(event);
        System.out.println(event.getEvent_id());
        System.out.print(em);
        System.out.print(em.getProperties());
        em.persist(ent);
        return event;
    }

    @Transactional
    public EventDto getById(Integer id){
        System.out.print(em);
        System.out.print(em.getProperties());
        System.out.println(id);
        EventEntity ent  = em.find(EventEntity.class,id);
        return EventConverter.toDto(ent);
    }

}
