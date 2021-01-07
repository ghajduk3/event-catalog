package si.fri.rso.event_catalog.services.clients;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import si.fri.rso.event_catalog.models.dtos.LocationDto;
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
import javax.ws.rs.core.MediaType;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

@ApplicationScoped
public class LocationProcessing {

    private Logger log = Logger.getLogger(EventsDbBean.class.getName());
    @Inject
    private LocationProperties locationConfig;

//    private String baseUrl;
    private Client httpClient;

    @Inject
    @DiscoverService(value = "location-processing", version = "1.0.0")
    private String baseUrl;



    @PostConstruct
    private void init(){
        System.out.println("Location client started");
        httpClient = ClientBuilder.newClient();
//        baseUrl = "http://" + locationConfig.getServiceName() +  ":" + locationConfig.getPort() + "/v1/location/";
        baseUrl = baseUrl + "/location/v1/location/";
    }

    @Timeout(value=3, unit = ChronoUnit.SECONDS)
    @CircuitBreaker(requestVolumeThreshold = 2)
    @Fallback(fallbackMethod = "preprocessLocationFallback")
    public Integer preprocessLocation(String location){
        log.info("Processing address information");
        String locationId;
        try {
            locationId = httpClient
                    .target(baseUrl + "process")
                    .queryParam("address",location)
                    .request()
                    .get(String.class);
        }catch(WebApplicationException | ProcessingException e){
            log.severe(e.getMessage());
            throw new InternalServerErrorException(e);
        }

        return Integer.parseInt(locationId);

    }

    @Timeout(value=3, unit = ChronoUnit.SECONDS)
    @CircuitBreaker(requestVolumeThreshold = 2)
    @Fallback(fallbackMethod = "getLocationByIdFallback")
    public LocationDto getLocationById(String locationId){
        LocationDto location = new LocationDto();
        try {
            location = httpClient
                    .target(baseUrl + locationId)
                    .request(MediaType.APPLICATION_JSON)
                    .get(LocationDto.class);

        }catch(WebApplicationException | ProcessingException e){
            throw new InternalServerErrorException(e);
        }
        return location;
    }


    public LocationDto getLocationByIdFallback(String locationId){return null;};
    public Integer preprocessLocationFallback(String location){
        return null;
    }



}



