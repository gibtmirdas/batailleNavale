package jUnit;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;

import org.junit.Test;

import packet.PacketBye;
import packet.PacketCardAction;
import packet.PacketHello;
import packet.PacketInfoBoat;
import packet.PacketNewCard;
import packet.PacketNewGame;
import packet.PacketUpdate;

public class TestPacketEncoding {

	@Test
	public void testHello() {
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();

			// Test Hello
			PacketHello hello = new PacketHello(10, mac);
			PacketHello helloDecoded = new PacketHello(hello.encodedPacket);
			// System.out.println("AvMac : " + Arrays.toString(mac));
			// System.out.println("ApMac : "
			// + Arrays.toString(helloDecoded.getMacAddress()));
			assertArrayEquals(mac, helloDecoded.getMacAddress());

			//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testNewGame() {
		PacketNewGame nG = new PacketNewGame(1, 30);
		PacketNewGame nGDecoded = new PacketNewGame(nG.encodedPacket);
		assertEquals(nG.getTime(), nGDecoded.getTime());
	}

	@Test
	public void testNewCard() {
		PacketNewCard nc = new PacketNewCard(1, 10);
		System.out.println(Arrays.toString(nc.encodedPacket));
		PacketNewCard ncD = new PacketNewCard(nc.encodedPacket);
		assertEquals(nc.getCardId(), ncD.getCardId());
	}

	@Test
	public void testInfoBoat() {
		PacketInfoBoat iB = new PacketInfoBoat(1, 1, 0, 1, 2, 3, 4);
		PacketInfoBoat iBDecoded = new PacketInfoBoat(iB.encodedPacket);
		assertEquals(iB.getIdBoat(), iBDecoded.getIdBoat());
		assertEquals(iB.getXStart(), iBDecoded.getXStart());
		assertEquals(iB.getLife(), iBDecoded.getLife());
	}

	@Test
	public void testCardAction() {
		PacketCardAction action = new PacketCardAction(1, 10, 20, 100);
		PacketCardAction actionD = new PacketCardAction(action.encodedPacket);
		assertEquals(action.getIdCard(), actionD.getIdCard());
		assertEquals(action.getX(), actionD.getX());
		assertEquals(action.getY(), actionD.getY());
	}

	@Test
	public void testUpdate() {
		PacketUpdate up = new PacketUpdate(1, 150);
		PacketUpdate upD = new PacketUpdate(up.encodedPacket);
		assertEquals(up.getIdUser(), upD.getIdUser());
	}

	@Test
	public void testBye() {
		PacketBye b = new PacketBye(1, 23);
		PacketBye bD = new PacketBye(b.encodedPacket);
		assertEquals(b.getIdUser(), bD.getIdUser());
	}

}
