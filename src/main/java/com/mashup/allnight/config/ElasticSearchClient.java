package com.mashup.allnight.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ElasticSearchClient {

    private static ElasticSearchClient instance = null;

    private ElasticSearchClient() {}

    public static ElasticSearchClient getInstance() {
        if (instance == null) {
            synchronized (ElasticSearchClient.class) {
                if (instance == null) {
                    instance = new ElasticSearchClient();
                }
            }
        }
        return instance;
    }

    public TransportClient getElaasticClient() throws UnknownHostException {
        //Fixme
        return new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("13.209.250.163"), 9200));
    }
}
