package aoc.pimts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolOfFish {
    Map<Integer, Long> fishBucket;

    public SchoolOfFish(List<LanternFish> initialSchool) {
        this.fishBucket = new HashMap<>();
        fishBucket = createNewBucket();
        initialSchool.forEach(lanternFish -> fishBucket.put(
                lanternFish.getRemainingDays(), fishBucket.get(lanternFish.getRemainingDays()) + 1));
    }

    private Map<Integer, Long> createNewBucket() {
        Map<Integer, Long> newBucket = new HashMap<>();
        for (int i = 0; i <= LanternFish.DAYS_TO_GROW; i++) {
            newBucket.put(i, 0L);
        }
        return newBucket;
    }

    public void grow() {
        Map<Integer, Long> newBucket = createNewBucket();
        // Advance all fishes from remaining days 8 to 1 by one day.
        for (int i = LanternFish.DAYS_TO_GROW; i >= 1; i--) {
            Long current = fishBucket.get(i);
            newBucket.put(i-1, current);
        }

        // Special case for the fishes with 0 days
        // as they produce a new fish.
        Long currentFishToGrow = fishBucket.get(0);
        newBucket.put(6, fishBucket.get(7) + currentFishToGrow);
        newBucket.put(8, currentFishToGrow);
        fishBucket = newBucket;
    }

    public long getSizeOfSchool() {
        long sizeOfSchool = 0;
        for (Integer integer : fishBucket.keySet()) {
            sizeOfSchool += fishBucket.get(integer);
        }
        return sizeOfSchool;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        for (Integer integer : fishBucket.keySet()) {
            stringBuilder.append(fishBucket.get(integer));
            stringBuilder.append(" ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
