package aoc.pimts.submarine;

import aoc.pimts.Command;
import aoc.pimts.Move;

public class Submarine {

    protected int depth;
    protected int horizontalPosition;

    public Submarine() {
        this.depth = 0;
        this.horizontalPosition = 0;
    }

    public void performMovement(Command movementCommand) {
        int movementValue = movementCommand.getValue();
        Move move = movementCommand.getMove();
        switch (move) {
            case UP:
                moveUpBy(movementValue);
                break;
            case DOWN:
                moveDownBy(movementValue);
                break;
            case FORWARD:
                moveForwardBy(movementValue);
                break;
        }
    }

    protected void moveUpBy(int upValue) {
        depth -= upValue;
    }

    protected void moveDownBy(int downValue) {
        depth += downValue;
    }

    protected void moveForwardBy(int forwardValue) {
        horizontalPosition += forwardValue;
    }

    public int getCurrentDepth() {
        return depth;
    }

    public int getCurrentHorizontalPosition() {
        return horizontalPosition;
    }
}
