package si.fri.rso.event_catalog.services.dao;

import si.fri.rso.event_catalog.models.entities.EventEntity;

import javax.enterprise.context.RequestScoped;
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
    public EventEntity update(EventEntity event, Integer id){
        EventEntity entity = findById(id);

        if(entity == null){
            return null;
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
