package com.mashup.allnight.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticSearchClient {

    private static ElasticSearchClient esc = null;
    private static volatile TransportClient searchInstance = null;

    private ElasticSearchClient() {}

    public static TransportClient getInstance() throws UnknownHostException {
        if (searchInstance == null) {
            synchronized (TransportClient.class) {
                if (searchInstance == null) {
                    searchInstance = createElasticClient();
                }
            }
        }
        return searchInstance;
    }

    /**
     * V.5.5 - Transport.Tcp
     */
    private static TransportClient createElasticClient() throws UnknownHostException {
//        //Fixme
        Settings settings = Settings.builder().put("cluster.name", "key-application").build();
        searchInstance = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("13.209.250.163"), 9300));
        return searchInstance;
    }

    /**
     * V.7.2 - RestHighLevelClient
     */
//    static RestHighLevelClient client = new RestHighLevelClient(
//            RestClient.builder(
//                    new HttpHost("13.209.250.163", 9200, "http")));
}
