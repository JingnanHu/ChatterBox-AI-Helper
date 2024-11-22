package com.chatterbox.api.application.job;

import com.chatterbox.api.domain.AI.model.vo.Choices;
import com.chatterbox.api.domain.AI.service.OpenAI;
import com.chatterbox.api.domain.chattersDomain.IchatterboxApi;
import com.chatterbox.api.domain.chattersDomain.agg.UnAnsweredQuestionsAggregates;
import com.chatterbox.api.domain.chattersDomain.vo.Topics;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ChatterboxSchedule {

    private IchatterboxApi chatterboxApi ;
    private OpenAI openAI;

    @Value("${chatterbox-api.groupId}")
    private String groupId;
    @Value("${chatterbox-api.cookie}")
    private String cookie;
    Logger logger = LoggerFactory.getLogger(ChatterboxSchedule.class);

    public void run() throws IOException {
        GregorianCalendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour < 8 || hour > 20) {
            System.out.println("Sorry, AI is sleeping");
            return;
        }
//        1. Search for unanswered questions
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = new UnAnsweredQuestionsAggregates();
        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        if (topics.isEmpty() || null == topics) {
            System.out.println("Sorry, I can not find questions");
            return;
        }
//        2. get AI replies
        Topics topic = topics.get(0);
        String answer = openAI.do_AI(topic.getQuestion().getText()).trim();
//        3. reply questions
        boolean status = chatterboxApi.answer(groupId,cookie, topic.getTopic_id(),answer, false);
        logger.info("topicID：{} question：{} answer：{} status：{}", topic.getTopic_id(), topic.getQuestion().getText(), answer, status);

    }
}
