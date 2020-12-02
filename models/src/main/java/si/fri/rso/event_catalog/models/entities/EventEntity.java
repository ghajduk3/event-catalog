package si.fri.rso.event_catalog.models.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import si.fri.rso.event_catalog.models.dtos.EventDto;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name="cleaning_events")
public class EventEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private Integer event_id;

    @Column(name="event_start")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date eventStart;

    @Column(name="event_end")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date eventEnd;

    @Column(name="record_stored")

    private Date dateStored = new Date();

    @Column(name="location_id")

    private Integer location_id;
    @Column(name="image_id")
    private Integer image_id;
    @Column(name="description")
    private String description;

    public EventEntity(){

    }
    public EventEntity(Integer event_id, Date eventStart, Date eventEnd,String description) {
        this.event_id = event_id;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.description = description;
    }

    public EventEntity(Integer event_id, Date eventStart, Date eventEnd, Integer location_id, Integer image_id, String description) {
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
