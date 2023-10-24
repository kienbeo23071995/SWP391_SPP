/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ulti;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.tomcat.jakartaee.commons.io.IOUtils;
import org.json.JSONArray;

/**
 *
 * @
 */
public class RestAPI {

    public static JSONArray getJsonArray(String type, String id, String name, String token) {
        JSONArray json = null;
        try {
            URI dummyUri = new URI("https://gitlab.com/api/v4/" + type + "/" + id + "/" + name);
            HttpClient client = HttpClientBuilder.create().build();
            HttpUriRequest httpUriRequest = RequestBuilder.get()
                    .setUri(dummyUri)
                    .build();
            httpUriRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpUriRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

            try {
                HttpResponse respon = client.execute(httpUriRequest);
                if (respon.getStatusLine().getStatusCode() != 200) {
                    return null;
                }
                String source = IOUtils.toString(respon.getEntity().getContent(), "UTF-8");
                json = new JSONArray(source);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    public static JSONArray getJsonArrayGet(String type, String id, String name, String keys,
            String values, String token) {
        JSONArray json = null;
        try {
            URI dummyUri = new URI("https://gitlab.com/api/v4/" + type + "/" + id + "/" + name);
            HttpClient client = HttpClientBuilder.create().build();
            RequestBuilder build = RequestBuilder.get().setUri(dummyUri);

            //set key and value 
            build.addParameter(keys, values);

            HttpUriRequest httpUriRequest = build.build();
            httpUriRequest.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpUriRequest.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

            try {
                HttpResponse respon = client.execute(httpUriRequest);
                if (respon.getStatusLine().getStatusCode() != 200) {
                    return null;
                }
                String source = IOUtils.toString(respon.getEntity().getContent(), "UTF-8");
                json = new JSONArray(source);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return json;
    }
    
    public static void main(String[] args) {
        getJsonArray("projects", "49126518", "glft-p7RLC42wSa_issD3xSR7", "glimt-3i5wnsvvmpzfd7cmcqgfl1rfa");
    }
}
