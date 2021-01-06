package si.fri.rso.event_catalog.services.clients;

import si.fri.rso.event_catalog.models.dtos.LocationDto;
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
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class LocationProcessing {

    @Inject
    private LocationProperties locationConfig;

    private String baseUrl;
    private Client httpClient;


    @PostConstruct
    private void init(){
        httpClient = ClientBuilder.newClient();
        baseUrl = "http://" + locationConfig.getServiceName() +  ":" + locationConfig.getPort() + "/v1/location/";
    }

//        @Timeout(value=10, unit = ChronoUnit.SECONDS)
//    @CircuitBreaker(requestVolumeThreshold = 5)
//    @Fallback(fallbackMethod = "preprocessLocationFallback")
    public Integer preprocessLocation(String location){
        String locationId;
        try {
            locationId = httpClient
                    .target(baseUrl + "process")
                    .queryParam("address",location)
                    .request()
                    .get(String.class);
        }catch(WebApplicationException | ProcessingException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException(e);
        }

        return Integer.parseInt(locationId);

    }

    public LocationDto getLocationById(String locationId){
        LocationDto location = new LocationDto();
        try {
            location = httpClient
                    .target(baseUrl + locationId)
//                    .queryParam("locationId",locationId)
                    .request(MediaType.APPLICATION_JSON)
                    .get(LocationDto.class);

        }catch(WebApplicationException | ProcessingException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException(e);
        }
        return location;
    }



    public Integer preprocessLocationFallback(String location){
        return null;
    }



}



