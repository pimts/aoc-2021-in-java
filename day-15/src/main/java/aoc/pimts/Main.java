package aoc.pimts;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = " + part2());
    }

    private static long part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        int[][] caveMap = input.stream()
                .map(s -> s.chars()
                        .map(Character::getNumericValue)
                        .toArray())
                .toArray(size -> new int[size][1]);

        return findPathShortestPath(caveMap);
    }

    private static long part2() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        int[][] caveMap = input.stream()
                .map(s -> s.chars()
                        .map(Character::getNumericValue)
                        .toArray())
                .toArray(size -> new int[size][1]);
        int[][] increasedCaveMap = increaseCaveMap(caveMap);
        return findPathShortestPath(increasedCaveMap);
    }

    private static int findPathShortestPath(int[][] caveMap) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new Node());
        Set<Node> visited = new HashSet<>();
        visited.add(new Node(0, 0, 0));
        priorityQueue.add(new Node(0, 0, 0));

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();

            if (currentNode.getX() == caveMap[0].length - 1 && currentNode.getY() == caveMap.length - 1) {
                return currentNode.getRisk();
            }

            if (currentNode.getX() - 1 > 0 && !visited.contains(new Node(currentNode.getX() - 1, currentNode.getY(), 0))) {
                int neighborY = currentNode.getY();
                int neighborX = currentNode.getX() - 1;
                int risk = caveMap[neighborY][neighborX];
                priorityQueue.add(new Node(neighborX, neighborY, currentNode.getRisk() + risk));
                visited.add(new Node(neighborX, neighborY, 0));
            }

            if (currentNode.getX() + 1 < caveMap[0].length && !visited.contains(new Node(currentNode.getX() + 1, currentNode.getY(), 0))) {
                int neighborY = currentNode.getY();
                int neighborX = currentNode.getX() + 1;
                int risk = caveMap[neighborY][neighborX];
                priorityQueue.add(new Node(neighborX, neighborY, currentNode.getRisk() + risk));
                visited.add(new Node(neighborX, neighborY, 0));
            }

            if (currentNode.getY() - 1 > 0 && !visited.contains(new Node(currentNode.getX(), currentNode.getY() - 1, 0))) {
                int neighborY = currentNode.getY() - 1;
                int neighborX = currentNode.getX();
                int risk = caveMap[neighborY][neighborX];
                priorityQueue.add(new Node(neighborX, neighborY, currentNode.getRisk() + risk));
                visited.add(new Node(neighborX, neighborY, 0));
            }

            if (currentNode.getY() + 1 < caveMap.length && !visited.contains(new Node(currentNode.getX(), currentNode.getY() + 1, 0))) {
                int neighborY = currentNode.getY() + 1;
                int neighborX = currentNode.getX();
                int risk = caveMap[neighborY][neighborX];
                priorityQueue.add(new Node(neighborX, neighborY, currentNode.getRisk() + risk));
                visited.add(new Node(neighborX, neighborY, 0));
            }
        }
        return -1;
    }

    private static int[][] increaseCaveMap(int[][] caveMap) {
        int[][] newCaveMap = new int[caveMap.length * 5][caveMap[0].length * 5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int y = 0; y < caveMap.length; y++) {
                    for (int x = 0; x < caveMap[0].length; x++) {
                        int newValue = caveMap[y][x] + i + j;
                        while (newValue > 9) {
                            newValue -= 9;
                        }
                        newCaveMap[y + (i * caveMap.length)][x + (j * caveMap[0].length)] = newValue;
                    }

                }
            }
        }
        return newCaveMap;
    }
}
