package com.mashup.allnight.service;

import com.mashup.allnight.util.BaseService;


import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class SearchServiceImpl implements SearchService {

    @Override
    public String searchIngredient(String ingredient) {

        List<String> temp = new ArrayList<>();
        temp.add("abc");

        return "abcasd";

        //Todo 이전내용

//        System.out.println(ingredient);
//
//        MultiSearchRequest multiSearchRequest = new MultiSearchRequest();
//
//        SearchRequest oneSearchRequest = new SearchRequest("cocktail");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.termQuery("ingredient01", ingredient));
//        searchSourceBuilder.from(0);
//        searchSourceBuilder.size(500);
//        oneSearchRequest.source(searchSourceBuilder);
//
//        multiSearchRequest.add(oneSearchRequest);
//
//        SearchRequest twoSearchRequest = new SearchRequest("cocktail");
//        searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.termQuery("ingredient02", ingredient));
//        searchSourceBuilder.from(0);
//        searchSourceBuilder.size(500);
//        twoSearchRequest.source(searchSourceBuilder);
//
//        multiSearchRequest.add(twoSearchRequest);
//
//        SearchRequest threeSearchRequest = new SearchRequest("cocktail");
//        searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.termQuery("ingredient03", ingredient));
//        searchSourceBuilder.from(0);
//        searchSourceBuilder.size(500);
//        threeSearchRequest.source(searchSourceBuilder);
//
//        multiSearchRequest.add(threeSearchRequest);
//
//        SearchRequest foreSearchRequest = new SearchRequest("cocktail");
//        searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.termQuery("ingredient04", ingredient));
//        searchSourceBuilder.from(0);
//        searchSourceBuilder.size(500);
//        foreSearchRequest.source(searchSourceBuilder);
//
//        multiSearchRequest.add(foreSearchRequest);
//
//        MultiSearchResponse multiSearchResponse = ElasticSearchClient.getInstance().msearch(multiSearchRequest, RequestOptions.DEFAULT);
//
//        System.out.println(multiSearchResponse.getResponses()[0].getResponse().getHits().getTotalHits());
//        System.out.println(multiSearchResponse.getResponses()[1].getResponse().getHits().getTotalHits());
//        System.out.println(multiSearchResponse.getResponses()[2].getResponse().getHits().getTotalHits());
//        System.out.println(multiSearchResponse.getResponses()[3].getResponse().getHits().getTotalHits());
//
//        List<String> ingredients = new ArrayList<>();
//
//        System.out.println(multiSearchResponse.getResponses().length);
////        for (int i = 0; i < multiSearchResponse.getResponses().length; i++) {
////            i++;
////            System.out.println(i);
////            String str = "ingredient0" + i;
////            System.out.println(str);
////            multiSearchResponse.getResponses()[i].getResponse().getHits().forEach(item -> {
//////                System.out.println(item.getSourceAsMap().get(str).toString());
//////                ingredients.add(item.getSourceAsMap().get(str).toString());
////                ingredients.add(item.getSourceAsMap().get("ingredient01").toString());
////            });
////        }
////        List<String> resIngredient = ingredients.stream().distinct().collect(Collectors.toList());
////        System.out.println(resIngredient);
//
////        SearchResponse searchResponse = client.search(oneSearchRequest, RequestOptions.DEFAULT);
//
//
//        multiSearchResponse.getResponses()[0].getResponse().getHits().forEach(item -> {
//            ingredients.add(item.getSourceAsMap().get("ingredient01").toString());
//        });
//        multiSearchResponse.getResponses()[1].getResponse().getHits().forEach(item -> {
//            ingredients.add(item.getSourceAsMap().get("ingredient02").toString());
//        });
//        multiSearchResponse.getResponses()[2].getResponse().getHits().forEach(item -> {
//            ingredients.add(item.getSourceAsMap().get("ingredient03").toString());
//        });
//        multiSearchResponse.getResponses()[3].getResponse().getHits().forEach(item -> {
//            ingredients.add(item.getSourceAsMap().get("ingredient04").toString());
//        });
//        List<String> resIngredient = ingredients.stream().distinct().collect(Collectors.toList());
//        System.out.println(resIngredient);

//        return resIngredient;
    }

    @Override
    public void searchCocktail() {

    }

    @Override
    public void searchCocktailById() {

    }

    @Override
    public String testFunction(String ingredient) {
        return "나의 재료는" + ingredient;
    }
//
//    public static String searchCocktail(List<String> ingredients, int offset, int size) throws IOException {
//        System.out.println(ingredients.toString());
//        System.out.println(offset);
//        System.out.println(size);
//
//        String[] includeFields = new String[] {"ingredient01", "ingredient02", "ingredient03", "ingredient04", "ingredient05"};
//        String[] excludeFields = new String[] {};
//
//
//
//        SearchRequest oneSearchRequest = new SearchRequest("cocktail");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
////        searchSourceBuilder.fetchSource(includeFields, excludeFields);
//
//        System.out.println(ingredients.get(0));
//        System.out.println(ingredients.get(1));
//        searchSourceBuilder.query(
//                QueryBuilders.boolQuery()
//                        .should(QueryBuilders.termQuery("ingredient01", ingredients.get(0)))
//                        .must(QueryBuilders.termQuery("ingredient02", ingredients.get(1))));
////                        .filter(QueryBuilders.termQuery("ingredient02", ingredients.get(0)))
////                        .filter(QueryBuilders.termQuery("ingredient03", ingredients.get(0)))
////                        .filter(QueryBuilders.termQuery("ingredient04", ingredients.get(0)))
////                        .filter(QueryBuilders.termQuery("ingredient05", ingredients.get(0))));
//
//        searchSourceBuilder.from(0);
//        searchSourceBuilder.size(500);
//        oneSearchRequest.source(searchSourceBuilder);
//
//        SearchResponse searchResponse = client.search(oneSearchRequest, RequestOptions.DEFAULT);
//
//        System.out.println(searchResponse.getHits().getTotalHits());
//
//        return "Success";
//    }

}