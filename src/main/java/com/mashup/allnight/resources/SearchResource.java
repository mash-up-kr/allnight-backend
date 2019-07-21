package com.mashup.allnight.resources;

import com.mashup.allnight.dto.requset.SearchCocktailRequest;
import com.mashup.allnight.dto.response.CocktailDetailResponse;
import com.mashup.allnight.dto.response.CocktailResponse;
import com.mashup.allnight.service.SearchService;
import com.mashup.allnight.service.SearchServiceImpl;
import com.mashup.allnight.util.FunctionUtil;
import com.sun.istack.internal.Nullable;
import org.elasticsearch.search.SearchHit;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

@Provider
@Path("/api/v1/search")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

    @Inject
    private SearchService searchService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> searchIngredient(@QueryParam("ingredient") String ingredient) throws IOException {
        return searchService.searchIngredient(ingredient);
    }

    @GET
    @Path("/cocktail")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<CocktailResponse> searchCocktailTest(@BeanParam SearchCocktailRequest request) throws IOException {
        return searchService.searchCocktail(request);
    }

    @GET
    @Path("/cocktail/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CocktailDetailResponse searchCocktail(@PathParam("id") String id) throws IOException {
        return searchService.searchCocktailById(id);
    }

    @GET
    @Path("/cocktail/static")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<CocktailResponse> searchCocktail() throws IOException {
        return searchService.getStaticCocktailList();
    }

}
