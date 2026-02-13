package com.bubua12.langchain4j.mcp.service;

import reactor.core.publisher.Flux;

/**
 *
 *
 * @author bubua12
 * @since 2026/2/12 9:53
 */
public interface McpService {
    Flux<String> chat(String question);
}
