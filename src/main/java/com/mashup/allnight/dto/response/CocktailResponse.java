package com.mashup.allnight.dto.response;


import java.util.Objects;

public class CocktailResponse {

    private String id;
    private String drinkName;
    private String alcoholic;
    private String drinkThumb;
    private String enDrinkName;

    public CocktailResponse(String id, String drinkName, String alcoholic, String drinkThumb, String enDrinkName) {
        this.id = id;
        this.drinkName = drinkName;
        this.alcoholic = alcoholic;
        this.drinkThumb = drinkThumb;
        this.enDrinkName = enDrinkName;
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
        return enDrinkName;
    }

    public void setEnDrinkName(String enDrinkName) {
        this.enDrinkName = enDrinkName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CocktailResponse that = (CocktailResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(drinkName, that.drinkName) &&
                Objects.equals(alcoholic, that.alcoholic) &&
                Objects.equals(drinkThumb, that.drinkThumb) &&
                Objects.equals(enDrinkName, that.enDrinkName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, drinkName, alcoholic, drinkThumb, enDrinkName);
    }

    @Override
    public String toString() {
        return "CocktailResponse{" +
                "id='" + id + '\'' +
                ", drinkName='" + drinkName + '\'' +
                ", alcoholic='" + alcoholic + '\'' +
                ", drinkThumb='" + drinkThumb + '\'' +
                ", enDrinkName='" + enDrinkName + '\'' +
                '}';
    }
}
