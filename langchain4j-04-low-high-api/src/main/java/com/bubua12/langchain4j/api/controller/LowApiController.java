package com.bubua12.langchain4j.api.controller;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.TokenUsage;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author bubua12
 * @since 2026/02/11 10:37
 */
@RestController
public class LowApiController {
    @Resource(name = "qwen")
    private ChatModel chatModelQwen;

    @Resource(name = "deepseek")
    private ChatModel chatModelDeepSeek;

    @RequestMapping(value = "/lowapi/api01")
    public String api01(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        String result = chatModelQwen.chat(prompt);

        System.out.println("调用大模型回复：" + result);

        return result;
    }


    /**
     * token用量计算的底层api演示验证案例
     */
    @RequestMapping(value = "/lowapi/api02")
    public String api02(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        ChatResponse chatResponse = chatModelDeepSeek.chat(UserMessage.from(prompt));

        String result = chatResponse.aiMessage().text();
        System.out.println("通过调用大模型返回结果: " + result);

        TokenUsage tokenUsage = chatResponse.tokenUsage();
        System.out.println("本次调用消耗Token: " + tokenUsage);

        result = result + "\t\n" + tokenUsage;

        return result;
    }
}
