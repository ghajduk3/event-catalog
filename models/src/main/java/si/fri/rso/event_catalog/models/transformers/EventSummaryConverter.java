package si.fri.rso.event_catalog.models.transformers;

import javax.enterprise.context.RequestScoped;
import si.fri.rso.event_catalog.models.dtos.EventDto;
import si.fri.rso.event_catalog.models.dtos.EventSummary;
import si.fri.rso.event_catalog.models.entities.EventEntity;

@RequestScoped
public class EventSummaryConverter extends GenericConverter<EventEntity, EventSummary>{
    @Override
    public EventSummary transformToDTO(EventEntity entity){
        EventSummary dto = new EventSummary();
        dto.setEventId(entity.getEvent_id());
        dto.setEventStart(entity.getEventStart());
        dto.setEventEnd(entity.getEventEnd());
        dto.setLocationId(Integer.toString(entity.getLocation_id()));
        dto.setDescription(entity.getDescription());
        return dto;
    }



}
