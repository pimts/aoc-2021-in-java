package aoc.pimts;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = " + part2());
    }

    private static long part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        String initialState = input.get(0);
        List<LanternFish> initialSchool = Arrays.stream(initialState.split(","))
                .map(Integer::parseInt)
                .map(LanternFish::new)
                .collect(Collectors.toList());
        SchoolOfFish schoolOfFish = new SchoolOfFish(initialSchool);
        for (int i = 0; i < 80; i++) {
            schoolOfFish.grow();
        }
        return schoolOfFish.getSizeOfSchool();
    }

    private static long part2() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        String initialState = input.get(0);
        List<LanternFish> initialSchool = Arrays.stream(initialState.split(","))
                .map(Integer::parseInt)
                .map(LanternFish::new)
                .collect(Collectors.toList());
        SchoolOfFish schoolOfFish = new SchoolOfFish(initialSchool);
        for (int i = 0; i < 256; i++) {
            schoolOfFish.grow();
        }
        return schoolOfFish.getSizeOfSchool();
    }
}
