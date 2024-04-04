package it.ringmaster.unittestimprovementgenai.service;

import it.ringmaster.unittestimprovementgenai.enums.LLMModelEnum;
import it.ringmaster.unittestimprovementgenai.factory.LLMFactory;
import it.ringmaster.unittestimprovementgenai.model.LLMModel;
import it.ringmaster.unittestimprovementgenai.model.TestClass;
import it.ringmaster.unittestimprovementgenai.util.FileManager;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ProgressBar;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.IntStream;

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

    public String generate(String prompt) throws IOException {
        log.info("Providing the prompt to the model...");
        return model.generate(prompt,true);
    }

    public void improveTestClass(Path srcPath, Path dstPath, int impSteps) {
        TestClass testClass = new TestClass(srcPath);
        try (ProgressBar pb = new ProgressBar("Test Class Improvement...", 100)) {
            IntStream.range(0,impSteps).forEach(v -> {
                try {
                    testClass.appendImprovedClass(model.generate(testClass.getLastClassVersion(), false));
                    FileManager.writeToFile(dstPath, testClass.getLastClassVersion());
                } catch (IOException e) {
                    log.error("Error in test improving", e);
                }
                pb.stepBy(100/impSteps);
            });
        }

    }
}
