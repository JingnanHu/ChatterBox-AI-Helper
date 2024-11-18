package com.chatterbox.api.test;

import com.chatterbox.api.domain.chattersDomain.IchatterboxApi;
import com.chatterbox.api.domain.chattersDomain.agg.UnAnsweredQuestionsAggregates;
import com.chatterbox.api.domain.chattersDomain.vo.Topics;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;

public class SpringBootRun {
    private IchatterboxApi chatterboxApi ;

    @Value("${chatterbox-api.groupId}")
    private String groupId;
    @Value("${chatterbox-api.cookie}")
    private String cookie;

    public void test_chatterbox_api() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = chatterboxApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for(Topics topic: topics){
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();

chatterboxApi.answer(groupId, cookie, topicId, text,false);
        }
        }

    }