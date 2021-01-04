package si.fri.rso.event_catalog.api.v1.health;


import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import si.fri.rso.event_catalog.services.config.AppProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class CustomHealth implements HealthCheck {

    @Inject
    private AppProperties appProperties;

    @Override
    public HealthCheckResponse call(){

        if(appProperties.getBroken()){
            return HealthCheckResponse.down("Resource is not alive");
        }else{
            return HealthCheckResponse.up("Resource is alive");
        }
    }

//
}
