package si.fri.rso.event_catalog.api.v1;






import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;



@ApplicationPath("/v1")
@OpenAPIDefinition(
        info = @Info(title = "Rest API", version = "v1", description = "Event Catalog rest api endpoints"),
        servers = @Server(url ="http://localhost:8081/v1")
)
public class EventApplication extends Application{

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("jersey.config.server.provider.classnames",
                "org.glassfish.jersey.media.multipart.MultiPartFeature");
        return props;
    }

}
