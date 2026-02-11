package com.bubua12.langchain4j.parameter.controller;

import dev.langchain4j.model.chat.ChatModel;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author bubua12
 * @since 2026/2/11 11:34
 */
@RestController
public class ModelParameterController {
    @Resource
    private ChatModel chatModelQwen;

    @GetMapping(value = "/modelparam/config")
    public String config(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        String result = chatModelQwen.chat(prompt);

        System.out.println("通过 langchain4j 调用模型返回结果：" + result);

        return result;
    }
}
