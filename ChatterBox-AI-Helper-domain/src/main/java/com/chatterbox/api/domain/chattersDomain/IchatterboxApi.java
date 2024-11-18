package com.chatterbox.api.domain.chattersDomain;

import com.chatterbox.api.domain.chattersDomain.agg.UnAnsweredQuestionsAggregates;

import java.io.IOException;

public interface IchatterboxApi {
    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;

}
