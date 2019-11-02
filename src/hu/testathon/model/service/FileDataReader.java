package hu.testathon.model.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileDataReader {

    private final String input;

    public FileDataReader(String input) {
        this.input = input;
    }

    List<String> read() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            lines = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    List<String> read2() {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
