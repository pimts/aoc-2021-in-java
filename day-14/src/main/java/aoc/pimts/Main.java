package aoc.pimts;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = " + part2());
    }

    private static long part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();

        String polymerTemplate = input.get(0);
        List<String> rulesInput = input.subList(2, input.size());

        Map<String, String> insertionRules = getInsertionRules(rulesInput);
        Map<String, Long> elementCount = getInitialElementCount(polymerTemplate);
        Map<String, Long> polymerPairs = getInitialPairs(polymerTemplate);

        for (int i = 0; i < 10; i++) {
            polymerPairs = performInsertionStep(insertionRules, elementCount, polymerPairs);
        }

        OptionalLong min = elementCount.values().stream().mapToLong(Long::longValue).min();
        OptionalLong max = elementCount.values().stream().mapToLong(Long::longValue).max();

        return max.getAsLong() - min.getAsLong();
    }

    private static long part2() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();

        String polymerTemplate = input.get(0);
        List<String> rulesInput = input.subList(2, input.size());

        Map<String, String> insertionRules = getInsertionRules(rulesInput);
        Map<String, Long> elementCount = getInitialElementCount(polymerTemplate);
        Map<String, Long> polymerPairs = getInitialPairs(polymerTemplate);

        for (int i = 0; i < 40; i++) {
            polymerPairs = performInsertionStep(insertionRules, elementCount, polymerPairs);
        }

        OptionalLong min = elementCount.values().stream().mapToLong(Long::longValue).min();
        OptionalLong max = elementCount.values().stream().mapToLong(Long::longValue).max();

        return max.getAsLong() - min.getAsLong();
    }

    private static Map<String, String> getInsertionRules(List<String> rulesList) {
        Map<String, String> insertionRules = new HashMap<>();
        rulesList.stream()
                .map(s -> s.split(" -> "))
                .forEach(s -> insertionRules.put(s[0], s[1]));
        return insertionRules;
    }

    private static Map<String, Long> getInitialElementCount(String polymerTemplate) {
        Map<String, Long> elementCount = new HashMap<>();
        for (char c : polymerTemplate.toCharArray()) {
            elementCount.put(String.valueOf(c), 1L);
        }
        return elementCount;
    }

    private static Map<String, Long> getInitialPairs(String polymerTemplate) {
        Map<String, Long> polymerPairs = new HashMap<>();
        for (int i = 0; i < polymerTemplate.length() - 1; i++) {
            String pair = polymerTemplate.substring(i, i + 2);
            long count = polymerPairs.getOrDefault(pair, 0L);
            polymerPairs.put(pair, ++count);
        }
        return polymerPairs;
    }

    private static Map<String, Long> performInsertionStep(Map<String, String> insertionRules, Map<String, Long> elementCount, Map<String, Long> polymerPairs) {
        Map<String, Long> newPolymerPairs = new HashMap<>();
        for (Map.Entry<String, Long> pair : polymerPairs.entrySet()) {
            String element = insertionRules.get(pair.getKey());
            String leftElement = String.valueOf(pair.getKey().charAt(0));
            String rightElement = String.valueOf(pair.getKey().charAt(1));
            Long count = pair.getValue();

            String newPair1 = leftElement + element;
            String newPair2 = element + rightElement;

            newPolymerPairs.put(newPair1, newPolymerPairs.getOrDefault(newPair1, 0L) + count);
            newPolymerPairs.put(newPair2, newPolymerPairs.getOrDefault(newPair2, 0L) + count);
            elementCount.put(element, elementCount.getOrDefault(element, 0L) + count);
        }
        return newPolymerPairs;
    }
}
