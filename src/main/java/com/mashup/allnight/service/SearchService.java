package com.mashup.allnight.service;

import java.util.List;


public interface SearchService {

    String searchIngredient(String ingredient);


    void searchCocktail();


    void searchCocktailById();


    String testFunction(String ingredient);
}
