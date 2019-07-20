package com.mashup.allnight.resources;

import com.mashup.allnight.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @POST
    @Path("/scrap/recipe/{cocktailId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String scrapCocktailRecipe(@PathParam("cocktailId") String cocktailId) {
        userService.scrapCocktail(cocktailId);
        return "Success";
    }

    @GET
    @Path("/scrap/recipe")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getCocktailRecipeList() {
        userService.getMyCocktailList();
        return "Success";
    }
}
