package si.fri.rso.event_catalog.services.config;


import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("location")
public class LocationProperties {

    @ConfigValue(value = "service-name",watch = true)
    private String serviceName;

    @ConfigValue(value = "port",watch = true)
    private String port;

    @ConfigValue(value = "version",watch = true)
    private String version;

    @ConfigValue(value = "environment",watch = true)
    private String environment;

    @ConfigValue(value = "process-base-uri",watch = true)
    private String processBaseUri;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getProcessBaseUri() {
        return processBaseUri;
    }

    public void setProcessBaseUri(String processBaseUri) {
        this.processBaseUri = processBaseUri;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
