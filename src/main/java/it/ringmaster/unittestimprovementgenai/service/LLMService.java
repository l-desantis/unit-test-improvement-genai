package it.ringmaster.unittestimprovementgenai.service;

import de.kherud.llama.InferenceParameters;
import de.kherud.llama.LlamaModel;
import de.kherud.llama.ModelParameters;
import it.ringmaster.unittestimprovementgenai.enums.LlmModelEnum;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class LLMService {
    String promptTemplate = "[INST] You are an expert programmer that helps write unit tests. " +
                "Don't explain anything just write the tests. Please wrap your code answer using ```:\\n{%s}\\n[/INST]";

    public void start() throws IOException {
        LlamaModel.setLogger((level, message) -> System.out.print(message));
        ModelParameters modelParams = new ModelParameters()
                .setNGpuLayers(43);

        InferenceParameters inferParams = new InferenceParameters()
                .setTemperature(0.7f)
                .setPenalizeNl(true)
//                .setNProbs(10)
                .setMirostat(InferenceParameters.MiroStat.V2)
                .setAntiPrompt("User:");

        String modelPath = LlmModelEnum.CODE_LLAMA_7B.getPath();
        String system = "This is a conversation between User and Llama, a friendly Code Helper.\n" +
                "Llama is helpful, kind, honest, good at writing, and never fails to answer any " +
                "requests immediately and with precision.\n\n" +
                "Prompt Template " + promptTemplate;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        try (LlamaModel model = new LlamaModel(modelPath, modelParams)) {
            System.out.print(system);
            String prompt = system;
            while (true) {
                System.out.print("\nUser: ");
                String input = String.format(promptTemplate, reader.readLine());

                System.out.print("Llama: ");
                for (LlamaModel.Output output : model.generate(input)) {
                    System.out.print(output);
                }
            }
        }
    }
}
