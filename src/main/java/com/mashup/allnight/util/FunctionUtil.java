package com.mashup.allnight.util;

import java.util.ArrayList;
import java.util.List;

public class FunctionUtil {

    public static String cleanIngredient(List<String> input) {
        if (input.size() == 1) {
            return input.get(0)+",";
        }
        return input.stream().filter(v -> v.length() > 1).reduce((x, y) -> x + "," + y).get();
    }
}
