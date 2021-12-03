import aoc.pimts.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = " + part2());
    }

    private static int part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> strings = fileUtils.readLinesAsString();
        int wordLength = strings.get(0).length();

        StringBuilder gammaRateString = new StringBuilder();
        StringBuilder epsilonString = new StringBuilder();
        for (int i = 0; i < wordLength; i++) {
            int index = i;
            List<String> stringsCopy = new ArrayList<>(strings);
            stringsCopy.removeIf(s -> s.charAt(index) != '1');
            if (stringsCopy.size() > strings.size() / 2) {
                gammaRateString.append("1");
                epsilonString.append("0");
            } else {
                gammaRateString.append("0");
                epsilonString.append("1");
            }
        }
        int gammaRate = Integer.parseInt(gammaRateString.toString(), 2);
        int epsilonRate = Integer.parseInt(epsilonString.toString(), 2);
        return gammaRate * epsilonRate;
    }

    private static int part2() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> strings = fileUtils.readLinesAsString();

        List<String> oxygenGeneratorRatings = new ArrayList<>(strings);
        int oxygenGeneratorRating = calculateOxygenGeneratorRating(oxygenGeneratorRatings);

        List<String> co2ScrubberRatings = new ArrayList<>(strings);
        int co2ScrubberRating = calculateCO2ScrubberRating(co2ScrubberRatings);
        return oxygenGeneratorRating * co2ScrubberRating;
    }

    private static int calculateOxygenGeneratorRating(List<String> oxygenGeneratorRatings) {
        int wordLength = oxygenGeneratorRatings.get(0).length();
        for (int i = 0; i < wordLength; i++) {
            int index = i;
            long oxygen = oxygenGeneratorRatings.stream()
                    .filter(s -> s.charAt(index) == '1')
                    .count();

            char nextOxygenBit = '0';
            if (oxygen >= oxygenGeneratorRatings.size() / 2.0) {
                nextOxygenBit = '1';
            }

            char finalNextOxygenBit = nextOxygenBit;
            if (oxygenGeneratorRatings.size() > 1) {
                oxygenGeneratorRatings.removeIf(s -> s.charAt(index) != finalNextOxygenBit);
            }
        }
        return Integer.parseInt(oxygenGeneratorRatings.get(0), 2);
    }

    private static int calculateCO2ScrubberRating(List<String> co2ScrubberRatings) {
        int wordLength = co2ScrubberRatings.get(0).length();
        for (int i = 0; i < wordLength; i++) {
            int index = i;
            long co2ScrubberRatingCount = co2ScrubberRatings.stream()
                    .filter(s -> s.charAt(index) == '0')
                    .count();

            char nextCO2Bit = '1';
            if (co2ScrubberRatingCount <= co2ScrubberRatings.size() / 2.0) {
                nextCO2Bit = '0';
            }

            char finalNextCO2Bit = nextCO2Bit;
            if (co2ScrubberRatings.size() > 1) {
                co2ScrubberRatings.removeIf(s -> s.charAt(index) != finalNextCO2Bit);
            }
        }
        return Integer.parseInt(co2ScrubberRatings.get(0), 2);
    }
}
