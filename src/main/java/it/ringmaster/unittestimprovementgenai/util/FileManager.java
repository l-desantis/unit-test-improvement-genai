package it.ringmaster.unittestimprovementgenai.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
    public static String loadFromFile(Path filePath) throws IOException {
        return new String(Files.readAllBytes(filePath));
    }
    public static void writeToFile(Path filePath, String input) throws IOException {
        Files.write(filePath, input.getBytes());
    }
}
