package aoc.pimts;

import java.util.Comparator;
import java.util.Objects;

public class Node implements Comparator<Node> {

    private final int x;
    private final int y;
    private int risk;

    public Node() {
        x = 0;
        y = 0;
    }

    public Node(int x, int y, int risk) {
        this.x = x;
        this.y = y;
        this.risk = risk;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    @Override
    public int compare(Node n1, Node n2) {
        return Integer.compare(n1.risk, n2.risk);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
