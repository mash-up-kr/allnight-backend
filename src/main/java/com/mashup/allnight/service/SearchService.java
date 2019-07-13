package com.mashup.allnight.service;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;


public interface SearchService {

    List<String> searchIngredient(String ingredient) throws IOException;


    void searchCocktail();


    void searchCocktailById();


    List<String> testFunction(String ingredient) throws UnknownHostException;
}
