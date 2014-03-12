package packet;

public class PacketHello extends Packet {

	public PacketHello(int idSource, byte[] macAddress) {
		super(6, idSource, macAddress, packet.PacketHello.class);
		this.encode();
	}

	public PacketHello(byte[] encodedPacket) {
		super(encodedPacket);
	}

	public byte[] getMacAddress() {
		if (dataSize > 0)
			return data;
		else
			return null;
	}

}
