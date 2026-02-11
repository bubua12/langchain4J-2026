package com.bubua12.langchain4j.persistence.controller;

import cn.hutool.core.date.DateUtil;
import com.bubua12.langchain4j.persistence.service.ChatPersistenceAssistant;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author bubua12
 * @since 2026/02/11 19:12
 */
@RestController
public class ChatPersistenceController {

    @Resource
    private ChatPersistenceAssistant chatPersistenceAssistant;

    @GetMapping(value = "/chatpersistence/redis")
    public String testChatPersistence() {
        chatPersistenceAssistant.chat(1L, "你好！我的名字是小白");
        chatPersistenceAssistant.chat(2L, "你好！我的名字是小鸡毛");

        String chat = chatPersistenceAssistant.chat(1L, "我的名字是什么");
        System.out.println(chat);

        chat = chatPersistenceAssistant.chat(2L, "我的名字是什么");
        System.out.println(chat);

        return "testChatPersistence success : " + DateUtil.now();
    }
}
