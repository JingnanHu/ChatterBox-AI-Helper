package com.chatterbox.api.domain.AI;

import java.io.IOException;

public interface IOpenAi {
    String do_AI(String question) throws IOException;
}
