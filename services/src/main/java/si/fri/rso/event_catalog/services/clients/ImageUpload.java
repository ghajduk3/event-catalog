package si.fri.rso.event_catalog.services.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.kumuluz.ee.discovery.annotations.DiscoverService;
//import com.kumuluz.ee.discovery.annotations.RegisterService;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import si.fri.rso.event_catalog.models.dtos.ImageDTO;
import si.fri.rso.event_catalog.services.config.ImageProperties;
import si.fri.rso.event_catalog.services.config.LocationProperties;
import si.fri.rso.event_catalog.services.db.EventsDbBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

@ApplicationScoped
public class ImageUpload {

    private Logger log = Logger.getLogger(ImageUpload.class.getName());

    @Inject
    private ImageProperties imageConfig;

    private Client httpClient;

    @Inject
    @DiscoverService(value = "image-upload", version = "1.0.0")
    private String baseUrl;



    @PostConstruct
    private void init(){
        httpClient = ClientBuilder.newClient();
        baseUrl = baseUrl + "/image/v1/upload";
    }

    @Timeout(value=5, unit = ChronoUnit.SECONDS)
    @CircuitBreaker(requestVolumeThreshold = 3)
    @Fallback(fallbackMethod = "uploadImageFallback")
    public String uploadImage(ImageDTO image) {
        log.info("Uploading image to azure blob storage");
        ObjectMapper objectMapper = new ObjectMapper();
        String result;
        String imageAsString;
        try {
            imageAsString = objectMapper.writeValueAsString(image);
        }catch (Exception e){

            throw new InternalServerErrorException(e);
        }

        Entity ent = Entity.entity(imageAsString, MediaType.APPLICATION_JSON_TYPE);

        try {
            result = httpClient
                    .target(baseUrl)
                    .request()
                    .post(ent,String.class);

        }catch(WebApplicationException | ProcessingException e){
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }
        return result;
    }

    public String uploadImageFallback(ImageDTO image){
        return null;
    }


}
