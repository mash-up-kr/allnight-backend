package com.mashup.allnight.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/test")
public class resource {

    @GET
    public String testing(){
        return "Fuck You";
    }
}
