package packet;

public class PacketClientList extends Packet {

	public PacketClientList(int idSource, byte[] list) {
		super(list.length, idSource, list, packet.PacketClientList.class);
		this.encode();
	}

	public PacketClientList(byte[] encodedPacket) {
		super(encodedPacket);
	}

	public String[][] getList() {
		if (dataSize > 0) {
			String[] tmp = new String(data).split("-");
			String[][] ret = new String[tmp.length][3];
			for (int i = 0; i < tmp.length; i++) {
				ret[i] = tmp[i].split("/");
			}
			return ret;
		} else
			return null;
	}

}
