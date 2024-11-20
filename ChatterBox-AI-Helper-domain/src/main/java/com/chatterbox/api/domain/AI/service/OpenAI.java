package com.chatterbox.api.domain.AI.service;
import com.alibaba.fastjson.JSON;
import com.chatterbox.api.domain.AI.IOpenAi;
import com.chatterbox.api.domain.AI.model.aggre.AIAnswer;
import com.chatterbox.api.domain.AI.model.vo.Choices;
import com.chatterbox.api.domain.chattersDomain.IchatterboxApi;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.json.Json;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;

public class OpenAI implements IOpenAi {
    @Value("$(chatterbox-api.openAI)")
    private String openAiKey;
    @Override
    public String do_AI(String question) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api.chatterbox.com");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + openAiKey);
        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"帮我写一个java冒泡排\", \"temperature\": 0, \"max_tokens\": 1024}";
        StringEntity entity = new StringEntity(paramJson, ContentType.create("UTF-8"));
        httpPost.setEntity(entity);
       CloseableHttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            String paramStr = response.getEntity().getContent().toString();
            AIAnswer aiAnswer = JSON.parseObject(paramStr,AIAnswer.class);
            List<Choices> choices = aiAnswer.getChoices();
            StringBuilder separateAnswer = new StringBuilder();
            for (Choices choice:choices){
                separateAnswer.append(choice.getText());
            } return separateAnswer.toString();
        } else {
            return response.getStatusLine().getReasonPhrase();
        }
    }
}
