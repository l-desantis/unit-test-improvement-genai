package it.ringmaster.unittestimprovementgenai;

import it.ringmaster.unittestimprovementgenai.enums.LLMModelEnum;
import it.ringmaster.unittestimprovementgenai.model.TestClass;
import it.ringmaster.unittestimprovementgenai.service.LLMService;
import it.ringmaster.unittestimprovementgenai.util.FileManager;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ProgressBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.IntStream;

@SpringBootApplication
@Slf4j
public class UnitTestImprovementGenaiApplication implements CommandLineRunner {

    private final LLMService llmService;

    @Autowired
    public UnitTestImprovementGenaiApplication(LLMService llmService) {
        this.llmService = llmService;
    }

    public static void main(String[] args) {
        SpringApplication.run(UnitTestImprovementGenaiApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        try{
//            llmService.startChat();
//        } catch (IOException e) {
//            log.info(e.getMessage());
//        }

//        String inputTestClass = FileManager.loadFromFile(Path.of("files/MyClassTest.java"));
//        String result = llmService.generate(inputTestClass);
//        log.info(result);
        llmService.improveTestClass(Path.of("files/BookTest.java"),
                Path.of("files/BookTest.imp.java"),4);
    }
}
