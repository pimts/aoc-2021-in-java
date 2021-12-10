package aoc.pimts;

import java.io.IOException;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = " + part2());
    }

    private static int part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        return input.stream()
                .map(Main::getFirstIllegalCharacter)
                .filter(ch -> ch != 'x')
                .map(Main::getScoreOfIllegalChar)
                .reduce(0, Integer::sum);
    }

    private static long part2() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();

        List<Long> scores = input.stream()
                .filter(l -> getFirstIllegalCharacter(l) == 'x')
                .map(Main::getUnclosedBrackets)
                .map(s -> new StringBuilder(s).reverse())
                .map(s -> s.chars()
                        .map(c -> getScoreOfClosingBracket((char) c))
                        .mapToLong(Integer::toUnsignedLong)
                        .reduce(0, (acc, it) -> acc = acc * 5 + it))
                .sorted()
                .collect(Collectors.toList());

        return scores.get(scores.size() / 2);
    }

    private static String getUnclosedBrackets(String incompleteLine) {
        while (containsClosedChunks(incompleteLine)) {
            incompleteLine = incompleteLine.replaceAll("\\(\\)", "")
                    .replaceAll("\\[\\]", "")
                    .replaceAll("\\{\\}", "")
                    .replaceAll("<>","");
        }
        return incompleteLine;
    }

    private static boolean containsClosedChunks(String incompleteLine) {
        return incompleteLine.contains("()")
                || incompleteLine.contains("[]")
                || incompleteLine.contains("{}")
                || incompleteLine.contains("<>");
    }

    private static char getScoreOfClosingBracket(char c) {
        switch (c) {
            case '(':
                return 1;
            case '[':
                return 2;
            case '{':
                return 3;
            case '<':
                return 4;
            default:
                return 0;
        }
    }

    private static int getScoreOfIllegalChar(char c) {
        switch (c) {
            case ')':
                return 3;
            case ']':
                return 57;
            case '}':
                return 1197;
            case '>':
                return 25137;
            default:
                return 0;
        }
    }

    private static char getFirstIllegalCharacter(String input) {
        Stack<Character> brackets = new Stack<>();
        for (char c : input.toCharArray()) {
            if (c == '(' || c == '[' || c == '{' || c == '<') {
                brackets.push(c);
                continue;
            }

            if (brackets.isEmpty()) {
                return c;
            }

            switch (c) {
                case ')':
                    if (brackets.pop() != '(') {
                        return c;
                    }
                    break;
                case '}':
                    if (brackets.pop() != '{') {
                        return c;
                    }
                    break;
                case ']':
                    if (brackets.pop() != '[') {
                        return c;
                    }
                    break;
                case '>':
                    if (brackets.pop() != '<') {
                        return c;
                    }
                    break;
            }
        }
        return 'x';
    }
}
