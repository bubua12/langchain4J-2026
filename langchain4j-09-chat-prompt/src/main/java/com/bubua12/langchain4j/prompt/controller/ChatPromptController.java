package com.bubua12.langchain4j.prompt.controller;

import cn.hutool.core.date.DateUtil;
import com.bubua12.langchain4j.prompt.entities.LawPrompt;
import com.bubua12.langchain4j.prompt.service.LawAssistant;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author bubua12
 * @since 2026/2/11 16:30
 */
@RestController
public class ChatPromptController {
    @Resource
    private LawAssistant lawAssistant;

    /**
     * 针对方案一的测试
     */
    @GetMapping(value = "/chatprompt/test1")
    public String test1() {
        String chat = lawAssistant.chat("什么是知识产权？", 2000);
        System.out.println(chat);

        String chat2 = lawAssistant.chat("什么是java？", 2000);
        System.out.println(chat2);

        String chat3 = lawAssistant.chat("介绍下西瓜和芒果", 2000);
        System.out.println(chat3);

        String chat4 = lawAssistant.chat("飞机发动机原理", 2000);
        System.out.println(chat4);

        return "success : " + DateUtil.now() + "<br> \n\n chat: " + chat + "<br> \n\n chat2: " + chat2;
    }


    /**
     * 针对方案二的测试
     * TRIPS协议（与贸易有关的知识产权协议）：这是世界贸易组织（WTO）成员间的一个重要协议，
     * 它规定了最低标准的知识产权保护要求，并适用于所有WTO成员。
     */
    @GetMapping(value = "/chatprompt/test2")
    public String test2() {
        LawPrompt prompt = new LawPrompt();

        prompt.setLegal("知识产权");
        prompt.setQuestion("TRIPS协议?");

        String chat = lawAssistant.chat(prompt);

        System.out.println(chat);

        return "success : " + DateUtil.now() + "<br> \n\n chat: " + chat;
    }

}
