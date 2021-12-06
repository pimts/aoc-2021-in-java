package aoc.pimts;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = " + part2());
    }

    private static int part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        List<Line> lines = parseLinesFromInput(input);
        Field field = new Field(Point.getMaxX() + 1, Point.getMaxY() + 1);
        List<Line> nonDiagonalLines = lines.stream()
                .filter(line -> !line.isDiagonal())
                .collect(Collectors.toList());
        nonDiagonalLines.forEach(field::addLine);
        return field.getNumberOfDangerousAreas();
    }

    private static int part2() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        List<Line> lines = parseLinesFromInput(input);
        Field field = new Field(Point.getMaxX() + 1, Point.getMaxY() + 1);
        lines.forEach(field::addLine);
        return field.getNumberOfDangerousAreas();
    }

    private static List<Line> parseLinesFromInput(List<String> input) {
        return input.stream()
                .map(s -> s.split(" -> "))
                .map(strings -> {
                    int x1 = Integer.parseInt(strings[0].split(",")[0]);
                    int y1 = Integer.parseInt(strings[0].split(",")[1]);
                    int x2 = Integer.parseInt(strings[1].split(",")[0]);
                    int y2 = Integer.parseInt(strings[1].split(",")[1]);
                    Point point1 = new Point(x1, y1);
                    Point point2 = new Point(x2, y2);
                    return new Line(point1, point2);
                })
                .collect(Collectors.toList());
    }
}
