package aoc.pimts;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
    }

    private static long part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        return input.stream()
                .map(s -> s.split(" \\| ")[1])
                .map(s -> s.split(" "))
                .flatMap(Arrays::stream)
                .filter(s -> s.length() == 2 || s.length() == 3 || s.length() == 4 || s.length() == 7)
                .count();
    }
}
