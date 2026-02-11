package com.bubua12.langchain4j.stream.service;

import reactor.core.publisher.Flux;

/**
 * <a href="https://docs.langchain4j.dev/tutorials/response-streaming">流式输出</a>
 *
 * @author bubua12
 * @since 2026/2/11 15:12
 */
public interface ChatAssistant {
    String chat(String prompt);

    /**
     * Flux流式接口调用
     */
    Flux<String> chatFlux(String prompt);
}
