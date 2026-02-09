package com.bubua12.langchain4j.boot.controller;

import dev.langchain4j.model.chat.ChatModel;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author bubua12
 * @since 2026/02/09 22:30
 */
@RestController
public class PopularIntegrationController {

    @Resource
    private ChatModel chatModel;

    /**
     * 就是langchain4j最原生、最底层的 API ChatModel
     */
    @GetMapping(value = "/boot/chat")
    public String chat(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        return chatModel.chat(prompt);
    }
}
