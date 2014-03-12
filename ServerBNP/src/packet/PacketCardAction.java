package packet;

public class PacketCardAction extends Packet {

	public PacketCardAction(byte[] encodedPacket) {
		super(encodedPacket);
	}

	public PacketCardAction(int idSource, int idCard, int xStart, int yStart) {
		super(3, idSource, null, PacketCardAction.class);
		this.setDatas(idCard, xStart, yStart);
		this.encode();
	}

        public final void setDatas(int idCard, int xStart, int yStart) {
		byte[] d = new byte[3];
		d[0] = (byte) (idCard & 0xFF);
		d[1] = (byte) (xStart & 0xFF);
		d[2] = (byte) (yStart & 0xFF);
		this.data = d;
	}

	public int getIdCard() {
		return data[0];
	}

	public int getX() {
		return data[1];
	}

	public int getY() {
		return data[2];
	}

}
