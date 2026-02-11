package com.bubua12.langchain4j.rag.controller;

import com.bubua12.langchain4j.rag.service.ChatAssistant;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * <a href="https://docs.langchain4j.dev/tutorials/rag#rag-flavours-in-langchain4j">rag</a>
 *
 * @author bubua12
 * @since 2026/02/11 23:28
 */
@RestController
@Slf4j
public class RAGController {

    @Resource
    InMemoryEmbeddingStore<TextSegment> embeddingStore;

    @Resource
    ChatAssistant chatAssistant;

    @GetMapping(value = "/rag/add")
    public String testAdd() throws FileNotFoundException {
        //Document document = FileSystemDocumentLoader.loadDocument("E:\\work__bak\\alibaba-java.docx");

        FileInputStream fileInputStream = new FileInputStream("E:\\work__bak\\dy.docx");
        Document document = new ApacheTikaDocumentParser().parse(fileInputStream);

        EmbeddingStoreIngestor.ingest(document, embeddingStore);

        String result = chatAssistant.chat("电冶的故障是什么");

        System.out.println(result);

        return result;
    }
}
