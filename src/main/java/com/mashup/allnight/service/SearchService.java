package com.mashup.allnight.service;

import com.mashup.allnight.dto.response.CocktailResponse;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;


public interface SearchService {

    List<String> searchIngredient(String ingredient) throws IOException;


    List<CocktailResponse> searchCocktail(String ingredients) throws IOException;


    void searchCocktailById();


    List<String> testFunction(String ingredient) throws UnknownHostException;
}
