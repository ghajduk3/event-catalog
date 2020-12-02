package si.fri.rso.event_catalog.models.transformers;

import si.fri.rso.event_catalog.models.dtos.EventDto;
import si.fri.rso.event_catalog.models.entities.EventEntity;


public class EventConverter {

    public static EventDto toDto(EventEntity entity){
        EventDto dto = new EventDto();
        dto.setEvent_id(entity.getEvent_id());
        dto.setEventStart(entity.getEventStart());
        dto.setEventEnd(entity.getEventEnd());
        dto.setDateStored(entity.getDateStored());
        dto.setImage_id(entity.getImage_id());
        dto.setEvent_id(entity.getEvent_id());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static EventEntity toEntity(EventDto dto){
        EventEntity entity = new EventEntity();

        entity.setEvent_id(dto.getEvent_id());
        entity.setEventStart(dto.getEventStart());
        entity.setEventEnd(dto.getEventEnd());
        entity.setDateStored(dto.getDateStored());
        entity.setImage_id(dto.getImage_id());
        entity.setLocation_id(dto.getLocation_id());
        entity.setDescription(dto.getDescription());

        return entity;
    }
}
