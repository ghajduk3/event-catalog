package si.fri.rso.event_catalog.models.transformers;

import si.fri.rso.event_catalog.models.dtos.EventDto;
import si.fri.rso.event_catalog.models.entities.EventEntity;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class EventConverter extends GenericConverter<EventEntity,EventDto>{


    @Override
    public EventDto transformToDTO(EventEntity entity){
        EventDto dto = new EventDto();
        dto.setEvent_id(entity.getEvent_id());
        dto.setEventStart(entity.getEventStart());
        dto.setEventEnd(entity.getEventEnd());
        dto.setDateStored(entity.getDateStored());
        dto.setAddress(Integer.toString(entity.getLocation_id()));
        dto.setDescription(entity.getDescription());
        return dto;
    }

    @Override
    public List<EventDto> transformToDTO(List<EventEntity> entities){
        List<EventDto> dtos = new ArrayList<>();
        for(EventEntity entity : entities){
            dtos.add(transformToDTO(entity));
        }
        return dtos;
    }

    @Override
    public EventEntity transformToEntity(EventDto dto){
        EventEntity entity = new EventEntity();
        entity.setEventStart(dto.getEventStart());
        entity.setEventEnd(dto.getEventEnd());
        entity.setDateStored(dto.getDateStored());
        entity.setImage_id(null);
        entity.setLocation_id(1);
        entity.setDescription(dto.getDescription());

        return entity;
    }

    @Override
    public List<EventEntity> transformToEntity(List<EventDto> dtos){
        List<EventEntity> entities = new ArrayList<>();

        for(EventDto dto:dtos){
            entities.add(transformToEntity(dto));
        }
        return entities;
    }


}
