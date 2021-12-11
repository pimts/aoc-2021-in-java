package aoc.pimts;

import java.util.ArrayList;
import java.util.List;

public class Octopus {

    private final List<Octopus> adjacentOctopuses;
    private int energyLevel;
    private boolean flashed;

    public Octopus(int energyLevel) {
        this.adjacentOctopuses = new ArrayList<>();
        this.energyLevel = energyLevel;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void increaseEnergyLevel() {
        energyLevel++;
    }

    public boolean hasFlashed() {
        return flashed;
    }

    public void flash() {
        flashed = true;
    }

    public void recharge() {
        flashed = false;
        energyLevel = 0;
    }

    public void addAdjacentOctopus(Octopus octopus) {
        adjacentOctopuses.add(octopus);
    }

    public List<Octopus> getAdjacentOctopuses() {
        return adjacentOctopuses;
    }
}
