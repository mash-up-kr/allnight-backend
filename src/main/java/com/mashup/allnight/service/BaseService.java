package com.mashup.allnight.service;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public abstract class BaseService {

    static RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("13.209.250.163", 9201, "http")));
}
