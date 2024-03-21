package it.ringmaster.unittestimprovementgenai;

import it.ringmaster.unittestimprovementgenai.enums.LLMModelEnum;
import it.ringmaster.unittestimprovementgenai.service.LLMService;
import it.ringmaster.unittestimprovementgenai.util.FileManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Time;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

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

       /* try{
            llmService.startChat();
        } catch (IOException e) {
            log.info(e.getMessage());
        }*/
        Long t1= System.currentTimeMillis();
        String inputTestClass = FileManager.loadFromFile(Path.of("files/UserTest.java"));
        String inputClass = FileManager.loadFromFile(Path.of("files/UserController.java"));
        String result = llmService.generate(inputClass,inputTestClass);
        log.info(result);
        Long t2= System.currentTimeMillis();
        System.out.println(TimeUnit.MILLISECONDS.toMinutes(t2-t1));
    }
}
