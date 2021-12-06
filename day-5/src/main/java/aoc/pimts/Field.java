package aoc.pimts;

public class Field {

    private final int[][] grid;
    private final int columns;
    private final int rows;

    public Field(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        grid = new int[rows][columns];
    }

    public void addLine(Line line) {
        if (line.isVertical()) {
            addVerticalLine(line);
        } else if (line.isHorizontal()) {
            addHorizontalLine(line);
        } else {
            addDiagonalLine(line);
        }
    }

    private void addVerticalLine(Line line) {
        Point begin;
        Point end;
        if (line.getPoint1().getY() < line.getPoint2().getY()) {
            begin = line.getPoint1();
            end = line.getPoint2();
        } else {
            begin = line.getPoint2();
            end = line.getPoint1();
        }
        int diff = Math.abs(begin.getY() - end.getY());
        for (int i = begin.getY(); i <= begin.getY() + diff; i++) {
            grid[i][begin.getX()]++;
        }
    }
    
    private void addHorizontalLine(Line line) {
        Point begin;
        Point end;
        if (line.getPoint1().getX() < line.getPoint2().getX()) {
            begin = line.getPoint1();
            end = line.getPoint2();
        } else {
            begin = line.getPoint2();
            end = line.getPoint1();
        }
        int diff = Math.abs(begin.getX() - end.getX());
        for (int i = begin.getX(); i <= begin.getX() + diff; i++) {
            grid[begin.getY()][i]++;
        }
    }

    private void addDiagonalLine(Line line) {
        Point begin;
        Point end;
        if (line.getPoint1().getX() < line.getPoint2().getX()) {
            begin = line.getPoint1();
            end = line.getPoint2();
        } else {
            begin = line.getPoint2();
            end = line.getPoint1();
        }
        int diff = Math.abs(begin.getX() - end.getX());
        int yStep = (begin.getY() < end.getY()) ? 1 : -1;
        int j = 0;
        for (int i = begin.getX(); i <= begin.getX() + diff; i++) {
            grid[begin.getY() + j * yStep][i]++;
            j++;
        }
    }

    public int getNumberOfDangerousAreas() {
        int numberOfDangerousAreas = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] > 1) {
                    numberOfDangerousAreas++;
                }
            }
        }
        return numberOfDangerousAreas;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            stringBuilder.append("[ ");
            for (int j = 0; j < columns; j++) {
                stringBuilder.append(grid[i][j]);
                stringBuilder.append(" ");
            }
            stringBuilder.append("]\n");
        }
        return stringBuilder.toString();
    }
}
