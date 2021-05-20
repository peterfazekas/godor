package hu.pit.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileWriter {

    private final String filename;

    public FileWriter(String filename) {
        this.filename = filename;
    }

    public void writeAll(List<String> lines) {
        try {
            Files.write(Path.of(filename), lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
