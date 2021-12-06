package aoc.pimts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolOfFish {
    Map<Integer, Long> fishBuckets;

    public SchoolOfFish(List<LanternFish> initialSchool) {
        this.fishBuckets = new HashMap<>();
        fishBuckets = createNewBucket();
        initialSchool.forEach(lanternFish -> fishBuckets.put(
                lanternFish.getRemainingDays(), fishBuckets.get(lanternFish.getRemainingDays()) + 1));
    }

    private Map<Integer, Long> createNewBucket() {
        Map<Integer, Long> newBucket = new HashMap<>();
        for (int i = 0; i <= LanternFish.DAYS_TO_GROW; i++) {
            newBucket.put(i, 0L);
        }
        return newBucket;
    }

    public void grow() {
        Map<Integer, Long> newBuckets = createNewBucket();
        newBuckets.put(0, fishBuckets.get(1));
        newBuckets.put(1, fishBuckets.get(2));
        newBuckets.put(2, fishBuckets.get(3));
        newBuckets.put(3, fishBuckets.get(4));
        newBuckets.put(4, fishBuckets.get(5));
        newBuckets.put(5, fishBuckets.get(6));
        newBuckets.put(6, fishBuckets.get(7) + fishBuckets.get(0));
        newBuckets.put(7, fishBuckets.get(8));
        newBuckets.put(8, fishBuckets.get(0));
        fishBuckets = newBuckets;
    }

    public long getSizeOfSchool() {
        long sizeOfSchool = 0;
        for (Integer integer : fishBuckets.keySet()) {
            sizeOfSchool += fishBuckets.get(integer);
        }
        return sizeOfSchool;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        for (Integer integer : fishBuckets.keySet()) {
            stringBuilder.append(fishBuckets.get(integer));
            stringBuilder.append(" ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
