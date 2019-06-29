package com.mashup.allnight.service;

import com.mashup.allnight.config.ElasticSearchClient;
import com.mashup.allnight.elastic.builder.SearchBuilder;
import com.mashup.allnight.elastic.executor.Executor;
import com.mashup.allnight.resources.SearchResource;
import org.apache.http.HttpHost;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
public class SearchServiceImpl extends BaseService implements SearchService {


    public static List<String> searchIngredient(String ingredient) throws IOException {

        System.out.println(ingredient);
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("13.209.250.163", 9200, "http")));

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

    public static void searchCocktail() {

    }

    public static void searchCocktailById() {

    }
}
