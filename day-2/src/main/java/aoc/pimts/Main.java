package aoc.pimts;

import aoc.pimts.submarine.ImprovedSubmarine;
import aoc.pimts.submarine.Submarine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = " + part2());
    }


    // Part 1
    private static int part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> commandStrings = fileUtils.readLinesAsString();
        List<Command> commands = parseCommandStrings(commandStrings);
        Submarine submarine = new Submarine();
        for (Command command : commands) {
            submarine.performMovement(command);
        }
        return submarine.getCurrentDepth() * submarine.getCurrentHorizontalPosition();
    }

    private static List<Command> parseCommandStrings(List<String> commandStrings) {
        List<Command> commands = new ArrayList<>();
        for (String command : commandStrings) {
            String[] moveAndValue = command.split(" ");
            Move move = Move.valueOf(moveAndValue[0].toUpperCase());
            int value = Integer.parseInt(moveAndValue[1]);
            commands.add(new Command(move, value));
        }
        return commands;
    }

    // Part 2
    private static int part2() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> commandStrings = fileUtils.readLinesAsString();
        List<Command> commands = parseCommandStrings(commandStrings);
        ImprovedSubmarine submarine = new ImprovedSubmarine();
        for (Command command : commands) {
            submarine.performMovement(command);
        }
        return submarine.getCurrentDepth() * submarine.getCurrentHorizontalPosition();
    }
}
