package aoc.pimts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = " + part2());
    }

    private static int part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        input.add(0, "9".repeat(input.get(0).length()));
        input.add(input.size(), "9".repeat(input.get(0).length()));
        int[][] heightMap = input.stream()
                .map(s -> "9" + s + "9")
                .map(s -> s.chars()
                        .map(Character::getNumericValue)
                        .toArray())
                .toArray(size -> new int[size][1]);

        int sum = 0;
        for (int i = 1; i < heightMap.length - 1; i++) {
            for (int j = 1; j < heightMap[i].length - 1; j++) {
                int lowest = Integer.MAX_VALUE;
                lowest = Math.min(lowest, heightMap[i - 1][j]);
                lowest = Math.min(lowest, heightMap[i][j - 1]);
                lowest = Math.min(lowest, heightMap[i][j + 1]);
                lowest = Math.min(lowest, heightMap[i + 1][j]);
                if (heightMap[i][j] < lowest) {
                    sum += heightMap[i][j] + 1;
                }
            }
        }
        return sum;
    }

    static int[][] heightMap;
    private static int part2() throws IOException {
        FileUtils fileUtils = new FileUtils();

        List<String> input = fileUtils.readLinesAsString();
        input.add(0, "9".repeat(input.get(0).length()));
        input.add(input.size(), "9".repeat(input.get(0).length()));

        heightMap = input.stream()
                .map(s -> "9" + s + "9")
                .map(s -> s.chars()
                        .map(Character::getNumericValue)
                        .toArray())
                .toArray(size -> new int[size][1]);

        List<Position> lowestPositions = new ArrayList<>();
        for (int i = 1; i < heightMap.length - 1; i++) {
            for (int j = 1; j < heightMap[i].length - 1; j++) {
                int lowest = Integer.MAX_VALUE;
                lowest = Math.min(lowest, heightMap[i - 1][j]);
                lowest = Math.min(lowest, heightMap[i][j - 1]);
                lowest = Math.min(lowest, heightMap[i][j + 1]);
                lowest = Math.min(lowest, heightMap[i + 1][j]);
                if (heightMap[i][j] < lowest) {
                    lowestPositions.add(new Position(j, i));
                }
            }
        }

        return lowestPositions.stream()
                .map(p -> expandBasin(p.getX(), p.getY()))
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(1, (acc, it) -> acc *= it);
    }

    private static int expandBasin(int x, int y) {
        if (heightMap[y][x] == 9) {
            return 0;
        } else {
            heightMap[y][x] = 9;
            return 1 + expandBasin(x, y - 1) + expandBasin(x - 1, y) + expandBasin(x + 1, y) + expandBasin(x, y + 1);
        }
    }
}
