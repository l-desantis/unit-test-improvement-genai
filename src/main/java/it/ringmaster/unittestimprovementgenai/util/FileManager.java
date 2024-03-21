package it.ringmaster.unittestimprovementgenai.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    public static String loadFromFile(Path filePath) throws IOException {
        return new String(Files.readAllBytes(filePath));
    }
}
