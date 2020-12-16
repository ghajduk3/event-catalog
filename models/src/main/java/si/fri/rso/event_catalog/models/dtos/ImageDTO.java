package si.fri.rso.event_catalog.models.dtos;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.BufferedInputStream;
import java.io.Serializable;


public class ImageDTO{

    private String fileInputStream;
    private Long fileLength;



    public ImageDTO(){}

    public ImageDTO(String inputStream, Long fileSize) {
        this.fileInputStream = inputStream;
        this.fileLength = fileSize;
    }

    public String getInputStream() {
        return fileInputStream;
    }

    public void setInputStream(String inputStream) {
        this.fileInputStream = inputStream;
    }

    public Long getFileSize() {
        return fileLength;
    }

    public void setFileSize(Long fileSize) {
        this.fileLength = fileSize;
    }

}
