package si.fri.rso.event_catalog.services.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import si.fri.rso.event_catalog.models.dtos.ImageDTO;
import si.fri.rso.event_catalog.services.config.ImageProperties;
import si.fri.rso.event_catalog.services.config.LocationProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class ImageUpload {

    @Inject
    private ImageProperties imageConfig;

    private String baseUrl;
    private Client httpClient;


    @PostConstruct
    private void init(){
        httpClient = ClientBuilder.newClient();
        baseUrl = "http://" + imageConfig.getServiceName() + ":" + imageConfig.getPort() + "/v1/upload";
    }

    public String uploadImage(ImageDTO image) {
        ObjectMapper objectMapper = new ObjectMapper();
        String result;
        String imageAsString;
        try {
            imageAsString = objectMapper.writeValueAsString(image);
        }catch (Exception e){
            throw new InternalServerErrorException(e);
        }
        System.out.println(imageAsString);

        Entity ent = Entity.entity(imageAsString, MediaType.APPLICATION_JSON_TYPE);

        System.out.println(ent.getEntity());
        System.out.println(ent.toString());
        try {
            result = httpClient
                    .target(baseUrl)
                    .request()
                    .post(ent,String.class);

        }catch(WebApplicationException | ProcessingException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException(e);
        }
        return result;
    }


}
