package it.ringmaster.unittestimprovementgenai.service;

import de.kherud.llama.InferenceParameters;
import de.kherud.llama.LlamaModel;
import de.kherud.llama.ModelParameters;
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
//        String modelName = System.getProperty("llama-2-7b-chat.Q2_K.gguf");
        String modelPath = "C:\\Users\\utente\\WorkProjects\\Ringmaster\\Techlab\\test-automation-genai\\codellama-7b-instruct.Q5_K_M.gguf";
        String system = "This is a conversation between User and Llama, a friendly Code Helper.\n" +
                "Llama is helpful, kind, honest, good at writing, and never fails to answer any " +
                "requests immediately and with precision.\n\n" +
                "Prompt Template " + promptTemplate;
        ;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        try (LlamaModel model = new LlamaModel(modelPath, modelParams)) {
            System.out.print(system);
            String prompt = system;
            while (true) {
//                prompt += "\nUser: ";
                System.out.print("\nUser: ");
                String input = String.format(promptTemplate, reader.readLine());
//                String response = model.complete(input);
                System.out.print("Llama: ");
                for (LlamaModel.Output output : model.generate(input)) {
                    System.out.print(output);
                }
//                System.out.println(response);
//                prompt += "\nLlama: ";
//                String answer = model.complete(prompt, inferParams);
//                System.out.print(answer);
//                prompt += answer;
//                for (LlamaModel.Output output : model.generate(prompt, inferParams)) {
//                    System.out.print(output);
//                    prompt += output;
//                }
            }
        }
    }
}
