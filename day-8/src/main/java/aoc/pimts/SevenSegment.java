package aoc.pimts;

import java.util.HashSet;
import java.util.Set;

public class SevenSegment {

    private final Set<Character> segments;

    public SevenSegment(String segments) {
        this.segments = stringToCharSet(segments);
    }

    private Set<Character> stringToCharSet(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    public boolean isContainedBy(String other) {
        Set<Character> otherSet = stringToCharSet(other);
        return otherSet.containsAll(segments);
    }

    public int differences(String other) {
        Set<Character> otherSet = stringToCharSet(other);
        Set<Character> copySegments = new HashSet<>(segments);
        copySegments.removeAll(otherSet);
        return copySegments.size();
    }
}
