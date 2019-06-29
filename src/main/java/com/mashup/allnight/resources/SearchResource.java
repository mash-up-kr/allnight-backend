package com.mashup.allnight.resources;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

public class SearchResource {

    @GET
    @Path("aaa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String searchDataById() {
       return "ABC";
    }

}
