package com.chatterbox.api.domain.chattersDomain.res;

import com.chatterbox.api.domain.chattersDomain.vo.Topics;

import java.util.List;

public class RespData {

    private List<Topics> topics;

    public List<Topics> getTopics() {
        return topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }

}
