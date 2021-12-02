public class Window {

    private final int part1;
    private final int part2;
    private final int part3;

    public Window(int part1, int part2, int part3) {
        this.part1 = part1;
        this.part2 = part2;
        this.part3 = part3;
    }

    public int getSumOfParts() {
        return part1 + part2 + part3;
    }

    @Override
    public String toString() {
        return "Window{" +
                "part1=" + part1 +
                ", part2=" + part2 +
                ", part3=" + part3 +
                '}';
    }
}
