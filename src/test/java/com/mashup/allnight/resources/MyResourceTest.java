package com.mashup.allnight.resources;

import com.mashup.allnight.config.App;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;

public class MyResourceTest {

//    private HttpServer server;
//    private WebTarget target;
//
//    @Before
//    public void setUp() throws Exception {
//        // start the server
//        server = App.startServer();
//        // create the client
//        Client c = ClientBuilder.newClient();
//
//        // uncomment the following line if you want to enable
//        // support for JSON in the client (you also have to uncomment
//        // dependency on jersey-media-json module in pom.xml and Main.startServer())
//        // --
//        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());
//
//        target = c.target(App.BASE_URI);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        server.stop();
//    }
//
//    /**
//     * Test to see that the message "Got it!" is sent in the response.
//     */
//    @Test
//    public void testGetIt() {
//        String responseMsg = target.path("test").request().get(String.class);
//        assertEquals("Fuck You", responseMsg);
//    }
}
