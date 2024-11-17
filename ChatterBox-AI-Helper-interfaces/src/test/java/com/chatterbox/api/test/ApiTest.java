package com.chatterbox.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class ApiTest {
    @Test
    public void getQuerry() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://api.chatterbox.com");
        httpGet.addHeader("cookie","");
        httpGet.addHeader("contentType","");
try {
    CloseableHttpResponse response = httpClient.execute(httpGet);

    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
        String res = EntityUtils.toString(response.getEntity());
        System.out.println(res);
    } else {
        System.out.println(response.getStatusLine().getStatusCode());
    }
    response.close();
    httpClient.close();
}  catch (Exception e) {
    e.printStackTrace();
}
    }
    @Test
    public void postReply() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.chatterbox.com");
        httpPost.addHeader("cookie","");
        httpPost.addHeader("contentType","");
        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}