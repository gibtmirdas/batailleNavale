package packet;

public class PacketUpdate extends Packet {

	public PacketUpdate(byte[] encodedPacket) {
		super(encodedPacket);
	}

	public PacketUpdate(int idSource, int idUser) {
		super(1, idSource, null, PacketUpdate.class);
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
