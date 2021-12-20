package aoc.pimts;

public class LiteralValuePacket extends Packet {

    public LiteralValuePacket(int packetVersion, long value, int length) {
        super(packetVersion, 4, length);
        this.value = value;
    }
}
