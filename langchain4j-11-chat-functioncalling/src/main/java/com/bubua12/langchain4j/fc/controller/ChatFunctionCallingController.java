package com.bubua12.langchain4j.fc.controller;

import cn.hutool.core.date.DateUtil;
import com.bubua12.langchain4j.fc.service.FunctionAssistant;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author bubua12
 * @since 2026/02/11 19:22
 */
@RestController
public class ChatFunctionCallingController {
    @Resource
    private FunctionAssistant functionAssistant;

    @GetMapping(value = "/chatfunction/test1")
    public String test1() {
        String chat = functionAssistant.chat("开张发票,公司：大布布有限公司 税号：bubua121314 金额：668.12");

        System.out.println(chat);

        return "success : " + DateUtil.now() + "\t" + chat;
    }
}
