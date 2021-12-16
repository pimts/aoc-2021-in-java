package aoc.pimts;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

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

    private static long part2() {
        return -2L;
    }

    private static int findPathShortestPath(int[][] caveMap) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new Node());
        Set<Node> visited = new HashSet<>();
        visited.add(new Node(0,0,0));
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


            if (currentNode.getY() - 1 > 0 && !visited.contains(new Node(currentNode.getX(), currentNode.getY() -1, 0))) {
                int neighborY = currentNode.getY() -1;
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
}
