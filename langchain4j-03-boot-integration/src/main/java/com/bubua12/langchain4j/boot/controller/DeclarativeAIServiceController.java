package com.bubua12.langchain4j.boot.controller;

import com.bubua12.langchain4j.boot.service.ChatAssistant;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author bubua12
 * @since 2026/02/09 22:32
 */
@RestController
public class DeclarativeAIServiceController {
    @Resource
    private ChatAssistant chatAssistant;

    @GetMapping("/boot/declara")
    public String declarative(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        return chatAssistant.chat(prompt);
    }
}
