package aoc.pimts;

import java.util.HashSet;
import java.util.Set;

public class Cave {

    private final Set<Cave> connectedCaves;
    private final String label;
    private final boolean isBig;

    public Cave(String label, boolean isBig) {
        this.isBig = isBig;
        this.connectedCaves = new HashSet<>();
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public boolean isBig() {
        return isBig;
    }

    public Set<Cave> getConnectedCaves() {
        return connectedCaves;
    }

    public void addConnectedCave(Cave cave) {
        connectedCaves.add(cave);
    }
}
