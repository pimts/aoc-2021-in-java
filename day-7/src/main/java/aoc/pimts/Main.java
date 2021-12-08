package aoc.pimts;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = " + part2());
    }

    private static int part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        List<Integer> crabPositions = Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
        int median;
        if (crabPositions.size() % 2 != 0) {
            median = crabPositions.get(crabPositions.size() / 2);
        } else {
            median = (int) crabPositions.stream()
                    .skip((crabPositions.size() / 2) - 1)
                    .limit(2)
                    .mapToInt(Integer::intValue)
                    .average().getAsDouble();
        }
        return crabPositions.stream()
                .reduce(0, (acc, it) -> acc += Math.abs(median - it));
    }

    private static int part2() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        List<Integer> crabPositions = Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());
        int max = crabPositions.get(crabPositions.size() - 1);
        return IntStream.rangeClosed(0, max)
                .map(position -> crabPositions.stream().reduce(0, (acc, it) -> acc += ((Math.abs(it - position) * (Math.abs(it - position) + 1))) / 2))
                .min().getAsInt();
    }
}