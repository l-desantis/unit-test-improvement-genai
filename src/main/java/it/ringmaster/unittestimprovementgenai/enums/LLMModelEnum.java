package it.ringmaster.unittestimprovementgenai.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LLMModelEnum {
    CODE_LLAMA_7B("models/codellama-7b-instruct.Q5_K_M.gguf",
            "[INST] You are an expert Java programmer that helps to improve unit test coverage. " + "I will provide the Java test class wrapped with \\{\\}. " + " improve the Java test class as much as possible and write the code. Please wrap your code answer using ``` .You only write java code and dont explain the code you are writing,stop after you write the code :\\n{%s}\\n [/INST]"
                           ),

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
