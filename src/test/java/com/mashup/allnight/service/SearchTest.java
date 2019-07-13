package com.mashup.allnight.service;

import com.mashup.allnight.config.App;
import com.mashup.allnight.dto.response.CocktailResponse;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
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

public class SearchTest {

//    @Inject
    private SearchService searchService = new SearchServiceImpl();
    private RestHighLevelClient client;

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        server = App.startServer();
        Client c = ClientBuilder.newClient();
        target = c.target(App.BASE_URI);

        client = new RestHighLevelClient(RestClient.builder(new HttpHost("13.209.250.163", 9201, "http")));
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
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
    @Test
    public void 칵테일_검색() throws IOException {
        String ingredients = "보드카, 레몬";

        SearchRequest searchRequest = new SearchRequest("cocktail02");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("ingredients", ingredients));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(20);
        searchSourceBuilder.timeout(new TimeValue(15, TimeUnit.SECONDS));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(searchResponse.getHits().getTotalHits());
        System.out.println(searchResponse.getHits().getHits().length);

        List<CocktailResponse> res = new ArrayList<>();

        Arrays.stream(searchResponse.getHits().getHits()).forEach(searchHit -> {
            res.add(new CocktailResponse(searchHit.getId(),
                    searchHit.getSourceAsMap().get("drinkName").toString(),
                    searchHit.getSourceAsMap().get("alcoholic").toString(),
                    searchHit.getSourceAsMap().get("drinkThumb").toString(),
                    searchHit.getSourceAsMap().get("EnDrinkName").toString()));
        });

        System.out.println(res.size());
    }
}
