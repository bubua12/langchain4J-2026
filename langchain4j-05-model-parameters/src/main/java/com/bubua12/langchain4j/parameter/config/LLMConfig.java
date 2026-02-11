package com.bubua12.langchain4j.parameter.config;

import com.bubua12.langchain4j.parameter.listener.TestChatModelListener;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

/**
 * 大模型相关配置类
 *
 * @author bubua12
 * @since 2026/02/09 21:39
 */
@Configuration
public class LLMConfig {

//    @Bean(name = "qwen")
//    public ChatModel chatModelQwen() {
//        return OpenAiChatModel.builder()
//                .apiKey(System.getenv("BAILIAN_API_KEY"))
//                .modelName("qwen3-max-preview")
//                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
//                .build();
//    }

    // 大模型使用过程中的 大模型三件套: api、modelName、baseUrl
    // 除了上述的大模型三件套之外，在跟大模型交互过程中，可能需要一些其它的功能，比如说 日志、监控、重试次数、最大 token值的配置等等。
    // 为了更好的跟大模型进行交互，需要对模型的属性做一些个性化和针对性的配置 简而言之，继续配置
    // https://docs.langchain4j.dev/tutorials/model-parameters

    @Bean(name = "qwen")
    public ChatModel chatModelQwen() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("BAILIAN_API_KEY"))
                .modelName("qwen3-max-preview")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .logRequests(true) // 日志请求：日志级别设置为 debug才有效
                .logResponses(true) // 日志回应：日志级别设置为 debug才有效
                .listeners(List.of(new TestChatModelListener()))
                .maxRetries(3)
                .timeout(Duration.ofSeconds(200)) // 向大模型发送请求时，如在指定的时间内没有收到响应，该请求将被中断，并报 request timeout
                .build();
    }

}
