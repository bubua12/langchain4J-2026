package com.bubua12.langchain4j.memory.service;

/**
 *
 *
 * @author bubua12
 * @since 2026/2/11 15:50
 */
public interface ChatAssistant {

    /**
     * 普通聊天对话，不带记忆缓存功能
     */
    String chat(String prompt);
}
