package com.mashup.allnight.service;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public abstract class BaseService {

    static RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("13.209.250.163", 9200, "http")));

}
