package com.bubua12.langchain4j.image.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author bubua12
 * @since 2026/2/11 13:15
 */
@Configuration
public class LLMConfig {

    /**
     * 图像处理模型
     */
    @Bean
    public ChatModel chatModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("BAILIAN_API_KEY"))
                .modelName("qwen-vl-max") // fixme 这里只能qwen-vl-max 图片理解、图片生成是不同的模型负责
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .logRequests(true)
                .logResponses(true)
                .build();
    }
}
