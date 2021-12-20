package aoc.pimts;

public class Packet {

    protected int packetVersion;
    protected int typeID;
    protected int length;

    public Packet(int packetVersion, int typeID, int length) {
        this.packetVersion = packetVersion;
        this.typeID = typeID;
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
