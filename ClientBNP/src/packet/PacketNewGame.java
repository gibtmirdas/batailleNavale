package packet;

public class PacketNewGame extends Packet {

	public PacketNewGame(int idSource, int time) {
		super(1, idSource, null, PacketNewGame.class);
		this.setDatas(time);
		this.encode();
	}

	public PacketNewGame(byte[] encodedPacket) {
		super(encodedPacket);
	}

	public void setDatas(int time) {
		byte[] t = new byte[1];
		t[0] = (byte) (time & 0xFF);
		this.data = t;
	}

	public int getTime() {
		return (int) this.data[0];
	}
}
