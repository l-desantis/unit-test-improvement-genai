package it.ringmaster.unittestimprovementgenai.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LlmModelEnum {
    CODE_LLAMA_7B("models/codellama-7b-instruct.Q5_K_M.gguf"),
    LLAMA2_7B("models/llama-2-7b-chat.Q2_K.gguf");

    private final String path;
}
