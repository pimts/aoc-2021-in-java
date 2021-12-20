package aoc.pimts;

public class Packet {

    protected int packetVersion;
    protected int typeID;
    protected int length;
    protected long value;

    public Packet(int packetVersion, int typeID, int length) {
        this.packetVersion = packetVersion;
        this.typeID = typeID;
        this.length = length;
    }

    public int getPacketVersion() {
        return packetVersion;
    }

    public int getTypeID() {
        return typeID;
    }

    public int getLength() {
        return length;
    }

    public long getValue() {
        return value;
    }
}
