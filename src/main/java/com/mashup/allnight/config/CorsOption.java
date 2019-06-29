package com.mashup.allnight.config;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

public class CorsOption implements ContainerResponseFilter {

    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
        headers.add("Access-Control-Allow-Headers", "Authorization,Keep-Alive,User-Agent,Content-Type");

        if ("OPTIONS".equals(requestContext.getMethod())) {
            responseContext.setStatus(204);
        }
    }
}
