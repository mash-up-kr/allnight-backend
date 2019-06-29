package com.mashup.allnight.resources;

import org.elasticsearch.common.Nullable;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

public class SearchResource {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String searchIngredient(@QueryParam("ingredient") String ingredient) {
        return ingredient;
    }

    @GET
    @Path("/cocktail")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String searchCocktail(@QueryParam("ingredients") List<String> ingredients,
                                 @QueryParam("0") int offset,
                                 @DefaultValue("14") @QueryParam("size") int size,
                                 @Nullable @DefaultValue("true") @QueryParam("isAlcohol") boolean isAlcohol,
                                 @Nullable @QueryParam("ingredientCount") int ingredientCount) {
        return cleanNames(ingredients);
    }

    @GET
    @Path("/cocktail/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String searchCocktail(@PathParam("id") String id) {
        return id;
    }

    private String cleanNames(List<String> input) {
        return input.stream().filter(v -> v.length() > 1).map(String::toUpperCase).reduce((x, y) -> x + "," + y).get();
    }

}
