package com.bubua12.langchain4j.helloworld.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 大模型相关配置类
 *
 * @author bubua12
 * @since 2026/02/09 21:39
 */
@Configuration
public class LLMConfig {

    /**
     * fixme 1、这里的ChatModel是什么意思？是只能聊天的嘛？ 2、openai是什么标准？和dashscope又有什么区别？这俩分别是什么？
     *
     * @return ChatModel
     */
    @Bean
    public ChatModel chatModelQwen() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("BAILIAN_API_KEY"))
                .modelName("qwen3-max-preview")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }
}
