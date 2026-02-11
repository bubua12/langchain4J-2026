package com.bubua12.langchain4j.prompt.entities;

import dev.langchain4j.model.input.structured.StructuredPrompt;
import lombok.Data;

/**
 * 第二组提示词模板 新建带有@StructuredPrompt的业务实体类
 *
 * @author bubua12
 * @since 2026/2/11 17:00
 */
@Data
@StructuredPrompt("根据中国{{legal}}法律，解答以下问题：{{question}}")
public class LawPrompt {
    private String legal;
    private String question;
}