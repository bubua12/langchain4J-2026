package com.bubua12.langchain4j.boot.service;

import dev.langchain4j.service.spring.AiService;

/**
 * <a href="https://docs.langchain4j.dev/tutorials/spring-boot-integration/#spring-boot-starter-for-declarative-ai-services">知识出处</a>
 *
 * @author bubua12
 * @since 2026/02/09 22:33
 */
@AiService
public interface ChatAssistant {

    String chat(String prompt);
}
