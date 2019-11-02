package hu.testathon.model.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DataWriter {

    private final String fileName;

    public DataWriter(String fileName) {
        this.fileName = fileName;
    }

    public void printAll(List<String> lines) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            lines.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printAll2(List<String> lines) {
        try {
            Files.write(Paths.get(fileName), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
