package aoc.pimts;

public class Command {

    private final Move move;
    private final int value;

    public Command(Move move, int value) {
        this.move = move;
        this.value = value;
    }

    public Move getMove() {
        return move;
    }

    public int getValue() {
        return value;
    }
}
