package aoc.pimts;

public class LiteralValuePacket extends Packet {

    private long literalValue;
    public LiteralValuePacket(int packetVersion, long value, int length) {
        super(packetVersion, 4, length);
        this.literalValue = value;
    }
}
