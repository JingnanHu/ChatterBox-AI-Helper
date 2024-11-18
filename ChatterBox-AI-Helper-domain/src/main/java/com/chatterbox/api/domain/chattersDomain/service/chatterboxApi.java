package com.chatterbox.api.domain.chattersDomain.service;

import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.chatterbox.api.domain.chattersDomain.IchatterboxApi;
import com.chatterbox.api.domain.chattersDomain.agg.UnAnsweredQuestionsAggregates;
import com.chatterbox.api.domain.chattersDomain.req.AnswerReq;
import com.chatterbox.api.domain.chattersDomain.req.ReqData;
import com.chatterbox.api.domain.chattersDomain.res.AnswerRes;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class chatterboxApi implements IchatterboxApi {
 private final Logger logger = LoggerFactory.getLogger(chatterboxApi.class);


    @Override
    public UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(""+groupId);
        get.addHeader("Cookie", cookie);
        get.addHeader("contentType", "application/json");
        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == 200) {
            String JsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            return JSON.parseObject(JsonStr, UnAnsweredQuestionsAggregates.class);
        } else {
            throw new RuntimeException("Failed to fetch data, HTTP Status: " + response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
  CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(""+groupId);
        post.addHeader("Cookie", cookie);
        post.addHeader("content-type", "application/json");

        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));
        String paramJson = JSON.toJSONString(answerReq);
        StringEntity entity = new StringEntity(paramJson, "UTF-8");
        post.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == 200) {
            String JsonStr = response.getEntity().getContent().toString();
            logger.info("回答问题结果。groupId：{} topicId：{} jsonStr：{}", groupId, topicId, JsonStr);
AnswerRes answerRes = JSON.parseObject(JsonStr, AnswerRes.class);
return answerRes.isSucceeded();
        } else {
            return response.getStatusLine().getStatusCode() == 404;
        }

    }
}
