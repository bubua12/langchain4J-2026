package com.bubua12.langchain4j.helloworld.controller;

import dev.langchain4j.model.chat.ChatModel;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author bubua12
 * @since 2026/02/09 21:46
 */
@RestController
public class HelloLangChain4JController {

    @Resource
    private ChatModel chatModel;


    @RequestMapping(value = "/langchin4j/hello")
    public String hello(@RequestParam(value = "question", defaultValue = "你是谁") String question) {
        String result = chatModel.chat(question);

        System.out.println("调用大模型回复：" + result);

        return result;
    }
}
