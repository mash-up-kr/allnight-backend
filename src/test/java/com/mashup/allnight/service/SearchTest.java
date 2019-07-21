package com.mashup.allnight.service;

import com.mashup.allnight.config.App;
import com.mashup.allnight.config.ElasticSearchClient;
import com.mashup.allnight.dto.response.CocktailResponse;
import com.mashup.allnight.util.FunctionUtil;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SearchTest {

//    @Inject
//    private SearchService searchService = new SearchServiceImpl();
//    private RestHighLevelClient client;
//
//    private HttpServer server;
//    private WebTarget target;
//
//    @Before
//    public void setUp() throws Exception {
//        server = App.startServer();
//        Client c = ClientBuilder.newClient();
//        target = c.target(App.BASE_URI);
//
//        client = new RestHighLevelClient(RestClient.builder(new HttpHost("13.209.250.163", 9201, "http")));
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        server.stop();
//        client.close();
//    }


    @Test
    public void 재료_검색() throws IOException {
        String ingredient = "보드카";

        SearchRequestBuilder srb1 = ElasticSearchClient.getInstance()
                .prepareSearch("cocktail02")
                .setQuery(QueryBuilders.matchQuery("ingredient01", ingredient))
                .setSize(500);

        SearchRequestBuilder srb2 = ElasticSearchClient.getInstance()
                .prepareSearch("cocktail02")
                .setQuery(QueryBuilders.matchQuery("ingredient02", ingredient))
                .setSize(500);

        SearchRequestBuilder srb3 = ElasticSearchClient.getInstance()
                .prepareSearch("cocktail02")
                .setQuery(QueryBuilders.matchQuery("ingredient03", ingredient))
                .setSize(500);

        SearchRequestBuilder srb4 = ElasticSearchClient.getInstance()
                .prepareSearch("cocktail02")
                .setQuery(QueryBuilders.matchQuery("ingredient04", ingredient))
                .setSize(500);

        MultiSearchResponse multiSearchResponse = ElasticSearchClient.getInstance()
                .prepareMultiSearch()
                .add(srb1)
                .add(srb2)
                .add(srb3)
                .add(srb4)
                .get();

        List<String> ingredients = new ArrayList<>();

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

//        resIngredient.forEach(System.out::println);
        System.out.println(FunctionUtil.cleanIngredient(resIngredient));

    }


    /**
     * ingredients
     * offset
     * size  (DefaultValue - 500)
     * isAlcohol (DefaultValue - true)
     * ingredientCount
     *
     * @throws IOException
     */

    /**
     *  1. 재료가 몇개든 match 쿼리로 검색을 한다.
     *  2. isAlcohol의 여부를 검색한다 (전부 true 데이터이므로 박아도 될듯)
     */
    //Todo. ingredientCount (재료 갯수.. 이게 뭔가 문제 일거 같은데 고민해보자.)
//    @Test
//    public void 칵테일_검색() throws IOException {
//        String ingredients = "보드카, 레몬";
//
//        SearchRequest searchRequest = new SearchRequest("cocktail02");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchQuery("ingredients", ingredients));
//        searchSourceBuilder.from(0);
//        searchSourceBuilder.size(20);
//        searchSourceBuilder.timeout(new TimeValue(15, TimeUnit.SECONDS));
//        searchRequest.source(searchSourceBuilder);
//
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//
//        System.out.println(searchResponse.getHits().getTotalHits());
//        System.out.println(searchResponse.getHits().getHits().length);
//
//        List<CocktailResponse> res = new ArrayList<>();
//
//        Arrays.stream(searchResponse.getHits().getHits()).forEach(searchHit -> {
//            res.add(new CocktailResponse(searchHit.getId(),
//                    searchHit.getSourceAsMap().get("drinkName").toString(),
//                    searchHit.getSourceAsMap().get("alcoholic").toString(),
//                    searchHit.getSourceAsMap().get("drinkThumb").toString(),
//                    searchHit.getSourceAsMap().get("EnDrinkName").toString()));
//        });
//
//        System.out.println(res.size());
//    }

    @Test
    public void 칵테일_검색_투() throws IOException {
        String ingredients = "레몬, 보드카";

        SearchResponse searchResponse = ElasticSearchClient.getInstance().prepareSearch("f_cocktail")
                .setTypes("f_cocktail")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
//                .setPostFilter(QueryBuilders.termQuery("alcoholic", "Non"))
                .setQuery(QueryBuilders.boolQuery()
                        .filter(QueryBuilders.multiMatchQuery("Non Optional", "alcoholic"))
//                        .filter(QueryBuilders.matchPhrasePrefixQuery("alcoholic", "Optional"))
                        .must(QueryBuilders.matchAllQuery()))
//                .setQuery(QueryBuilders.matchQuery("ingredients", ingredients))               // Query
//                .setFrom(0).setSize(60).setExplain(true)
                .get();

        System.out.println(searchResponse.getHits().getTotalHits());
        System.out.println(searchResponse.getHits().getHits().length);

        List<CocktailResponse> res = new ArrayList<>();

        Arrays.stream(searchResponse.getHits().getHits()).forEach(searchHit -> {
            res.add(new CocktailResponse(searchHit.getId(),
                    searchHit.getSourceAsMap().get("drinkName").toString(),
                    searchHit.getSourceAsMap().get("alcoholic").toString(),
                    searchHit.getSourceAsMap().get("drinkThumb").toString(),
                    searchHit.getSourceAsMap().get("enDrinkName").toString()));
        });
        res.forEach(item -> System.out.println(item.getEnDrinkName()));
        System.out.println(res.size());
    }


    @Test
    public void 칵테일_상세정보_검색() throws IOException {

//        String id = "vOeS6WsBTwvkY-8GRCoB";
//
//        SearchRequest searchRequest = new SearchRequest("cocktail02");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.termQuery("_id", id));
//        searchSourceBuilder.timeout(new TimeValue(15, TimeUnit.SECONDS));
//        searchRequest.source(searchSourceBuilder);
//
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//
//        System.out.println(searchResponse.getHits().getTotalHits());
//        System.out.println(searchResponse.getHits().getHits().length);

//        System.out.println(searchResponse.getHits().getHits()[0].getSource);
    }

    @Test
    public void 칵테일_상세정보_검색_투() throws IOException {

        String id = "AWvo1-Y2QWt5lvzDZQGU";

        SearchResponse response = ElasticSearchClient.getInstance().prepareSearch("cocktail02")
                .setTypes("cocktail02")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("_id", id))                 // Query
//                .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .get();

        System.out.println(response.getHits().getHits()[0].getSource());
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
