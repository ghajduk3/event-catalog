package si.fri.rso.event_catalog.models.transformers;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericConverter<E, D> {
    public D transformToDTO(E entity) {
        return null;
    }

    public List<D> transformToDTO(List<E> entities) {
        List<D> dtos = new ArrayList<>();
        for(E entity : entities) {
            if(entity != null) {
                dtos.add(transformToDTO(entity));
            }
        }
        return dtos;
    }

    public E transformToEntity(D dto) {
        return null;
    }

    public List<E> transformToEntity(List<D> dtos) {
        List<E> entities = new ArrayList<>();
        for(D dto : dtos) {
            if(dto != null) {
                entities.add(transformToEntity(dto));
            }
        }
        return entities;
    }
}