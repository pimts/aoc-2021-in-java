package aoc.pimts;

import java.util.ArrayList;
import java.util.List;

public class OperatorPacket extends Packet {

    private final List<Packet> subPackets;

    public OperatorPacket(int packetVersion, int typeID) {
        super(packetVersion, typeID, 0);
        this.subPackets = new ArrayList<>();
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void addSubPacket(Packet packet) {
        subPackets.add(packet);
    }
}
