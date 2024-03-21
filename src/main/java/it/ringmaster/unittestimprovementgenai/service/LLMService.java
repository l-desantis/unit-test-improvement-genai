package it.ringmaster.unittestimprovementgenai.service;

import it.ringmaster.unittestimprovementgenai.enums.LLMModelEnum;
import it.ringmaster.unittestimprovementgenai.factory.LLMFactory;
import it.ringmaster.unittestimprovementgenai.model.LLMModel;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class LLMService {
    private LLMModel model;
    @PostConstruct
    private void init() {
        LLMFactory llmFactory = new LLMFactory();
        model = llmFactory.createModel(LLMModelEnum.CODE_LLAMA_7B);
    }
    public void startChat() throws IOException {
        model.startChat();
    }

    public String generate(String prompt,String prompt2) throws IOException {
        log.info("Providing the prompt to the model");
        return model.generate(prompt,prompt2);
    }
}
