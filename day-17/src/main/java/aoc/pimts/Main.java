package aoc.pimts;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = " + part2());
    }

    private static long part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        String[] split = input.get(0).replaceAll("target area: ", "").split(", ");
        String[] y_values = split[1].replaceAll("y=", "").split("\\.\\.");
        int minY = Integer.parseInt(y_values[0]);
        return IntStream.range(0, Math.abs(minY)).sum();
    }

    static int maxX;
    static int minX;
    static int maxY;
    static int minY;

    private static long part2() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        String[] split = input.get(0).replaceAll("target area: ", "").split(", ");
        String[] x_values = split[0].replaceAll("x=", "").split("\\.\\.");
        String[] y_values = split[1].replaceAll("y=", "").split("\\.\\.");

        minY = Integer.parseInt(y_values[0]);
        maxY = Integer.parseInt(y_values[1]);
        minX = Integer.parseInt(x_values[0]);
        maxX = Integer.parseInt(x_values[1]);

        int hits = 0;
        for (int yVelocity = minY; yVelocity < -minY + 1; yVelocity++) {
            for (int xVelocity = findMinXVelocity(minX); xVelocity < maxX + 1; xVelocity++) {
                if (launch(xVelocity, yVelocity)) {
                    hits++;
                }
            }
        }
        return hits;
    }

    private static int findMinXVelocity(int minX) {
        int sum = 0;
        int minVx = 0;
        for (int i = 0; i < minX; i++) {
            sum += i;
            if (sum >= minX) {
                minVx = i;
                break;
            }
        }
        return minVx;
    }

    private static boolean launch(int xVelocity, int yVelocity) {
        int x = 0;
        int y = 0;
        while (x <= maxX && y >= minY) {
            x += xVelocity;
            y += yVelocity;
            if (xVelocity > 0) {
                xVelocity--;
            } else if (xVelocity < 0) {
                xVelocity++;
            }
            yVelocity--;
            if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
                return true;
            }
        }
        return false;
    }
}
