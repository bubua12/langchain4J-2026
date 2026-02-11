package com.bubua12.langchain4j.persistence.config;

import com.bubua12.langchain4j.persistence.service.ChatPersistenceAssistant;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author bubua12
 * @since 2026/02/11 19:08
 */
@Configuration
public class LLMConfig {

    @Resource
    private RedisChatMemoryStore redisChatMemoryStore;

    @Bean
    public ChatModel chatModelQwen() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("BAILIAN_API_KEY"))
                .modelName("qwen3-max-preview")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public ChatPersistenceAssistant chatPersistenceAssistant(ChatModel chatModelQwen) {
        // 持久化配置
        ChatMemoryProvider chatMemoryProvider = memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(1000)
                .chatMemoryStore(redisChatMemoryStore)
                .build();

        return AiServices.builder(ChatPersistenceAssistant.class)
                .chatModel(chatModelQwen)
                .chatMemoryProvider(chatMemoryProvider)
                .build();
    }

}
