package aoc.pimts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private final List<BingoBoard> boards;
    private List<Integer> numbersToDraw;
    private final Iterator<Integer> numberToDraw;
    private final List<BingoBoard> scoreBoard;

    public Game(String numberData, List<String> boardData) {
        this.boards = new ArrayList<>();
        this.scoreBoard = new ArrayList<>();
        parseNumberData(numberData);
        parseBoardData(boardData);
        this.numberToDraw = numbersToDraw.iterator();
    }

    public List<BingoBoard> getScoreBoard() {
        return scoreBoard;
    }

    public void play() {
        while (numberToDraw.hasNext()) {
            int number = numberToDraw.next();
            for (BingoBoard board : boards) {
                if (board.markNumber(number) && board.getNumbersMarked() >= 5 && board.hasWon()) {
                    scoreBoard.add(board);
                }
            }
            boards.removeAll(scoreBoard);
        }
    }

    private void parseNumberData(String numberData) {
        this.numbersToDraw = Arrays.stream(numberData.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void parseBoardData(List<String> boardData) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String boardDataString : boardData) {
            if (boardDataString.isEmpty()) {
                boards.add(new BingoBoard(stringBuilder.toString()));
                stringBuilder = new StringBuilder();
            }
            stringBuilder.append(boardDataString)
                    .append(" ");
        }
    }
}
