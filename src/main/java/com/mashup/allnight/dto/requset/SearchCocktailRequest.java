package com.mashup.allnight.dto.requset;

import com.sun.istack.internal.Nullable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.util.List;

@Setter
@Getter
@ToString
public class SearchCocktailRequest {

    @QueryParam("ingredients")
    private List<String> ingredients;

    @DefaultValue("0")
    @QueryParam("offset")
    private int offset;

    @DefaultValue("14")
    @QueryParam("size")
    private int size;

    @DefaultValue("true")
    @QueryParam("isAlcohol")
    private boolean isAlcohol;

    @Nullable
    @QueryParam("ingredientCount")
    private int ingredientCount;
}
