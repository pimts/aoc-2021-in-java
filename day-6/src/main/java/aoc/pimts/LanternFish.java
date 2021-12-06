package aoc.pimts;

public class LanternFish {

    public static int DAYS_TO_GROW = 8;
    private int remainingDays;

    public LanternFish(int remainingDays) {
        this.remainingDays = remainingDays;
    }

    public int getRemainingDays() {
        return remainingDays;
    }
}
