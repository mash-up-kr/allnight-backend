package com.mashup.allnight.dto.response;


public class CocktailResponse {

    private String id;
    private String drinkName;
    private String alcoholic;
    private String drinkThumb;
    private String EnDrinkName;

    public CocktailResponse(String id, String drinkName, String alcoholic, String drinkThumb, String enDrinkName) {
        this.id = id;
        this.drinkName = drinkName;
        this.alcoholic = alcoholic;
        this.drinkThumb = drinkThumb;
        EnDrinkName = enDrinkName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getAlcoholic() {
        return alcoholic;
    }

    public void setAlcoholic(String alcoholic) {
        this.alcoholic = alcoholic;
    }

    public String getDrinkThumb() {
        return drinkThumb;
    }

    public void setDrinkThumb(String drinkThumb) {
        this.drinkThumb = drinkThumb;
    }

    public String getEnDrinkName() {
        return EnDrinkName;
    }

    public void setEnDrinkName(String enDrinkName) {
        EnDrinkName = enDrinkName;
    }
}
