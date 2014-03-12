package packet;

public class PacketInfoBoat extends Packet {

	public PacketInfoBoat(byte[] encodedPacket) {
		super(encodedPacket);
	}

	public PacketInfoBoat(int idSource, int idBoat, int xStart, int yStart,
			int xEnd, int yEnd, int life) {
		super(6, idSource, null, packet.PacketInfoBoat.class);
		this.setDatas(idBoat, xStart, yStart, xEnd, yEnd, life);
		this.encode();
	}

	public void setDatas(int idBoat, int xStart, int yStart, int xEnd,
			int yEnd, int life) {
		byte[] d = new byte[6];
		d[0] = (byte) (idBoat & 0xFF);
		d[1] = (byte) (xStart & 0xFF);
		d[2] = (byte) (yStart & 0xFF);
		d[3] = (byte) (xEnd & 0xFF);
		d[4] = (byte) (yEnd & 0xFF);
		d[5] = (byte) (life & 0xFF);
		this.data = d;
	}

	public int getIdBoat() {
		return data[0];
	}

	public int getXStart() {
		return data[1];
	}

	public int getYStart() {
		return data[2];
	}

	public int getXEnd() {
		return data[3];
	}

	public int getYEnd() {
		return data[4];
	}

	public int getLife() {
		return data[5];
	}
}
