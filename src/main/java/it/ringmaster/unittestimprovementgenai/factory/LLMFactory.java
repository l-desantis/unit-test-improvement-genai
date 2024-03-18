package it.ringmaster.unittestimprovementgenai.factory;

import it.ringmaster.unittestimprovementgenai.enums.LLMModelEnum;
import it.ringmaster.unittestimprovementgenai.model.LLMModel;

public class LLMFactory {
    public LLMModel createModel(LLMModelEnum modelEnum) {
        LLMModel model = new LLMModel(modelEnum);
        model.setup();
        return model;
    }
}
