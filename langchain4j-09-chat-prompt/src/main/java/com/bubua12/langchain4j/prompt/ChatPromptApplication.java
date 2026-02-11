package com.bubua12.langchain4j.prompt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 不是随便一个提问就是提示词
 * <a href="https://docs.langchain4j.dev/tutorials/chat-and-language-models#types-of-chatmessage">提示词 从普通的提问 过渡到 提示词</a>
 * 基于多模态，按照官网所提供的强大的提示词辅助来实现我们问答对话性能的提升和高效的文档处理
 *
 * @author bubua12
 * @since 2026/2/11 16:28
 */
@SpringBootApplication
public class ChatPromptApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatPromptApplication.class, args);
    }
}
