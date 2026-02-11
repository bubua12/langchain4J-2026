package com.bubua12.langchain4j.prompt.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * <a href="https://docs.langchain4j.dev/tutorials/ai-services#systemmessage">SystemMessage</a>
 *
 * @author bubua12
 * @since 2026/2/11 16:29
 */
public interface LawAssistant {

    /**
     * 案例一：@SystemMessage + @UserMessage + @V
     * 1、顶部 @SystemMessage 功能边界上的压制
     * 2、@UserMessage
     */
    @SystemMessage("你是一位专业的中国法律顾问，只回答与中国法律相关的问题。" +
            "输出限制：对于其他领域的问题禁止回答，直接返回'抱歉，我只能回答中国法律相关的问题。'")
    @UserMessage("请回答以下法律问题：{{question}}, 字数控制在 {{length}}以内")
    String chat(@V("question") String question, @V("length") int length);
}
