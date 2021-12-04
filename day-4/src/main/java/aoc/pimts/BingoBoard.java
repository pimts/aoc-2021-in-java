package aoc.pimts;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BingoBoard {

    private final List<Integer> grid;
    private final int rowSize;
    private final int columns;
    private int numbersMarked;
    private int lastNumberMarked;

    public BingoBoard(String boardData) {
        this.rowSize = 5;
        this.columns = 5;
        this.numbersMarked = 0;
        this.grid = Arrays.stream(boardData.split(" +"))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public boolean markNumber(int number) {
        boolean hasReplaced = Collections.replaceAll(grid, number, -1);
        if (hasReplaced) {
            numbersMarked++;
            lastNumberMarked = number;
        }
        return hasReplaced;
    }

    public int getNumbersMarked() {
        return numbersMarked;
    }

    public int getLastNumberMarked() {
        return lastNumberMarked;
    }

    public boolean hasWon() {
        for (int i = 0; i < columns; i++) {
            //check rows
            if (grid.get(i * rowSize) == -1
                    && grid.get(i * rowSize + 1) == -1
                    && grid.get(i * rowSize + 2) == -1
                    && grid.get(i * rowSize + 3) == -1
                    && grid.get(i * rowSize + 4) == -1) {
                return true;
            }

            //check columns
            if (grid.get(i) == -1
                    && grid.get(i + rowSize) == -1
                    && grid.get(i + 2 * rowSize) == -1
                    && grid.get(i + 3 * rowSize) == -1
                    && grid.get(i + 4 * rowSize) == -1) {
                return true;
            }
        }

        return false;
    }

    public int getSumOfUnmarkedNumbers() {
        int sum = 0;
        for (Integer integer : grid) {
            if (integer != -1) {
                sum += integer;
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < grid.size(); i += 5) {
            stringBuilder.append("[")
                    .append(grid.get(i)).append(", ")
                    .append(grid.get(i + 1)).append(", ")
                    .append(grid.get(i + 2)).append(", ")
                    .append(grid.get(i + 3)).append(", ")
                    .append(grid.get(i + 4)).append("]")
                    .append("\n");
        }
        return stringBuilder.toString();
    }
}
