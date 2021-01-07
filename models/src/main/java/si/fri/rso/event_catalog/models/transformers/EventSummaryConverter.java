package si.fri.rso.event_catalog.models.transformers;

import javax.enterprise.context.RequestScoped;
import si.fri.rso.event_catalog.models.dtos.EventDto;
import si.fri.rso.event_catalog.models.dtos.EventSummary;
import si.fri.rso.event_catalog.models.entities.EventEntity;

import java.util.ArrayList;
import java.util.List;

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
        dto.setImageUri(entity.getImage_id());
        return dto;
    }

    @Override
    public List<EventSummary> transformToDTO(List<EventEntity> entities){
        List<EventSummary> dtos = new ArrayList<>();
        for(EventEntity entity : entities){
            dtos.add(transformToDTO(entity));
        }
        return dtos;
    }


}
