package aoc.pimts.submarine;

public class ImprovedSubmarine extends Submarine {

    private int aim;

    public ImprovedSubmarine() {
        this.aim = 0;
    }

    @Override
    protected void moveUpBy(int upValue) {
        aim -= upValue;
    }

    @Override
    protected void moveDownBy(int downValue) {
        aim += downValue;
    }

    @Override
    protected void moveForwardBy(int forwardValue) {
        super.moveForwardBy(forwardValue);
        depth += aim * forwardValue;
    }
}
