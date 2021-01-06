package si.fri.rso.event_catalog.models.dtos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.*;
import java.util.Date;

public class EventDto {

    private Integer event_id;
    private Date eventStart;
    private Date eventEnd;
    private Date dateStored = new Date();
    private String locationId;
    private String description;
    private String uploadedInputStream;
    private Long fileLength;
    private String imageUri;



    public EventDto(){}

    public EventDto(Integer event_id, Date eventStart, Date eventEnd,String description,String uploadedInputStream,Long fileLength) {
        this.event_id = event_id;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.description = description;
        this.uploadedInputStream = uploadedInputStream;
        this.fileLength = fileLength;
    }


    public EventDto(Integer event_id, Date eventStart, Date eventEnd, String locationId, String description, String uploadedInputStream, Long fileLength, String imageUri) {
        this.event_id = event_id;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.locationId = locationId;
        this.description = description;
        this.uploadedInputStream = uploadedInputStream;
        this.fileLength = fileLength;
        this.imageUri = imageUri;
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

    public String getlocationId() {
        return locationId;
    }

    public void setlocationId(String locationId) {
        this.locationId = locationId;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUploadedInputStream() {
        return uploadedInputStream;
    }

    public void setUploadedInputStream(String uploadedInputStream) {
        this.uploadedInputStream = uploadedInputStream;
    }

    public Long getFileLength() {
        return fileLength;
    }

    public void setFileLength(Long fileLength) {
        this.fileLength = fileLength;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}