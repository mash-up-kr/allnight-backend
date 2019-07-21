package com.mashup.allnight.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@Setter
//@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CocktailDetailResponse {

    @JsonProperty("drinkName")
    private String drinkName;

    @JsonProperty("enDrinkName")
    private String enDrinkName;

    @JsonProperty("instructions")
    private String instructions;

    @JsonProperty("enInstructions")
    private String enInstructions;

    @JsonProperty("drinkThumb")
    private String drinkThumb;

    @JsonProperty("glass")
    private String glass;

    @JsonProperty("enGlass")
    private String enGlass;

    @JsonProperty("category")
    private String category;

    @JsonProperty("enCategory")
    private String enCategory;

    @JsonProperty("alcoholic")
    private String alcoholic;

    @JsonProperty("ingredientArray")
    private List<String> ingredientArray;

    @JsonProperty("enIngredientArray")
    private List<String> enIngredientArray;

    @JsonProperty("measureArray")
    private List<String> measureArray;

    @JsonProperty("enMeasureArray")
    private List<String> enMeasureArray;

    @JsonProperty("imMatches")
    private List<Integer> imMatches;

    @JsonProperty("koFullIngredients")
    private List<String> koFullIngredients;

    @JsonProperty("enFullIngredients")
    private List<String> enFullIngredients;


}
