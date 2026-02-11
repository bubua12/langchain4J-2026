package com.bubua12.langchain4j.image.controller;

import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

/**
 *
 *
 * @author bubua12
 * @since 2026/2/11 13:23
 */
@RestController
public class ImageModelController {

    @Autowired
    private ChatModel chatModel;

    // org.springframework.core.io.Resource;
    @Value("classpath:static/image/mi.jpg")
    private Resource resource;

    /**
     * 通过Base64编码将图片转化为字符串，结合ImageContent和TextContent形成UserMessage一起发送到模型进行处理
     */
    @GetMapping(value = "/image/call")
    public String readImageContent(@RequestParam(value = "prompt", defaultValue = "分析一下我的这个图片的内容，5.30的股价是多少")
                                   String prompt) throws IOException {
        // 第一步：图片转码，通过Base64编码将图片转化为字符串
        byte[] byteArray = resource.getContentAsByteArray();
        String base64Data = Base64.getEncoder().encodeToString(byteArray);

        // 第二步：提示词指定，结合ImageContent和TextContent一起发送到模型进行处理
        UserMessage userMessage = UserMessage.from(
                TextContent.from(prompt),
                ImageContent.from(base64Data,"image/jpg")
        );

        // 第三步：API调用，使用OpenAiChatModel来构建请求，并通过chat方法进行模型调用 请求内容包括文本提示和图片，模型会根据输入返回分析结果
        ChatResponse chatResponse = chatModel.chat(userMessage);
        String result = chatResponse.aiMessage().text();

        // 第四步：解析与输出，从ChatResponse中获取AI大模型的回复，打印出处理后的结果
        System.out.println(result);

        return result;
    }
}
