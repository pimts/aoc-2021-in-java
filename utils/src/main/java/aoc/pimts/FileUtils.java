package aoc.pimts;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    private final File inputFile;

    public FileUtils() {
        // The puzzle input is always located in the day specific module's resource folder
        // under the name: input.txt
        URL inputFileURL = this.getClass().getClassLoader().getResource("input.txt");
        if (inputFileURL == null) {
            throw new IllegalStateException("Could not load input.txt from resources folder!");
        }
        inputFile = new File(inputFileURL.getFile());
    }

    public List<String> readLinesAsString() throws IOException {
        return Files.readAllLines(inputFile.toPath());
    }

    public List<Integer> readLinesAsInteger() throws IOException {
        return readLinesAsString().stream()
                .map((Integer::parseInt))
                .collect(Collectors.toList());
    }

    public List<Double> readLinesAsDouble() throws IOException {
        return readLinesAsString().stream()
                .map((Double::parseDouble))
                .collect(Collectors.toList());
    }
}
