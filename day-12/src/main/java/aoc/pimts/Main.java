package aoc.pimts;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = " + part2());
    }

    private static long part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();

        Map<String, Cave> caveSystem = input.stream()
                .map(s -> s.split("-"))
                .flatMap(Arrays::stream)
                .distinct()
                .map(name -> new Cave(name, isBigCave(name)))
                .collect(Collectors.toMap(Cave::getLabel, cave -> cave));

        input.stream()
                .map(s -> s.split("-"))
                .forEach(names -> {
                    Cave cave1 = caveSystem.get(names[0]);
                    Cave cave2 = caveSystem.get(names[1]);
                    if (!cave2.getLabel().equals("start")) {
                        cave1.addConnectedCave(cave2);
                    }
                    if(!cave1.getLabel().equals("start")) {
                        cave2.addConnectedCave(cave1);
                    }
                });

        return findPathsPart1(caveSystem.get("start"), new ArrayList<>());
    }

    private static long part2() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();

        Map<String, Cave> caveSystem = input.stream()
                .map(s -> s.split("-"))
                .flatMap(Arrays::stream)
                .distinct()
                .map(name -> new Cave(name, isBigCave(name)))
                .collect(Collectors.toMap(Cave::getLabel, cave -> cave));

        input.stream()
                .map(s -> s.split("-"))
                .forEach(names -> {
                    Cave cave1 = caveSystem.get(names[0]);
                    Cave cave2 = caveSystem.get(names[1]);
                    if (!cave2.getLabel().equals("start")) {
                        cave1.addConnectedCave(cave2);
                    }
                    if(!cave1.getLabel().equals("start")) {
                        cave2.addConnectedCave(cave1);
                    }
                });

        return findPathsPart2(caveSystem.get("start"), new ArrayList<>());
    }

    private static boolean isBigCave(String caveName) {
        return caveName.equals(caveName.toUpperCase());
    }

    private static long findPathsPart1(Cave cave, List<Cave> visitedCaves) {
        if (cave.getLabel().equals("end")) {
            return 1;
        }
        if (!cave.isBig() && visitedCaves.contains(cave)) {
            return 0;
        }
        visitedCaves.add(cave);
        return cave.getConnectedCaves()
                .stream()
                .map(c -> findPathsPart1(c, new ArrayList<>(visitedCaves)))
                .reduce(0L, (acc, it) -> acc += it);
    }

    private static long findPathsPart2(Cave cave, List<Cave> visitedCaves) {
        if (cave.getLabel().equals("end")) {
            return 1;
        }
        if (!cave.isBig() && visitedCaves.contains(cave) && alreadyVisitedSmallCaveTwice(visitedCaves)) {
            return 0;
        }
        visitedCaves.add(cave);
        return cave.getConnectedCaves()
                .stream()
                .map(c -> findPathsPart2(c, new ArrayList<>(visitedCaves)))
                .reduce(0L, (acc, it) -> acc += it);
    }

    private static boolean alreadyVisitedSmallCaveTwice(List<Cave> visitedCaves) {
        // Pigeonhole principle: if more small caves have been visited than the
        // size of the set of unique caves; then there is a small cave
        // that has been visited twice.
        int uniqueSmallCaves = visitedCaves.stream()
                .filter(c -> !c.isBig())
                .collect(Collectors.toSet()).size();
        long smallCavesVisited = visitedCaves.stream().filter(c -> !c.isBig()).count();
        return uniqueSmallCaves < smallCavesVisited;
    }
}
