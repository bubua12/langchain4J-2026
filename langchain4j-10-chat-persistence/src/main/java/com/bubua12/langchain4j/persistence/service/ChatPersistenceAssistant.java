package com.bubua12.langchain4j.persistence.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;

/**
 *
 *
 * @author bubua12
 * @since 2026/02/11 19:02
 */
public interface ChatPersistenceAssistant {

    /**
     * 聊天
     *
     * @param userId 用户 ID
     * @param message 对话内容
     * @return {@link String}
     */
    String chat(@MemoryId Long userId, @UserMessage String message);
}
