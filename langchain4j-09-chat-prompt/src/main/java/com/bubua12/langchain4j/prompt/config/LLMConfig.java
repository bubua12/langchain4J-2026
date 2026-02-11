package com.bubua12.langchain4j.prompt.config;

import com.bubua12.langchain4j.prompt.service.LawAssistant;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
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


    @Bean
    public LawAssistant chatAssistant(ChatModel chatModel) {
        return AiServices.create(LawAssistant.class, chatModel);
    }

}
