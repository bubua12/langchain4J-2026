package com.bubua12.langchain4j.api.controller;

import com.bubua12.langchain4j.api.service.ChatAssistant;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author bubua12
 * @since 2026/02/11 10:37
 */
@RestController
public class HighApiController {

    @Resource
    private ChatAssistant chatAssistant;

    @GetMapping(value = "/highapi/api01")
    public String highApi(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        return chatAssistant.chat(prompt);
    }
}
