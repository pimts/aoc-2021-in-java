package aoc.pimts;

public class FoldInstruction {

    private final int value;
    private final String axis;

    public FoldInstruction(int value, String axis) {
        this.value = value;
        this.axis = axis;
    }

    public int getValue() {
        return value;
    }

    public String getAxis() {
        return axis;
    }
}
