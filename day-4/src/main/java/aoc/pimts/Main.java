package aoc.pimts;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = " + part2());
    }

    private static int part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        Game game = new Game(input.get(0), input.subList(2, input.size()));
        game.play();
        int firstPlace = 0;
        BingoBoard winner = game.getScoreBoard().get(firstPlace);
        return winner.getLastNumberMarked() * winner.getSumOfUnmarkedNumbers();
    }

    private static int part2() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        Game game = new Game(input.get(0), input.subList(2, input.size()));
        game.play();
        int lastPlace = game.getScoreBoard().size() - 1;
        BingoBoard winner = game.getScoreBoard().get(lastPlace);
        return winner.getLastNumberMarked() * winner.getSumOfUnmarkedNumbers();
    }
}
