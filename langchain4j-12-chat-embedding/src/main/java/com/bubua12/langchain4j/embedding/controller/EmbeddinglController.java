package com.bubua12.langchain4j.embedding.controller;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.grpc.Collections;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

/**
 *
 *
 * @author bubua12
 * @since 2026/02/11 20:10
 */
@RestController
public class EmbeddinglController {
    @Resource
    private EmbeddingModel embeddingModel;
    @Resource
    private QdrantClient qdrantClient;
    @Resource
    private EmbeddingStore<TextSegment> embeddingStore;

    /**
     * 文本向量化测试，看看形成向量后的文本
     */
    @GetMapping(value = "/embedding/embed")
    public String embed() {
        String prompt = """
                   咏鸡
                鸡鸣破晓光，
                红冠映朝阳。
                金羽披霞彩，
                昂首步高岗。
                """;
        Response<Embedding> embeddingResponse = embeddingModel.embed(prompt);

        System.out.println(embeddingResponse);

        return embeddingResponse.content().toString();
    }

    /**
     * 新建向量数据库实例和创建索引：test-qdrant
     * 类似 mysql create database test-qdrant
     */
    @GetMapping(value = "/embedding/createCollection")
    public void createCollection() {
        var vectorParams = Collections.VectorParams.newBuilder()
                .setDistance(Collections.Distance.Cosine)
                .setSize(1024)
                .build();
        qdrantClient.createCollectionAsync("test-qdrant", vectorParams);
    }

    /**
     * 往向量数据库新增文本记录
     */
    @GetMapping(value = "/embedding/add")
    public String add() {
        String prompt = """
                咏鸡
                鸡鸣破晓光，
                红冠映朝阳。
                金羽披霞彩，
                昂首步高岗。
                """;
        TextSegment segment = TextSegment.from(prompt);
        // 相当于打备注，方便查找
        segment.metadata().put("author", "bubua12");

        Embedding embedding = embeddingModel.embed(segment).content();
        String result = embeddingStore.add(embedding, segment);

        System.out.println(result);

        return result;
    }


    @GetMapping(value = "/embedding/query1")
    public void query1() {
        Embedding queryEmbedding = embeddingModel.embed("咏鸡说的是什么").content();

        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(1)
                .build();
        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(embeddingSearchRequest);
        System.out.println(searchResult.matches().getFirst().embedded().text());
    }


    @GetMapping(value = "/embedding/query2")
    public void query2(){
        Embedding queryEmbedding = embeddingModel.embed("咏鸡").content();

        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .filter(metadataKey("author").isEqualTo("bubua12"))
                .maxResults(1)
                .build();

        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(embeddingSearchRequest);

        System.out.println(searchResult.matches().getFirst().embedded().text());
    }
}
