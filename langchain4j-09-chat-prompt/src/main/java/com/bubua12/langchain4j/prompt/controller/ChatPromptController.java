package com.bubua12.langchain4j.prompt.controller;

import cn.hutool.core.date.DateUtil;
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

}
