package aoc.pimts;

public class Point {

    private static int maxX = -1;
    private static int maxY = -1;

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        maxX = Math.max(x, maxX);
        maxY = Math.max(y, maxY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int getMaxX() {
        return maxX;
    }


    public static int getMaxY() {
        return maxY;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
