package com.bubua12.langchain4j.parameter.listener;

import cn.hutool.core.util.IdUtil;
import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 可观测性 <a href="https://docs.langchain4j.dev/tutorials/spring-boot-integration#observability">模型监控</a> 有点像环绕通知
 *
 * @author bubua12
 * @since 2026/2/11 12:47
 */
@Slf4j
public class TestChatModelListener implements ChatModelListener {

    /**
     * onRequest 配置的kv键值对，在onResponse阶段可以获得，上下文传递参数好用
     */
    @Override
    public void onRequest(ChatModelRequestContext requestContext) {
        String uuidValue = IdUtil.fastSimpleUUID();
        requestContext.attributes().put("trace_id", uuidValue);
        log.info("请求参数 traceId =  {}", uuidValue);
    }

    @Override
    public void onResponse(ChatModelResponseContext responseContext) {
        Object traceId = responseContext.attributes().get("trace_id");
        log.info("返回结果: traceId = {}", traceId);
    }

    @Override
    public void onError(ChatModelErrorContext errorContext) {
        log.error("请求异常: {}", errorContext);
    }
}
