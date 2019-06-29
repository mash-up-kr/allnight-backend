package com.mashup.allnight.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/health")
public class CheckResource {

    @GET
    public String test(){
        return "God Damn!";
    }
}
