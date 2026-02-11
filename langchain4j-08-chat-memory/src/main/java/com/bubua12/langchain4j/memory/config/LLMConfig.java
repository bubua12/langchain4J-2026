package com.bubua12.langchain4j.memory.config;

import com.bubua12.langchain4j.memory.service.ChatAssistant;
import com.bubua12.langchain4j.memory.service.ChatMemoryAssistant;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.TokenCountEstimator;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiTokenCountEstimator;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <a href="https://docs.langchain4j.dev/tutorials/chat-memory">记忆缓存</a>
 *
 * @author bubua12
 * @since 2026/2/11 15:41
 */
@Configuration
public class LLMConfig {

    // qwen-long作为模型能力提供
    @Bean
    public ChatModel chatModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("BAILIAN_API_KEY"))
                .modelName("qwen-long")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }


    // 普通版
    @Bean(name = "chat")
    public ChatAssistant chatAssistant(ChatModel chatModel) {
        return AiServices.create(ChatAssistant.class, chatModel);
    }

    /**
     * 带有记忆缓存功能的 策略：Window
     * 按照一个 memoryId对应创建一个ChatMemory
     *
     * @param chatModel qwen-long
     * @return ChatMemoryAssistant
     */
    @Bean(name = "chatMessageWindowChatMemory")
    public ChatMemoryAssistant chatMemoryAssistant(ChatModel chatModel) {
        return AiServices.builder(ChatMemoryAssistant.class)
                .chatModel(chatModel)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(100))
                .build();
    }


    /**
     * 带有记忆缓存功能的 策略：Token 基于令牌的数量限制策略
     * TokenCountEstimator: 基于OpenAI协议的token计数评估器
     *
     * @param chatModel qwen-long
     * @return ChatMemoryAssistant
     */
    @Bean(name = "chatTokenWindowChatMemory")
    public ChatMemoryAssistant chatTokenWindowChatMemory(ChatModel chatModel) {
        // TokenCountEstimator默认的 token分词器，需要结合Tokenizer计算ChatMessage的token数量
        TokenCountEstimator openAiTokenizer = new OpenAiTokenCountEstimator("gpt-4");

        return AiServices.builder(ChatMemoryAssistant.class)
                .chatModel(chatModel)
                .chatMemoryProvider(memoryId -> TokenWindowChatMemory.withMaxTokens(2000, openAiTokenizer))
                .build();
    }
}
