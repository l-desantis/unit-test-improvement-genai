package it.ringmaster.unittestimprovementgenai.model;

import de.kherud.llama.InferenceParameters;
import de.kherud.llama.LlamaModel;
import de.kherud.llama.ModelParameters;
import it.ringmaster.unittestimprovementgenai.enums.LLMModelEnum;
import it.ringmaster.unittestimprovementgenai.util.FileManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Slf4j
public class LLMModel {

    private final LLMModelEnum llmModelEnum;
    private LLMModelSettings modelSettings;

    public void setup() {
        LlamaModel.setLogger((level, message) -> System.out.print(message));
        ModelParameters modelParams = new ModelParameters()
                .setNGpuLayers(43)
                .setNCtx(4096);

        InferenceParameters inferParams = new InferenceParameters()
                .setTemperature(0.7f)
                .setPenalizeNl(true)
//                .setNProbs(10)
                .setMirostat(InferenceParameters.MiroStat.V2)

                .setAntiPrompt("User:");

        String system = "This is a conversation between User and Llama, a friendly Code Helper.\n" +
                "Llama is helpful, kind, honest, good at writing, and never fails to answer any " +
                "requests immediately and with precision.\n\n" +
                "Prompt Template " + llmModelEnum.getPromptTemplatePath();
        modelSettings = new LLMModelSettings(modelParams, inferParams, system);
    }

    public void startChat() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

        String promptTemplate = FileManager.loadFromFile(llmModelEnum.getPromptTemplatePath());
        log.info(String.format("Prompt template: \n %s", promptTemplate));

        try (LlamaModel model = new LlamaModel(llmModelEnum.getPath().toString(), modelSettings.getModelParams())) {
            System.out.print(modelSettings.getSystem());
            String prompt = modelSettings.getSystem();
            while (true) {
                System.out.print("\nUser: ");
                String input = String.format(promptTemplate, reader.readLine());

                System.out.print("Llama: ");
                for (LlamaModel.Output output : model.generate(input, modelSettings.getInferParams())) {
                    System.out.print(output);
                }
            }
        }
    }

    public String generate(String prompt,String test) throws IOException {
        String promptTemplate = FileManager.loadFromFile(llmModelEnum.getPromptTemplatePath());
        log.info(String.format("Prompt template: \n %s", promptTemplate));

        StringBuilder result = new StringBuilder();
        try (LlamaModel model = new LlamaModel(llmModelEnum.getPath().toString(), modelSettings.getModelParams())) {
            prompt = String.format(promptTemplate, prompt,test);
            for (LlamaModel.Output output : model.generate(prompt, modelSettings.getInferParams())) {
                System.out.print(output);
                result.append(output);
            }
        }
        return result.toString();
    }

    @AllArgsConstructor
    @Getter
    private static class LLMModelSettings {
        private ModelParameters modelParams;
        private InferenceParameters inferParams;
        private String system;
    }
}
