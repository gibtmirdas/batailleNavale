package packet;

import java.util.Arrays;

public class Packet {

	public byte[] encodedPacket = null;
	protected int dataSize, idSource, opcode;
	protected byte[] data;

	public Packet(byte[] encodedPacket) {
		idSource = encodedPacket[0] >> 4;
		opcode = encodedPacket[0] & 0xF;
		dataSize = encodedPacket[1];
		data = Arrays.copyOfRange(encodedPacket, 2, encodedPacket.length);
	}

	public Packet(int dataSize, int idSource, byte[] data, Class<?> c) {
		this.dataSize = dataSize;
		this.idSource = idSource;
		this.opcode = PacketBuilder.getOpCode(c);
		this.data = data;
	}

	public void encode() {
		this.encodedPacket = new byte[dataSize + 2];
		this.encodedPacket[0] = (byte) (opcode & 0xF);
		this.encodedPacket[0] += (byte) ((idSource & 0xF) << 4);
		this.encodedPacket[1] = (byte) (dataSize & 0xFF);
		System.arraycopy(this.encodedPacket, 2, data, 0, dataSize);
	}
        
        public int getIdSource(){
            return this.idSource;
        }
}