package com.mashup.allnight.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class Application extends ResourceConfig {

    public Application() {

        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.MONITORING_ENABLED, true);
        property(ServerProperties.MONITORING_STATISTICS_ENABLED, true);
        property(ServerProperties.MONITORING_STATISTICS_MBEANS_ENABLED, true);

        packages("com.mashup.allnight");

        register(CorsOption.class);
//        register(ServiceBinder.class);
    }
}
