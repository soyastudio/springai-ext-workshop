package io.spring.ai.configuration;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.transformers.TransformersEmbeddingModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransformerConfig {

    @Bean
    public EmbeddingModel transformersEmbeddingModel() throws Exception {
        TransformersEmbeddingModel embeddingModel = new TransformersEmbeddingModel();
        // Set URIs for the model and tokenizer files (classpath:, file:, or https: URIs supported)
        embeddingModel.setTokenizerResource("classpath:/onnx/abs/tokenizer.json");
        embeddingModel.setModelResource("classpath:/onnx/abs/model.onnx");
        embeddingModel.afterPropertiesSet(); // Important for initialization
        return embeddingModel;
    }
}

