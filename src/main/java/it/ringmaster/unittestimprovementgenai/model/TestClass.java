package it.ringmaster.unittestimprovementgenai.model;

import com.google.common.collect.Iterables;
import it.ringmaster.unittestimprovementgenai.util.FileManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class TestClass {
    private List<String> testClasses = new ArrayList<>();

    public TestClass(Path filePath) {
        try {
            testClasses.add(FileManager.loadFromFile(filePath));
        } catch (IOException e) {
            log.error("Error loading test class", e);
        }
    }

    public void appendImprovedClass(String f) {
        testClasses.add(f);
    }

    public String getLastClassVersion() {
        return Iterables.getLast(testClasses);
    }
}
