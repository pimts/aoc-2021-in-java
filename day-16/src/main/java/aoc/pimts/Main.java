package aoc.pimts;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Answer to part 1 = " + part1());
        System.out.println("Answer to part 2 = " + part2());
    }

    private static long part1() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        StringBuilder binaryString = new StringBuilder();
        for (char c : input.get(0).toCharArray()) {
            int hexValue = Integer.parseInt(String.valueOf(c), 16);
            String hexToBinary = Integer.toString(hexValue, 2);
            String replace = String.format("%4s", hexToBinary).replace(" ", "0");
            binaryString.append(replace);
        }
        PacketParser packetParser = new PacketParser();
        packetParser.parse(binaryString.toString());
        return packetParser.getSumPacketVersion();
    }

    private static long part2() throws IOException {
        FileUtils fileUtils = new FileUtils();
        List<String> input = fileUtils.readLinesAsString();
        StringBuilder binaryString = new StringBuilder();
        for (char c : input.get(0).toCharArray()) {
            int hexValue = Integer.parseInt(String.valueOf(c), 16);
            String hexToBinary = Integer.toString(hexValue, 2);
            String replace = String.format("%4s", hexToBinary).replace(" ", "0");
            binaryString.append(replace);
        }
        PacketParser packetParser = new PacketParser();
        packetParser.parse(binaryString.toString());
        return packetParser.getTotalValue();
    }
}
