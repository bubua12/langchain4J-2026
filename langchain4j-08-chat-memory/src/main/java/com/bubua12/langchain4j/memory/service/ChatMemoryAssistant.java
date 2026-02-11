package com.bubua12.langchain4j.memory.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;

/**
 *
 *
 * @author bubua12
 * @since 2026/2/11 15:51
 */
public interface ChatMemoryAssistant {

    /**
     * 聊天带记忆缓存功能
     *
     * @param userId 用户 ID
     * @param prompt 用户提示词
     * @return {@link String }
     */
    String chatWithMemory(@MemoryId Long userId, @UserMessage String prompt);
}
