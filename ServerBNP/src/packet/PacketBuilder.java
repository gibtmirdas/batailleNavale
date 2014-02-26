package packet;

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
}
