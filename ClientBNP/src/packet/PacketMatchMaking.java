package packet;

public class PacketMatchMaking extends Packet {

	public PacketMatchMaking(int idSource, byte[] username) {
		super(username.length, idSource, username,
				packet.PacketMatchMaking.class);
		this.encode();
	}

	public PacketMatchMaking(byte[] encodedPacket) {
		super(encodedPacket);
	}

	public byte[] getUsername() {
		if (dataSize > 0)
			return data;
		else
			return null;
	}

}
