package com.mashup.allnight.resources;

import com.mashup.allnight.service.SearchService;
import com.mashup.allnight.service.SearchServiceImpl;
import org.elasticsearch.common.Nullable;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class SearchResource {

//    @Inject
//    private SearchService searchService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> searchIngredient(@QueryParam("ingredient") String ingredient) throws IOException {
        return SearchServiceImpl.searchIngredient(ingredient);
    }

    @GET
    @Path("/cocktail")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String searchCocktail(@QueryParam("ingredients") List<String> ingredients,
                                 @QueryParam("0") int offset,
                                 @DefaultValue("500") @QueryParam("size") int size,
                                 @Nullable @DefaultValue("true") @QueryParam("isAlcohol") boolean isAlcohol,
                                 @Nullable @QueryParam("ingredientCount") int ingredientCount) throws IOException {
        return SearchServiceImpl.searchCocktail(ingredients, offset, size);
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
