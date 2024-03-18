package it.ringmaster.unittestimprovementgenai.service;

import it.ringmaster.unittestimprovementgenai.enums.LLMModelEnum;
import it.ringmaster.unittestimprovementgenai.factory.LLMFactory;
import it.ringmaster.unittestimprovementgenai.model.LLMModel;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LLMService {
    public void startChat() throws IOException {
        LLMFactory llmFactory = new LLMFactory();
        LLMModel model = llmFactory.createModel(LLMModelEnum.CODE_LLAMA_7B);
        model.startChat();
    }
}
