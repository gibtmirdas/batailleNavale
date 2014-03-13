package packet;

public class PacketConsultShop extends Packet {
	public PacketConsultShop(byte[] encodedPacket) {
		super(encodedPacket);
	}

	public PacketConsultShop(int idSource,int category) {
		super(1, idSource, null, PacketInfoProfile.class);
		this.setDatas(category);
		this.encode();
	}
	public void setDatas(int cat) {
		byte[] t = new byte[1];
		t[0] = (byte) (cat & 0xFF);
		data = t;
	}
	public int getCategory(){
		return (int)this.data[0];
	}
}
