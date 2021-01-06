package si.fri.rso.event_catalog.models.dtos;

import javax.persistence.Column;
import java.util.Date;

public class EventSummary {
    private Integer eventId;
    private String eventStart;
    private String eventEnd;
    private String description;
    private String locationId;
    private String imageUri;
    private LocationDto locationDetails;


    public EventSummary(){

    }

    public EventSummary(Integer eventId, String eventStart, String eventEnd, String description, String locationId, String imageUri) {
        this.eventId = eventId;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.description = description;
        this.locationId = locationId;
        this.imageUri = imageUri;
    }



    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventStart() {
        return eventStart;
    }

    public void setEventStart(Date eventStart) {
        this.eventStart = eventStart.toString();
    }

    public String getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(Date eventEnd) {
        this.eventEnd = eventEnd.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public LocationDto getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(LocationDto locationDetails) {
        this.locationDetails = locationDetails;
    }


}
