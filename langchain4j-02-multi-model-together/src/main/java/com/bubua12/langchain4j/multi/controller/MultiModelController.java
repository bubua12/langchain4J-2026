package com.bubua12.langchain4j.multi.controller;

import dev.langchain4j.model.chat.ChatModel;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author bubua12
 * @since 2026/02/09 22:00
 */
@RestController
public class MultiModelController {

    @Resource(name = "qwen")
    private ChatModel chatModelQwen;

    @Resource(name = "deepseek")
    private ChatModel chatModelDeepSeek;

    @RequestMapping(value = "/multi/qwen")
    public String qwenCall(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        String result = chatModelQwen.chat(prompt);

        System.out.println("[Qwen]调用大模型回复：" + result);

        return result;
    }

    @RequestMapping(value = "/multi/deepseek")
    public String deepSeekCall(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        String result = chatModelDeepSeek.chat(prompt);

        System.out.println("[DeepSeek]调用大模型回复：" + result);

        return result;
    }
}
