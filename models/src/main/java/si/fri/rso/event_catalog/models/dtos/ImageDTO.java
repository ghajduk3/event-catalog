package si.fri.rso.event_catalog.models.dtos;

public class ImageDTO {
    private String fileInputStream;
    private Long fileLength;

    public ImageDTO(String fileInputStream, Long fileLength) {
        this.fileInputStream = fileInputStream;
        this.fileLength = fileLength;
    }

    public String getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(String fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public Long getFileLength() {
        return fileLength;
    }

    public void setFileLength(Long fileLength) {
        this.fileLength = fileLength;
    }


}
