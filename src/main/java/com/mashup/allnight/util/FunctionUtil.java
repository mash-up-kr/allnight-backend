package com.mashup.allnight.util;

import java.util.ArrayList;
import java.util.List;

public class FunctionUtil {

    public static String cleanIngredient(List<String> input) {
        return input.stream().filter(v -> v.length() > 1).map(String::toUpperCase).reduce((x, y) -> x + "," + y).get();
    }
}
