package com.bubua12.langchain4j.memory.controller;

import cn.hutool.core.date.DateUtil;
import com.bubua12.langchain4j.memory.service.ChatAssistant;
import com.bubua12.langchain4j.memory.service.ChatMemoryAssistant;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author bubua12
 * @since 2026/2/11 15:41
 */
@RestController
public class ChatMemoryController {

    @Resource(name = "chat")
    private ChatAssistant chatAssistant;

    @Resource(name = "chatMessageWindowChatMemory")
    private ChatMemoryAssistant chatMessageWindowChatMemory;

    @Resource(name = "chatTokenWindowChatMemory")
    private ChatMemoryAssistant chatTokenWindowChatMemory;


    /**
     * v1 没有记忆功能
     */
    @GetMapping(value = "/chatmemory/test1")
    public String chat() {
        String answer01 = chatAssistant.chat("你好，我的名字叫张三");
        System.out.println("answer01返回结果：" + answer01);

        String answer02 = chatAssistant.chat("我的名字是什么");
        System.out.println("answer02返回结果：" + answer02);

        return "success : " + DateUtil.now() + "<br> \n\n answer01: " + answer01 + "<br> \n\n answer02: " + answer02;
    }


    /**
     * MessageWindowChatMemory实现聊天功能
     */
    @GetMapping(value = "/chatmemory/test2")
    public String chatMessageWindowChatMemory() {
        chatMessageWindowChatMemory.chatWithMemory(1L, "你好！我的名字是Java.");
        String answer01 = chatMessageWindowChatMemory.chatWithMemory(1L, "我的名字是什么");
        System.out.println("answer01返回结果：" + answer01);

        chatMessageWindowChatMemory.chatWithMemory(3L, "你好！我的名字是C++");
        String answer02 = chatMessageWindowChatMemory.chatWithMemory(3L, "我的名字是什么");
        System.out.println("answer02返回结果：" + answer02);

        return "chatMessageWindowChatMemory success : "
                + DateUtil.now() + "<br> \n\n answer01: " + answer01 + "<br> \n\n answer02: " + answer02;
    }


    /**
     * TokenWindowChatMemory实现聊天功能
     */
    @GetMapping(value = "/chatmemory/test3")
    public String chatTokenWindowChatMemory() {
        chatTokenWindowChatMemory.chatWithMemory(1L, "你好！我的名字是mysql");
        String answer01 = chatTokenWindowChatMemory.chatWithMemory(1L, "我的名字是什么");
        System.out.println("answer01返回结果：" + answer01);

        chatTokenWindowChatMemory.chatWithMemory(3L, "你好！我的名字是oracle");
        String answer02 = chatTokenWindowChatMemory.chatWithMemory(3L, "我的名字是什么");
        System.out.println("answer02返回结果：" + answer02);

        return "chatTokenWindowChatMemory success : "
                + DateUtil.now() + "<br> \n\n answer01: " + answer01 + "<br> \n\n answer02: " + answer02;
    }
}
