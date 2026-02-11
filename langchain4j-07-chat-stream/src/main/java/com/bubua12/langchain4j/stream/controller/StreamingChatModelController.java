package com.bubua12.langchain4j.stream.controller;

import com.bubua12.langchain4j.stream.service.ChatAssistant;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 *
 *
 * @author bubua12
 * @since 2026/2/11 15:05
 */
@RestController
public class StreamingChatModelController {

    @Resource // 直接使用 low-level LLM API
    private StreamingChatModel streamingChatModel;

    @Resource // 自己封装接口使用 high-level LLM API
    private ChatAssistant chatAssistant;

    /**
     * fixme SpringBoot3 响应式编程 & 消费者接口
     */
    @GetMapping(value = "/chatstream/chat")
    public Flux<String> chat(@RequestParam("prompt") String prompt) {
        return Flux.create(emitter -> streamingChatModel.chat(prompt, new StreamingChatResponseHandler() {

            @Override
            public void onPartialResponse(String partialResponse) {
                emitter.next(partialResponse);
            }

            @Override
            public void onCompleteResponse(ChatResponse completeResponse) {
                emitter.complete();
            }

            @Override
            public void onError(Throwable error) {
                emitter.error(error);
            }
        }));
    }

    @GetMapping(value = "/chatstream/chat2")
    public void chat2(@RequestParam("prompt") String prompt) {
        streamingChatModel.chat(prompt, new StreamingChatResponseHandler() {
            @Override
            public void onPartialResponse(String partialResponse) {
                System.out.println(partialResponse);
            }

            @Override
            public void onCompleteResponse(ChatResponse completeResponse) {
                System.out.println("---response over: " + completeResponse);
            }

            @Override
            public void onError(Throwable throwable) {
            }
        });
    }


    @GetMapping(value = "/chatstream/chat3")
    public Flux<String> chat3(@RequestParam(value = "prompt", defaultValue = "南京有什么好吃") String prompt) {
        return chatAssistant.chatFlux(prompt);
    }
}
