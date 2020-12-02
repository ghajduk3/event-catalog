package si.fri.rso.event_catalog.models.dtos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class EventDto {

    private Integer event_id;
    private Date eventStart;
    private Date eventEnd;
    private Date dateStored = new Date();
    private Integer location_id;
    private Integer image_id;
    private String description;

    public EventDto(){}

    public EventDto(Integer event_id, Date eventStart, Date eventEnd,String description) {
        this.event_id = event_id;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.description = description;
    }

    public EventDto(Integer event_id, Date eventStart, Date eventEnd, Integer location_id, Integer image_id, String description) {
        this.event_id = event_id;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.location_id = location_id;
        this.image_id = image_id;
        this.description = description;
    }

    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public Date getEventStart() {
        return eventStart;
    }

    public void setEventStart(Date eventStart) {
        this.eventStart = eventStart;
    }

    public Date getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(Date eventEnd) {
        this.eventEnd = eventEnd;
    }

    public Date getDateStored() {
        return dateStored;
    }

    public void setDateStored(Date dateStored) {
        this.dateStored = dateStored;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public Integer getImage_id() {
        return image_id;
    }

    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}