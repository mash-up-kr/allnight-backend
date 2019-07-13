package com.mashup.allnight.service;

import com.mashup.allnight.config.ElasticSearchClient;
import com.mashup.allnight.dto.response.CocktailResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;


import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
//@Named("Test")
//@Default
//@SearchServiceType(ServiceType.SEARCH)
public class SearchServiceImpl extends BaseService implements SearchService {

    @Override
    public List<String> searchIngredient(String ingredient) throws IOException {

        //Todo 이전내용

        System.out.println(ingredient);

        MultiSearchRequest multiSearchRequest = new MultiSearchRequest();

        SearchRequest oneSearchRequest = new SearchRequest("cocktail");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("ingredient01", ingredient));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(500);
        oneSearchRequest.source(searchSourceBuilder);

        multiSearchRequest.add(oneSearchRequest);

        SearchRequest twoSearchRequest = new SearchRequest("cocktail");
        searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("ingredient02", ingredient));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(500);
        twoSearchRequest.source(searchSourceBuilder);

        multiSearchRequest.add(twoSearchRequest);

        SearchRequest threeSearchRequest = new SearchRequest("cocktail");
        searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("ingredient03", ingredient));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(500);
        threeSearchRequest.source(searchSourceBuilder);

        multiSearchRequest.add(threeSearchRequest);

        SearchRequest foreSearchRequest = new SearchRequest("cocktail");
        searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("ingredient04", ingredient));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(500);
        foreSearchRequest.source(searchSourceBuilder);

        multiSearchRequest.add(foreSearchRequest);

//        MultiSearchResponse sr = ElasticSearchClient.getInstance()
//                .prepareMultiSearch()
//                .add(oneSearchRequest)
//                .add(twoSearchRequest)
//                .add(threeSearchRequest)
//                .add(foreSearchRequest)
//                .get();

        MultiSearchResponse multiSearchResponse = client.msearch(multiSearchRequest, RequestOptions.DEFAULT);

        System.out.println(multiSearchResponse.getResponses()[0].getResponse().getHits().getTotalHits());
        System.out.println(multiSearchResponse.getResponses()[1].getResponse().getHits().getTotalHits());
        System.out.println(multiSearchResponse.getResponses()[2].getResponse().getHits().getTotalHits());
        System.out.println(multiSearchResponse.getResponses()[3].getResponse().getHits().getTotalHits());

        List<String> ingredients = new ArrayList<>();

        System.out.println(multiSearchResponse.getResponses().length);
//        for (int i = 0; i < multiSearchResponse.getResponses().length; i++) {
//            i++;
//            System.out.println(i);
//            String str = "ingredient0" + i;
//            System.out.println(str);
//            multiSearchResponse.getResponses()[i].getResponse().getHits().forEach(item -> {
////                System.out.println(item.getSourceAsMap().get(str).toString());
////                ingredients.add(item.getSourceAsMap().get(str).toString());
//                ingredients.add(item.getSourceAsMap().get("ingredient01").toString());
//            });
//        }
//        List<String> resIngredient = ingredients.stream().distinct().collect(Collectors.toList());
//        System.out.println(resIngredient);

//        SearchResponse searchResponse = client.search(oneSearchRequest, RequestOptions.DEFAULT);


        multiSearchResponse.getResponses()[0].getResponse().getHits().forEach(item -> {
            ingredients.add(item.getSourceAsMap().get("ingredient01").toString());
        });
        multiSearchResponse.getResponses()[1].getResponse().getHits().forEach(item -> {
            ingredients.add(item.getSourceAsMap().get("ingredient02").toString());
        });
        multiSearchResponse.getResponses()[2].getResponse().getHits().forEach(item -> {
            ingredients.add(item.getSourceAsMap().get("ingredient03").toString());
        });
        multiSearchResponse.getResponses()[3].getResponse().getHits().forEach(item -> {
            ingredients.add(item.getSourceAsMap().get("ingredient04").toString());
        });
        List<String> resIngredient = ingredients.stream().distinct().collect(Collectors.toList());
        System.out.println(resIngredient);

        return resIngredient;
    }

    @Override
    public List<CocktailResponse> searchCocktail(String ingredients) throws IOException {

        SearchRequest searchRequest = new SearchRequest("cocktail02");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("ingredients", ingredients));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(500);
        searchSourceBuilder.timeout(new TimeValue(15, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(searchResponse.getHits().getTotalHits());
        System.out.println(searchResponse.getHits().getHits().length);

        List<CocktailResponse> res = new ArrayList<>();

        Arrays.stream(searchResponse.getHits().getHits()).forEach(searchHit -> {
            res.add(new CocktailResponse(searchHit.getId(),
                    searchHit.getSourceAsMap().get("drinkName").toString(),
                    "Alcoholic",
                    searchHit.getSourceAsMap().get("drinkThumb").toString(),
                    searchHit.getSourceAsMap().get("EnDrinkName").toString()));
        });

        System.out.println(res.size());
        return res;
    }

    @Override
    public Object searchCocktailById(String id) throws IOException {

        SearchRequest searchRequest = new SearchRequest("cocktail02");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("_id", id));
        searchSourceBuilder.timeout(new TimeValue(15, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(searchResponse.getHits().getTotalHits());
        System.out.println(searchResponse.getHits().getHits().length);

        return searchResponse.getHits().getHits()[0].getSourceAsMap();
    }







    @Override
    public List<String> testFunction(String ingredient) throws UnknownHostException {
//
//        SearchResponse response = ElasticSearchClient.getInstance().prepareSearch("cocktail02")
//                .setTypes("cocktail02")
//                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setQuery(QueryBuilders.termQuery("ingredients", ingredient))                 // Query
////                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
//                .setFrom(0).setSize(60).setExplain(true)
//                .get();
//        System.out.println(response.getHits().getTotalHits());
        List<String> a = new ArrayList<>();
        a.add("apple");
        a.add("banana");
        return a;
    }



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
