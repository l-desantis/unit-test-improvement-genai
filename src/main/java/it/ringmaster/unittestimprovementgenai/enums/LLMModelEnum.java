package it.ringmaster.unittestimprovementgenai.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LLMModelEnum {
    CODE_LLAMA_7B("models/codellama-7b-instruct.Q5_K_M.gguf",
            "[INST] You are an expert programmer that helps write unit tests. " +
            "Don't explain anything just write the tests. Please wrap your code answer using ```:\\n{%s}\\n[/INST]"),

    LLAMA2_7B("models/llama-2-7b-chat.Q2_K.gguf",
            "[INST] <<SYS>>\n" +
                    "You are a helpful, respectful and honest assistant. Always answer as helpfully as possible, " +
                    "while being safe.  Your answers should not include any harmful, unethical, racist, sexist, toxic," +
                    " dangerous, or illegal content. Please ensure that your responses are socially unbiased and " +
                    "positive in nature. If a question does not make any sense, or is not factually coherent, " +
                    "explain why instead of answering something not correct. If you don't know the answer to a question," +
                    " please don't share false information.\n" +
                    "<</SYS>>\n{prompt}[/INST]");

    private final String path;
    private final String promptTemplate;
}
