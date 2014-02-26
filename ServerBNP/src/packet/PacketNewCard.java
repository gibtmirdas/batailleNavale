package packet;

public class PacketNewCard extends Packet {

	public PacketNewCard(byte[] encodedPacket) {
		super(encodedPacket);
	}

	public PacketNewCard(int idSource, int idCard) {
		super(1, idSource, null, PacketNewCard.class);
		this.setDatas(idCard);
	}

	public void setDatas(int idCard) {
		byte[] t = new byte[1];
		t[0] = (byte) (idCard & 0xFF);
		this.data = t;
	}

	public int getCardId() {
		return (int) this.data[0];
	}
}
