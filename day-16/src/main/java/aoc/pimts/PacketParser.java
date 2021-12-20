package aoc.pimts;

import java.math.BigInteger;

public class PacketParser {

    int sumPacketVersion = 0;

    public Packet parse(String input) {
        int packetVersion = Integer.parseInt(input.substring(0, 3), 2);
        sumPacketVersion += packetVersion;
        int typeID = Integer.parseInt(input.substring(3, 6), 2);

        if (typeID == 4) {
            String subMessage = input.substring(6);
            StringBuilder stringBuilder = new StringBuilder();
            int index = 0;
            String part;
            do {
                part = subMessage.substring(index * 5, 5 + index * 5);
                stringBuilder.append(part.substring(1));
                index++;
            } while (4 + index * 5 < subMessage.length() && part.startsWith("1"));
            BigInteger literalValue = new BigInteger(stringBuilder.toString(), 2);
            int packetLength = 6 + index * 5;
            return new LiteralValuePacket(packetVersion, literalValue.longValue(), packetLength);
        } else {
            int lengthTypeId = Integer.parseInt(String.valueOf(input.charAt(6)));
            OperatorPacket operatorPacket = new OperatorPacket(packetVersion, typeID);
            if (lengthTypeId == 0) {
                int subPacketTotalLength = Integer.parseInt(input.substring(7, 22), 2);
                int subPacketLengthParsed = 0;
                while (subPacketLengthParsed < subPacketTotalLength) {
                    Packet packet = parse(input.substring(22 + subPacketLengthParsed));
                    subPacketLengthParsed += packet.getLength();
                    operatorPacket.addSubPacket(packet);
                }
                operatorPacket.setLength(3 + 3 + 1 + 15 + subPacketTotalLength);
            } else {
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
            }
            return operatorPacket;
        }
    }

    public int getSumPacketVersion() {
        return sumPacketVersion;
    }
}
