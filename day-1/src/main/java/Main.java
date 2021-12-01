import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = " + part2());
    }

    // Part 1
    private static int part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<Integer> depthsMeasured = fileUtils.readLinesAsInteger();
        return findNumberOfDepthIncreases(depthsMeasured);
    }

    private static int findNumberOfDepthIncreases(List<Integer> depthsMeasured) {
        int numberOfIncreases = 0;
        for (int i = 0; i < depthsMeasured.size() - 1; i++) {
            if (depthsMeasured.get(i + 1) > depthsMeasured.get(i)) {
                numberOfIncreases++;
            }
        }
        return numberOfIncreases;
    }

    //Part 2
    private static int part2() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<Integer> depthsMeasured = fileUtils.readLinesAsInteger();
        List<Window> slidingWindows = createWindows(depthsMeasured);
        List<Integer> totals = slidingWindows.stream()
                .map(Window::getSumOfParts)
                .collect(Collectors.toList());
        return findNumberOfDepthIncreases(totals);
    }

    private static List<Window> createWindows(List<Integer> depthsMeasured) {
        List<Window> slidingWindows = new ArrayList<>();
        for (int i = 0; i < depthsMeasured.size() - 2; i++) {
            int part1 = depthsMeasured.get(i);
            int part2 = depthsMeasured.get(i + 1);
            int part3 = depthsMeasured.get(i + 2);
            Window slidingWindow = new Window(part1, part2, part3);
            slidingWindows.add(slidingWindow);
        }
        return slidingWindows;
    }
}
