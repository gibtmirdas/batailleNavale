package packet;

public class PacketInfoProfile extends Packet {
	public PacketInfoProfile(byte[] encodedPacket) {
		super(encodedPacket);
	}

	public PacketInfoProfile(int idSource,int victoires,int defaites,int nbrCards,int solde) {
		super(8, idSource, null, PacketInfoProfile.class);
		this.setDatas(victoires,defaites,nbrCards,solde);
		this.encode();
	}

	public void setDatas(int victoires,int defaites,int nbrCards,int solde) {
		byte[] t = new byte[8];
		int mask8b = 0xFF;
		t[0] = (byte) ((victoires >> 8) & mask8b);
		t[1] = (byte) ((victoires) & mask8b);
		t[2] = (byte) ((defaites >> 8) & mask8b);
		t[3] = (byte) ((defaites) & mask8b);
		t[4] = (byte) ((nbrCards >> 8) & mask8b);
		t[5] = (byte) ((nbrCards) & mask8b);
		t[6] = (byte) ((solde >> 8) & mask8b);
		t[7] = (byte) ((solde) & mask8b);
		this.data = t;
		
	}
	
	public int getNumberOfVictories() {
		return (int)(data[0]<<8 + data[1]);
	}
	
	public int getNumberOfDefeats() {
		return (int)(data[2]<<8 + data[3]);
	}
	
	public int getNumberOfCards() {
		return (int)(data[4]<<8 + data[5]);
	}
	
	public int getSolde() {
		return (int)(data[6]<<8 + data[7]);
	}
	
}
