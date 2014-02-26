package packet;

public class PacketBye extends Packet {

	public PacketBye(byte[] encodedPacket) {
		super(encodedPacket);
	}

	public PacketBye(int idSource, int idUser) {
		super(1, idSource, null, PacketBye.class);
		this.setDatas(idUser);
		this.encode();
	}

	public void setDatas(int idUser) {
		byte[] d = new byte[1];
		d[0] = (byte) (idUser & 0xFF);
		this.data = d;
	}

	public int getIdUser() {
		return data[0];
	}
}
