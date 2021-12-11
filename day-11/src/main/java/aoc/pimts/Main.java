package aoc.pimts;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = " + part2());
    }

    static final int ROWS = 10;
    static final int COLUMNS = 10;
    static Octopus[][] cavern = new Octopus[ROWS][COLUMNS];

    private static long part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        createOctopuses(input);
        addAdjacentOctopuses();
        long result = 0;
        for (int i = 0; i < 100; i++) {
            result += step();
        }
        return result;
    }

    private static long part2() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();

        createOctopuses(input);
        addAdjacentOctopuses();

        long stepCounter = 0;
        do {
            stepCounter++;
        } while (step() != 100);
        return stepCounter;
    }

    private static void createOctopuses(List<String> input) {
        for (int y = 0; y < ROWS; y++) {
            String energyLevels = input.get(y);
            for (int x = 0; x < COLUMNS; x++) {
                int energyLevel = Integer.parseInt(String.valueOf(energyLevels.charAt(x)));
                cavern[y][x] = new Octopus(energyLevel);
            }
        }
    }

    private static void addAdjacentOctopuses() {
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (x + j >= 0 && x + j < COLUMNS && y + i >= 0 && y + i < ROWS) {
                            if (cavern[y][x] != cavern[y + i][x + j]) {
                                cavern[y][x].addAdjacentOctopus(cavern[y + i][x + j]);
                            }
                        }
                    }
                }
            }
        }
    }

    private static long step() {
        octopuses().forEach(Octopus::increaseEnergyLevel);

        while (octopuses().anyMatch(octopus -> octopus.getEnergyLevel() > 9 && !octopus.hasFlashed())) {
            octopuses().filter(octopus -> octopus.getEnergyLevel() > 9 && !octopus.hasFlashed())
                    .forEach(octopus -> {
                        octopus.flash();
                        octopus.getAdjacentOctopuses().forEach(Octopus::increaseEnergyLevel);
                    });
        }

        long flashes = octopuses().filter(Octopus::hasFlashed).count();
        octopuses().filter(Octopus::hasFlashed).forEach(Octopus::recharge);

        return flashes;
    }

    private static Stream<Octopus> octopuses() {
        return Arrays.stream(cavern)
                .flatMap(Arrays::stream);
    }
}
