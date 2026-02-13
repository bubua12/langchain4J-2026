package com.bubua12.langchain4j.mcp.controller;

import com.bubua12.langchain4j.mcp.service.McpService;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolProvider;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author bubua12
 * @since 2026/2/12 9:54
 */
@RestController
public class McpCallServerController {

    @Resource
    private StreamingChatModel streamingChatModel;


    @GetMapping("/mcp/chat")
    public Flux<String> chat(@RequestParam("question") String question) throws Exception {

        McpTransport transport = new StdioMcpTransport.Builder()
                .command(List.of("cmd", "/c", "npx", "-y", "@baidumap/mcp-server-baidu-map"))
                .environment(Map.of("BAIDU_MAP_API_KEY", System.getenv("BAIDU_MAP_API_KEY")))
                .build();

        // 2.构建McpClient客户端
        McpClient mcpClient = new DefaultMcpClient.Builder()
                .transport(transport)
                .build();

        // 3.创建工具集和原生的FunctionCalling类似
        ToolProvider toolProvider = McpToolProvider.builder()
                .mcpClients(mcpClient)
                .build();

        // 4.通过 AiServices 给我们自定义接口 MCPService构建实现类并将工具集和大模型赋值给AiService
        McpService mcpService = AiServices.builder(McpService.class)
                .streamingChatModel(streamingChatModel)
                .toolProvider(toolProvider)
                .build();


        // 5.调用我们定义的HighApi接口,通过大模型对百度 mcp server 调用
        try {
            return mcpService.chat(question);
        } finally {
            mcpClient.close();
        }
    }
}
