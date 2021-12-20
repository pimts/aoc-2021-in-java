package aoc.pimts;

import java.math.BigInteger;

public class PacketParser {

    private long sumPacketVersion = 0;
    private long totalValue = 0;

    public Packet parse(String input) {
        int typeID = Integer.parseInt(input.substring(3, 6), 2);
        if (typeID == 4) {
            LiteralValuePacket literalValuePacket = parseLiteralValuePacket(input);
            sumPacketVersion += literalValuePacket.getPacketVersion();
            return literalValuePacket;
        } else {
            int lengthTypeId = Integer.parseInt(String.valueOf(input.charAt(6)));
            OperatorPacket operatorPacket;
            if (lengthTypeId == 0) {
                operatorPacket = parseOperatorPacketOnNumberOfSubPackets(input);
            } else {
                operatorPacket = parseOperatorPacketOnSubPacketLength(input);
            }
            sumPacketVersion += operatorPacket.getPacketVersion();
            operatorPacket.setValue(calculateOperatorPacketValue(operatorPacket));
            totalValue = operatorPacket.getValue();
            return operatorPacket;
        }
    }

    public long getSumPacketVersion() {
        return sumPacketVersion;
    }

    public long getTotalValue() {
        return totalValue;
    }

    private LiteralValuePacket parseLiteralValuePacket(String input) {
        int packetVersion = Integer.parseInt(input.substring(0, 3), 2);
        String literalValueString = input.substring(6);
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        String part;
        do {
            part = literalValueString.substring(index * 5, 5 + index * 5);
            stringBuilder.append(part.substring(1));
            index++;
        } while (part.startsWith("1"));
        BigInteger literalValue = new BigInteger(stringBuilder.toString(), 2);
        int packetLength = 3 + 3 + index * 5;
        return new LiteralValuePacket(packetVersion, literalValue.longValue(), packetLength);
    }

    private OperatorPacket parseOperatorPacketOnNumberOfSubPackets(String input) {
        int packetVersion = Integer.parseInt(input.substring(0, 3), 2);
        int typeID = Integer.parseInt(input.substring(3, 6), 2);
        OperatorPacket operatorPacket = new OperatorPacket(packetVersion, typeID);

        int subPacketTotalLength = Integer.parseInt(input.substring(7, 22), 2);
        int subPacketLengthParsed = 0;
        while (subPacketLengthParsed < subPacketTotalLength) {
            Packet packet = parse(input.substring(22 + subPacketLengthParsed));
            subPacketLengthParsed += packet.getLength();
            operatorPacket.addSubPacket(packet);
        }
        operatorPacket.setLength(3 + 3 + 1 + 15 + subPacketTotalLength);
        return operatorPacket;
    }

    private OperatorPacket parseOperatorPacketOnSubPacketLength(String input) {
        int packetVersion = Integer.parseInt(input.substring(0, 3), 2);
        int typeID = Integer.parseInt(input.substring(3, 6), 2);
        OperatorPacket operatorPacket = new OperatorPacket(packetVersion, typeID);

        int totalNumberOfSubPackets = Integer.parseInt(input.substring(7, 18), 2);
        int numberOfSubPacketsParsed = 0;
        int subPacketLengthParsed = 0;
        while (numberOfSubPacketsParsed != totalNumberOfSubPackets) {
            Packet packet = parse(input.substring(18 + subPacketLengthParsed));
            numberOfSubPacketsParsed++;
            subPacketLengthParsed += packet.getLength();
            operatorPacket.addSubPacket(packet);
        }
        operatorPacket.setLength(3 + 3 + 1 + 11 + subPacketLengthParsed);
        return operatorPacket;
    }

    private long calculateOperatorPacketValue(OperatorPacket operatorPacket) {
        switch (operatorPacket.getTypeID()) {
            case 0:
                return operatorPacket.getSubPackets().stream().map(Packet::getValue).mapToLong(Long::longValue).sum();
            case 1:
                return operatorPacket.getSubPackets().stream().map(Packet::getValue).mapToLong(Long::longValue).reduce(1, (acc, it) -> acc *= it);
            case 2:
                return operatorPacket.getSubPackets().stream().map(Packet::getValue).mapToLong(Long::longValue).min().getAsLong();
            case 3:
                return operatorPacket.getSubPackets().stream().map(Packet::getValue).mapToLong(Long::longValue).max().getAsLong();
            case 5:
                return operatorPacket.getSubPackets().get(0).getValue() > operatorPacket.getSubPackets().get(1).getValue() ? 1L : 0L;
            case 6:
                return operatorPacket.getSubPackets().get(0).getValue() < operatorPacket.getSubPackets().get(1).getValue() ? 1L : 0L;
            case 7:
                return operatorPacket.getSubPackets().get(0).getValue() == operatorPacket.getSubPackets().get(1).getValue() ? 1L : 0L;
            default:
                throw new IllegalArgumentException("Packet has an invalid typeID!");
        }
    }

}
