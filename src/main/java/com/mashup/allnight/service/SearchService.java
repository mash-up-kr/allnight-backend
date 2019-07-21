package com.mashup.allnight.service;

import com.mashup.allnight.dto.requset.SearchCocktailRequest;
import com.mashup.allnight.dto.response.CocktailDetailResponse;
import com.mashup.allnight.dto.response.CocktailResponse;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;


public interface SearchService {


    List<String> searchIngredient(String ingredient) throws IOException;



    List<CocktailResponse> searchCocktail(SearchCocktailRequest request) throws IOException;



    CocktailDetailResponse searchCocktailById(String id) throws IOException;



    List<CocktailResponse> getStaticCocktailList() throws IOException;


}
