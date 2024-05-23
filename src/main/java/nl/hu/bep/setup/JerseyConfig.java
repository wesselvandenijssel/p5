package nl.hu.bep.setup;

import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

/**
 * The type Jersey config.
 * This class is used to configure the Jersey RESTful Web Services
 * @see org.glassfish.jersey.server.ResourceConfig
 * @see javax.ws.rs.ApplicationPath
 */
@ApplicationPath("restservices")
public class JerseyConfig extends ResourceConfig {
    /**
     * Instantiates a new Jersey config.
     * be advised that the package name(s) should be the package(s) where the webservices are located
     */
    public JerseyConfig() {
        packages("nl.hu.bep.countrycase.webservices");
    }
}
