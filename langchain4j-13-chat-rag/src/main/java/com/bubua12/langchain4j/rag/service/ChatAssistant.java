package com.bubua12.langchain4j.rag.service;

/**
 *
 *
 * @author bubua12
 * @since 2026/02/11 23:27
 */
public interface ChatAssistant {

    /**
     * 聊天
     *
     * @param message 消息
     * @return {@link String }
     */
    String chat(String message);
}