package si.fri.rso.event_catalog.services.dao;

import si.fri.rso.event_catalog.models.dtos.EventSummary;
import si.fri.rso.event_catalog.models.entities.EventEntity;
import si.fri.rso.event_catalog.services.exceptions.InternalServerException;
import si.fri.rso.event_catalog.services.exceptions.InvalidEntityException;
import si.fri.rso.event_catalog.services.exceptions.InvalidParameterException;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
public class EventDAO extends GenericDAO<EventEntity,Integer>{

    EventDAO(){
        super(EventEntity.class);
    }

    @Override
    public List<EventEntity> findAll(){
        return em.createNamedQuery("Event.findAll",EventEntity.class).getResultList();
    }

    @Override
    public EventEntity update(EventEntity event, Integer id) throws EntityNotFoundException, InvalidEntityException, InternalServerException {
        EventEntity entity = findById(id);

        if(entity == null){
            throw new InvalidEntityException();
        }

        try {
            beginTx();
            event.setEvent_id(entity.getEvent_id());
            event = em.merge(event);
            commitTx();
        }catch (Exception e) {
                rollbackTx();
        }
        return event;
    }




}
