package it.ringmaster.unittestimprovementgenai;

import it.ringmaster.unittestimprovementgenai.service.LLMService;
import it.ringmaster.unittestimprovementgenai.util.FileManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

        String inputTestClass = FileManager.readFile("files/MyClassTest.java");
        String result = llmService.generate(inputTestClass);
        log.info(result);
    }
}
