package it.ringmaster.unittestimprovementgenai.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.file.Path;

@AllArgsConstructor
@Getter
public enum LLMModelEnum {
    CODE_LLAMA_7B(
            Path.of("models/codellama-7b-instruct.Q5_K_M.gguf"),
            Path.of("src/main/resources/prompt/prompt1")
            ),

    LLAMA2_7B(
            Path.of("models/llama-2-7b-chat.Q2_K.gguf"),
            Path.of("src/main/resources/prompt/prompt2")
            );

    private final Path path;
    private final Path promptTemplatePath;
}
