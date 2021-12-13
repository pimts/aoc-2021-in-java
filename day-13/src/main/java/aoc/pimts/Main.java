package aoc.pimts;

import java.io.IOException;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = \n" + part2());
    }

    private static int part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();

        List<Dot> dots = parseDots(input);
        List<FoldInstruction> instructions = parseInstructions(input);

        FoldInstruction foldInstruction = instructions.get(0);
        dots = performFoldInstruction(dots, foldInstruction);

        return dots.size();
    }

    private static String part2() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();

        List<Dot> dots = parseDots(input);
        List<FoldInstruction> instructions = parseInstructions(input);

        for (FoldInstruction foldInstruction : instructions) {
            dots = performFoldInstruction(dots, foldInstruction);
        }

        return printManualCode(dots);
    }

    private static List<Dot> parseDots(List<String> input) {
        return input.stream()
                .takeWhile(s -> !s.isEmpty())
                .map(s -> s.split(","))
                .map(s -> List.of(Integer.parseInt(s[0]), Integer.parseInt(s[1])))
                .map(l -> new Dot(l.get(0), l.get(1)))
                .collect(Collectors.toList());
    }

    private static List<FoldInstruction> parseInstructions(List<String> input) {
        return input.stream()
                .dropWhile(s -> !s.startsWith("fold"))
                .map(s -> s.split(" ")[2])
                .map(s -> s.split("="))
                .map(s -> new FoldInstruction(Integer.parseInt(s[1]), s[0]))
                .collect(Collectors.toList());
    }

    private static List<Dot> performFoldInstruction(List<Dot> dots, FoldInstruction foldInstruction) {
        switch (foldInstruction.getAxis()) {
            case "x":
                dots = foldOverX(foldInstruction.getValue(), dots);
                break;
            case "y":
                dots = foldOverY(foldInstruction.getValue(), dots);
                break;
            default:
                throw new IllegalArgumentException("No folding possible for instruction: " + foldInstruction.getAxis());
        }
        return dots;
    }

    private static List<Dot> foldOverY(int value, List<Dot> dots) {
        dots.stream()
                .filter(dot -> dot.getY() > value)
                .forEach(dot -> dot.setY(2 * value - dot.getY()));
        return dots.stream()
                .filter(dot -> dot.getX() >= 0 || dot.getY() >= 0)
                .distinct()
                .collect(Collectors.toList());
    }

    private static List<Dot> foldOverX(int value, List<Dot> dots) {
        dots.stream()
                .filter(dot -> dot.getX() > value)
                .forEach(dot -> dot.setX(2 * value - dot.getX()));
        return dots.stream()
                .filter(dot -> dot.getX() >= 0 || dot.getY() >= 0)
                .distinct()
                .collect(Collectors.toList());
    }

    private static String printManualCode(List<Dot> dots) {
        OptionalInt maxX = dots.stream().map(Dot::getX).mapToInt(Integer::intValue).max();
        OptionalInt maxY = dots.stream().map(Dot::getY).mapToInt(Integer::intValue).max();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= maxY.getAsInt(); i++) {
            for (int j = 0; j <= maxX.getAsInt(); j++) {
                int x = j;
                int y = i;
                if (dots.stream().anyMatch(dot -> dot.getX() == x && dot.getY() == y)) {
                    stringBuilder.append("#");
                } else {
                    stringBuilder.append(".");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
