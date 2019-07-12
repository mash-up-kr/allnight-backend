package com.mashup.allnight.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @POST
    @Path("/scrap/recipe/{cocktailId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String scrapCocktailRecipe(@PathParam("cocktailId") String cocktailId) {
        return "Success";
    }

    @GET
    @Path("/scrap/recipe")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getCocktailRecipeList() {
        return "Success";
    }
}
