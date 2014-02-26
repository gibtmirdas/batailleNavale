import packet.Packet;
import packet.PacketBye;
import packet.PacketCardAction;
import packet.PacketHello;
import packet.PacketInfoBoat;
import packet.PacketNewCard;
import packet.PacketNewGame;
import packet.PacketUpdate;

public class Game {
	Connection player1, player2;
	int serverId = 0;

	public Game(Connection player1, Connection player2) {
		this.player1 = player1;
		this.player2 = player2;
		init();

	}

	public void init() {
		player1.sendMessage(new PacketHello(0, null));
		player2.sendMessage(new PacketHello(0, null));
	}

	public void traiterPacket(Packet p) {
		if (p instanceof PacketHello) {

		} else if (p instanceof PacketNewGame) {

		} else if (p instanceof PacketNewCard) {

		} else if (p instanceof PacketInfoBoat) {

		} else if (p instanceof PacketCardAction) {

		} else if (p instanceof PacketUpdate) {

		} else if (p instanceof PacketBye) {

		} else {
			// Error
		}
	}

	public void packetReceivedHello(PacketHello p) {
	}

	public void packetReceivedNewCard(PacketNewCard p) {
	}

	public void packetReceivedNewGame(PacketNewGame p) {
	}

	public void packetReceivedInfoBoat(PacketInfoBoat p) {
	}

	public void packetReceivedCardAction(PacketCardAction p) {
	}

	public void packetReceivedUpdate(PacketUpdate p) {
	}

	public void packetReceivedBye(PacketBye p) {
	}

}
