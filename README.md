# 🦜🔗 LangChain4j 编码示例项目

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-green)
![License](https://img.shields.io/badge/License-MIT-green)

> **基于 Spring Boot 3.5 & Java 21 的 LangChain4j 学习指南**
>
> 🚀 全面的实战示例集，助你掌握使用 LangChain4j 构建 Java LLM 应用的核心技能。

## 📖 简介

本项目包含一系列 **LangChain4j** 的教程和示例，旨在帮助 Java 开发者将大语言模型 (LLMs) 集成到他们的应用程序中。项目基于 **Spring Boot 3.5.0** 和 **Java 21** 构建，涵盖了从简单的 "Hello World" 到复杂的 RAG（检索增强生成）和 Agent（智能体）模式的各种实战场景。

所有示例主要配置为使用 **阿里云百炼 (DashScope)** 的 OpenAI 兼容接口。

## 🛠️ 技术栈

- **Java**: 21
- **Spring Boot**: 3.5.0
- **LangChain4j**: 1.0.1
- **Spring AI**: 1.0.0
- **构建工具**: Maven

## 📂 模块概览

| 模块 | 描述 | 核心概念 |
|--------|-------------|--------------|
| `langchain4j-01-helloworld` | 🔰 Hello World | 基础设置，OpenAI 接口兼容性 |
| `langchain4j-02-multi-model-together` | 🤖 多模型共存 | 同时使用多个不同的 LLM 模型 |
| `langchain4j-03-boot-integration` | 🍃 Boot 集成 | Spring Boot Starter，声明式 AI 服务 |
| `langchain4j-04-low-high-api` | ⚖️ 低阶 vs 高阶 API | 对比不同抽象层级的 API 使用方式 |
| `langchain4j-05-model-parameters` | ⚙️ 模型参数 | 调整 Temperature, Top-P 等参数 |
| `langchain4j-06-chat-image` | 🎨 图像交互 | 多模态分析 (图像理解) 与生成 |
| `langchain4j-07-chat-stream` | 🌊 流式响应 | 使用 Stream 提升用户体验 |
| `langchain4j-08-chat-memory` | 🧠 聊天记忆 | 处理上下文和对话历史 |
| `langchain4j-09-chat-prompt` | 📝 提示词工程 | Prompt 模板与管理 |
| `langchain4j-10-chat-persistence` | 💾 持久化 | 持久化聊天记录 (需要 **Redis**) |
| `langchain4j-11-chat-functioncalling` | 🔧 函数调用 | LLM 调用 Java 本地方法 (Tools) |
| `langchain4j-12-chat-embedding` | 🔢 Embedding | 文本嵌入与向量化 |
| `langchain4j-13-chat-rag` | 📚 RAG | 检索增强生成 (使用内存向量库) |
| `langchain4j-14-chat-mcp` | 🔌 MCP | Model Context Protocol 集成 |

## 🚀 快速开始

### 前置要求

- 已安装 **JDK 21**
- 已安装 **Maven**
- **Redis** (仅 `langchain4j-10-chat-persistence` 模块需要)
- **阿里云百炼 API Key** (或其他兼容 OpenAI 协议的 Key)

### 环境配置

大多数示例配置为使用 **阿里云百炼 (DashScope)** 服务。你需要设置以下环境变量：

```bash
# Linux/macOS
export BAILIAN_API_KEY="sk-xxxxxxxxxxxxxxxx"

# Windows PowerShell
$env:BAILIAN_API_KEY="sk-xxxxxxxxxxxxxxxx"
```

*注意：代码中使用 `System.getenv("BAILIAN_API_KEY")` 获取密钥。请确保在 IDE 的运行配置或终端会话中正确设置了该变量。*

### 运行示例

1. **克隆仓库:**
   ```bash
   git clone https://github.com/your-username/langchain4J-2026.git
   cd langchain4J-2026
   ```

2. **构建项目:**
   ```bash
   mvn clean install
   ```

3. **运行模块:**
   进入某个模块目录并运行 Spring Boot 应用。
   ```bash
   cd langchain4j-01-helloworld
   mvn spring-boot:run
   ```
   或者直接在 IDE 中运行主类 `HelloLangchain4JApplication`。

## 📝 配置说明

默认配置的模型是 `qwen3-max-preview` (通义千问)。你可以在每个模块的 `LLMConfig.java` 中修改模型名称或 Base URL。

```java
@Bean
public ChatModel chatModelQwen() {
    return OpenAiChatModel.builder()
            .apiKey(System.getenv("BAILIAN_API_KEY"))
            .modelName("qwen3-max-preview")
            .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
            .build();
}
```

## ❓ 常见问题

- **API Key 缺失**: 如果看到关于 missing API keys 的错误，请检查环境变量 `BAILIAN_API_KEY` 是否已正确设置。
- **Redis 连接错误**: 运行模块 10 时，请确保本地 Redis 服务已启动并在默认端口 6379 监听。

## 🤝 贡献

欢迎提交 Pull Request 来完善这个项目！

## 👤 作者

- **bubua12**

---
*Happy Coding with LangChain4j!* 🚀
