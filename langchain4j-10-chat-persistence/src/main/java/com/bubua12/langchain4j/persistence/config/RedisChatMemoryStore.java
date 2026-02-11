package com.bubua12.langchain4j.persistence.config;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <a href="https://docs.langchain4j.dev/tutorials/chat-memory#persistence">持久化存储</a>
 *
 * @author bubua12
 * @since 2026/02/11 19:04
 */
@Component
public class RedisChatMemoryStore implements ChatMemoryStore {

    public static final String CHAT_MEMERY_PREFIX = "CHAT_MEMERY:";

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        String value = redisTemplate.opsForValue().get(CHAT_MEMERY_PREFIX + memoryId);
        return ChatMessageDeserializer.messagesFromJson(value);
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        redisTemplate.opsForValue()
                .set(CHAT_MEMERY_PREFIX + memoryId, ChatMessageSerializer.messagesToJson(messages));
    }

    @Override
    public void deleteMessages(Object memoryId) {
        redisTemplate.delete(CHAT_MEMERY_PREFIX + memoryId);
    }
}
