package com.mashup.allnight.resources;

import com.mashup.allnight.config.ElasticSearchClient;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Path("/health")
public class CheckResource {

    @GET
    public String test() {

//        GetResponse response = ElasticSearchClient.getInstance().prepareGet("cocktail", "cocktail", "AWuj2MyFPXZajIsHRb7_").get();
//        System.out.println(response.getSourceAsString());

        return "God Damn!";
    }
}
