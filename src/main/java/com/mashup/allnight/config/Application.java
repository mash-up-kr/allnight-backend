package com.mashup.allnight.config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class Application extends ResourceConfig {

    public Application() {

        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.MONITORING_ENABLED, true);
        property(ServerProperties.MONITORING_STATISTICS_ENABLED, true);
        property(ServerProperties.MONITORING_STATISTICS_MBEANS_ENABLED, true);

        packages(true, "com.mashup.allnight");

        register(CorsOption.class);
        register(new JacksonFeature());
        register(new ServiceBinder());
//        register(ServiceBinder.class);


//        register(new CDIConfig());
//        Weld weld = new Weld();
//        WeldContainer container = weld.initialize();
//        SearchProcessor searchProcessor = container.select(SearchProcessor.class).get();

    }
}
