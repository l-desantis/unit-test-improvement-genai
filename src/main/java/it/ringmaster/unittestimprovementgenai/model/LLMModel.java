package it.ringmaster.unittestimprovementgenai.model;

import de.kherud.llama.InferenceParameters;
import de.kherud.llama.LlamaModel;
import de.kherud.llama.ModelParameters;
import it.ringmaster.unittestimprovementgenai.enums.LLMModelEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class LLMModel {

    private final LLMModelEnum llmModelEnum;
    private ModelSettings modelSettings;

    public void setup() {
        LlamaModel.setLogger((level, message) -> System.out.print(message));
        ModelParameters modelParams = new ModelParameters()
                .setNGpuLayers(43);

        InferenceParameters inferParams = new InferenceParameters()
                .setTemperature(0.7f)
                .setPenalizeNl(true)
//                .setNProbs(10)
                .setMirostat(InferenceParameters.MiroStat.V2)
                .setAntiPrompt("User:");

        String system = "This is a conversation between User and Llama, a friendly Code Helper.\n" +
                "Llama is helpful, kind, honest, good at writing, and never fails to answer any " +
                "requests immediately and with precision.\n\n" +
                "Prompt Template " + llmModelEnum.getPromptTemplate();
        modelSettings = new ModelSettings(modelParams, inferParams, system);
    }

    public void startChat() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        try (LlamaModel model = new LlamaModel(llmModelEnum.getPath(), modelSettings.getModelParams())) {
            System.out.print(modelSettings.getSystem());
            String prompt = modelSettings.getSystem();
            while (true) {
                System.out.print("\nUser: ");
                String input = String.format(llmModelEnum.getPromptTemplate(), reader.readLine());

                System.out.print("Llama: ");
                for (LlamaModel.Output output : model.generate(input, modelSettings.getInferParams())) {
                    System.out.print(output);
                }
            }
        }
    }

    @AllArgsConstructor
    @Getter
    private static class ModelSettings {
        private ModelParameters modelParams;
        private InferenceParameters inferParams;
        private String system;
    }
}
