package com.mashup.allnight.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashup.allnight.config.ElasticSearchClient;
import com.mashup.allnight.dto.response.CocktailDetailResponse;
import com.mashup.allnight.dto.response.CocktailResponse;
import com.mashup.allnight.util.Constants;
import org.elasticsearch.action.search.*;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
//@Named("Test")
//@Default
//@SearchServiceType(ServiceType.SEARCH)
public class SearchServiceImpl extends BaseService implements SearchService {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<String> searchIngredient(String ingredient) throws IOException {

        SearchRequestBuilder srb1 = ElasticSearchClient.getInstance()
                .prepareSearch(Constants.F_INDEX)
                .setQuery(QueryBuilders.matchQuery("ingredient01", ingredient))
                .setSize(500);

        SearchRequestBuilder srb2 = ElasticSearchClient.getInstance()
                .prepareSearch(Constants.F_INDEX)
                .setQuery(QueryBuilders.matchQuery("ingredient02", ingredient))
                .setSize(500);

        SearchRequestBuilder srb3 = ElasticSearchClient.getInstance()
                .prepareSearch(Constants.F_INDEX)
                .setQuery(QueryBuilders.matchQuery("ingredient03", ingredient))
                .setSize(500);

        SearchRequestBuilder srb4 = ElasticSearchClient.getInstance()
                .prepareSearch(Constants.F_INDEX)
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

        return ingredients.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<CocktailResponse> searchCocktail(String ingredients) throws IOException {

        SearchResponse searchResponse = ElasticSearchClient.getInstance().prepareSearch(Constants.F_INDEX)
                .setTypes(Constants.F_TYPE)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery("ingredients", ingredients))
                .setFrom(0).setSize(60).setExplain(true)
                .get();

        //Todo Logging
        System.out.println(searchResponse.getHits().getTotalHits());
        System.out.println(searchResponse.getHits().getHits().length);

        return makeCocktailResponse(searchResponse.getHits().getHits());
    }

    @Override
    public CocktailDetailResponse searchCocktailById(String id) throws IOException {

        SearchResponse response = ElasticSearchClient.getInstance().prepareSearch(Constants.F_INDEX)
                .setTypes(Constants.F_TYPE)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termQuery("_id", id))
                .setFrom(0).setSize(1).setExplain(false)
                .get();

        return mapper.readValue(response.getHits().getHits()[0].getSourceAsString(), CocktailDetailResponse.class);
    }

    @Override
    public List<CocktailResponse> getStaticCocktailList() throws IOException {
        SearchResponse searchResponse = ElasticSearchClient.getInstance().prepareSearch(Constants.F_INDEX)
                .setTypes(Constants.F_TYPE)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.termsQuery("_id", Constants.COCKTAIL_LIST))
                .setFrom(0).setSize(10).setExplain(true)
                .get();

        return makeCocktailResponse(searchResponse.getHits().getHits());
    }


    private List<CocktailResponse> makeCocktailResponse(SearchHit[] list) {
        List<CocktailResponse> res = new ArrayList<>();
        Arrays.stream(list).forEach(searchHit -> res.add(new CocktailResponse(searchHit.getId(),
                searchHit.getSourceAsMap().get("drinkName").toString(),
                searchHit.getSourceAsMap().get("alcoholic").toString(),
                searchHit.getSourceAsMap().get("drinkThumb").toString(),
                searchHit.getSourceAsMap().get("enDrinkName").toString())));
        return res;
    }
}
