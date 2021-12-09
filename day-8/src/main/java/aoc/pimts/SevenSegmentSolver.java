package aoc.pimts;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SevenSegmentSolver {

    private final List<String> output;
    private final SevenSegment one;
    private final SevenSegment four;

    public SevenSegmentSolver(String input) {
        String signalPatterns = input.split(" \\| ")[0];
        List<String> signalPatterns1 = Arrays.stream(signalPatterns.split(" "))
                .sorted((Comparator.comparingInt(String::length)))
                .collect(Collectors.toList());

        String outputValues = input.split(" \\| ")[1];
        this.output = List.of(outputValues.split(" "));

        this.one = new SevenSegment(signalPatterns1.get(0));
        this.four = new SevenSegment(signalPatterns1.get(2));
    }

    public int solve() {
        StringBuilder result = new StringBuilder();
        for (String s : output) {
            if (s.length() == 2) {
                result.append("1");
            } else if (s.length() == 3) {
                result.append("7");
            } else if (s.length() == 4) {
                result.append("4");
            } else if (s.length() == 7) {
                result.append("8");
            } else if (s.length() == 5) {
                if (one.isContainedBy(s)) {
                    result.append("3");
                } else if (four.differences(s) == 1) {
                    result.append("5");
                } else {
                    result.append("2");
                }
            } else if (s.length() == 6) {
                if (four.isContainedBy(s)) {
                    result.append("9");
                } else if (!one.isContainedBy(s) && !four.isContainedBy(s)) {
                    result.append("6");
                } else {
                    result.append("0");
                }
            }
        }
        return Integer.parseInt(result.toString());
    }
}
