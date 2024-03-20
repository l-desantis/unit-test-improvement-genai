package it.ringmaster.unittestimprovementgenai.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileManager {
    public static String readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);

        StringBuilder result = new StringBuilder();
        while (sc.hasNextLine()) {
            result.append(sc.nextLine());
        }
        return result.toString();
    }
}
