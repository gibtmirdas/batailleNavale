package packet;

import java.io.IOException;
import java.io.InputStream;

public class PacketBuilder {

	public static Class<?> classes[] = { packet.PacketHello.class,
			packet.PacketNewGame.class, packet.PacketNewCard.class,
			packet.PacketInfoBoat.class, packet.PacketCardAction.class,
			packet.PacketUpdate.class, packet.PacketBye.class };

	public static Packet build(byte[] encodedPacket) {
		return new Packet(encodedPacket);
	}

	public static Class<?> getPacketClass(Packet p) {
		try {
			return classes[p.opcode];
		} catch (NullPointerException e) {
			return null;
		}
	}

	public static int getOpCode(Class<?> c) {
		for (int i = 0; i < classes.length; i++) {
			if (c.getName().equals(classes[i].getName()))
				return i;
		}
		return -1;
	}
	public static byte[] readPacket(InputStream is) throws IOException{
		byte opcodeID, size;
		opcodeID = (byte)is.read();
		size = (byte)is.read();
		byte[] datas = new byte[size+2];
		datas[0] = opcodeID;
		datas[1] = size;
		for (int i = 2; i < datas.length; i++) {
			datas[i] = (byte) is.read();
		}
		
		return datas;
	}
}
