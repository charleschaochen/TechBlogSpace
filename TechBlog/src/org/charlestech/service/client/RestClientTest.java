package org.charlestech.service.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/*
 * @Description Restfult Webservice Client Test
 * @author Charles Chen
 * @date 14-4-22
 * @version 1.0
 */
public class RestClientTest {
    public static void main(String[] args) {
        String wsBaseUrl = "http://localhost:8080/rest/";
        Client client = Client.create(new DefaultClientConfig());
        WebResource service = client.resource(wsBaseUrl);
        String response = service.path("articles").path("get_articles").queryParam("callback", "TechBlogCallBack").get(String.class);
        System.out.println(response);
    }
}
