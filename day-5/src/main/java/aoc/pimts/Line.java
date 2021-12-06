package aoc.pimts;

public class Line {

    private final Point point1;
    private final Point point2;

    public Line(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public boolean isHorizontal() {
        return point1.getY() == point2.getY();
    }

    public boolean isVertical() {
        return point1.getX() == point2.getX();
    }

    public boolean isDiagonal() {
        return !isHorizontal() && !isVertical();
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    @Override
    public String toString() {
        return "Line{" +
                "begin=" + point1 +
                ", end=" + point2 +
                '}';
    }
}
