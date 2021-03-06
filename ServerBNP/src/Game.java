import packet.*;
import db.TCartes;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import db.TJoueurs;
import models.Joueur;

public class Game {
	private final Connection player1, player2;
	private final int serverId = 0;
	private int current_player;
	private final int[][] gameboard = new int[40][30];
	int[] p1BoatLife = new int[3];
	int[] p2BoatLife = new int[3];
	private UpdateTimerTask task;
	private Timer timer;
	public static final int ROUND_TIME_SEC = 30;
	public static final int ROUND_TIME_MILLISEC = Game.ROUND_TIME_SEC * 1000;

	public Game(Connection player1, Connection player2) {
		this.player1 = player1;
		this.player2 = player2;
		this.player1.setG(this);
		this.player2.setG(this);
		init();
	}

	private void init() {
		for (int y = 0; y < 30; y++)
			for (int x = 0; x < 40; x++)
				gameboard[x][y] = 0;
		
		player1.sendMessage(new PacketNewGame(0, 1));
		player2.sendMessage(new PacketNewGame(0, 2));
		for (int i = 0; i < 3; i++) {
			player1.sendMessage(new PacketNewCard(0, TCartes.DEFAULT_ID));
			player2.sendMessage(new PacketNewCard(0, TCartes.DEFAULT_ID));
			player1.sendMessage(new PacketInfoBoat(0, i + 1, (i + i * 3),
					(i + i * 3), (i + i * 3 + 1), (i + i * 3), 3));
			p1BoatLife[i] = 1;
			p2BoatLife[i] = 1;
			gameboard[i + i * 3][i + i * 3] = i + 1;
			gameboard[i + i * 3 + 1][i + i * 3] = i + 1;
			player2.sendMessage(new PacketInfoBoat(0, i + 4, (i + i * 3)+20,
					(i + i * 3), (i + i * 3 + 1)+20, (i + i * 3), 3));
			gameboard[i + i * 3 + 20][i + i * 3] = i + 4;
			gameboard[i + i * 3 + 20 + 1][i + i * 3] = i + 4;
		}
		current_player = 2;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*player1.sendMessage(new PacketUpdate(0, current_player));
		player2.sendMessage(new PacketUpdate(0, current_player));*/
		task = new UpdateTimerTask(this);
		timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, Game.ROUND_TIME_MILLISEC);
	}

	public void callUpdate() {
		this.current_player = current_player % 2 + 1;
		System.out.println("Tour de : "+current_player);
		player1.sendMessage(new PacketUpdate(serverId, current_player));
		player2.sendMessage(new PacketUpdate(serverId, current_player));
		Connection p = current_player == 1 ? player1 : player2;
		p.sendMessage(new PacketNewCard(0, TCartes.DEFAULT_ID));
	}

	public void traiterPacket(Packet p) throws ClassNotFoundException {
		Class<?> c = packet.PacketBuilder.getPacketClass(p);
		Packet p2;
		switch (c.getName()) {
		case "packet.PacketUpdate":
			
			p2 = new PacketUpdate(p.encodedPacket);
			System.out.println("Packet update received ["+p2.getIdSource()+"]");
			packetReceivedUpdate((PacketUpdate) p2);
			break;
		case "packet.PacketCardAction":
			p2 = new PacketCardAction(p.encodedPacket);
			System.out.println("Packet Card Action received ["+p2.getIdSource()+"]");
			packetReceivedCardAction((PacketCardAction) p2);
			break;
		default:
			throw new ClassNotFoundException("Unknown packet");

		}
	}

	public void packetReceivedInfoBoat(PacketInfoBoat p) {
		// nothing to do
	}

	/*
	 * public void packetReceivedLogin(PacketLogin p){ //login } public void
	 * packetReceivedSubscribe(PacketSubscribe p){ //subscribe user } public
	 * void packetReceivedBuyCard(PacketBuyCard p){ //user buy card return
	 * transaction update } public void
	 * packetReceivedConsultShop(PacketConsultShop p){ //Shop consulting .. send
	 * list of PacketNewCard }
	 */
	public void packetReceivedCardAction(PacketCardAction p) {
		int x = p.getX();
		int y = p.getY();
		int cid = p.getIdCard();
		
		
		
		System.out.println(x+' '+y);
		if (gameboard[x][y] != 0) {
			System.out.println(gameboard[x][y]);
			if (p.getIdSource() == 1) {
				p2BoatLife[gameboard[x][y] - 3-1]--;
				player2.sendMessage(new PacketInfoBoat(0, gameboard[x][y], 0,
						0, 0, 0, p2BoatLife[gameboard[x][y]-3-1]));
			} else {
				p1BoatLife[gameboard[x][y]-1]--;
				player1.sendMessage(new PacketInfoBoat(0, gameboard[x][y], 0,
						0, 0, 0, p1BoatLife[gameboard[x][y]-1]));
			}
			this.checkVictory();
		}
		System.out.println(Arrays.toString(p1BoatLife));
		System.out.println(Arrays.toString(p2BoatLife));
		player1.sendMessage(new PacketCardAction(serverId,p.getIdCard(),p.getX(),p.getY()));
		player2.sendMessage(new PacketCardAction(serverId,p.getIdCard(),p.getX(),p.getY()));
		
	}

	public void packetReceivedUpdate(PacketUpdate p) {
		timer.cancel();
		timer.scheduleAtFixedRate(task, 0, Game.ROUND_TIME_MILLISEC);
		callUpdate();
	}

	/*public void packetReceivedBye(PacketBye p) {
	}*/

	public void checkVictory() {
		int l1 = 0;
		int l2 = 0;
		for (int i = 0; i < 3; i++)
			l1 += p1BoatLife[i];
		for (int i = 0; i < 3; i++)
			l2 += p2BoatLife[i];
		if (l1 == 0) {
			player1.sendMessage(new PacketBye(0, 2));
			player2.sendMessage(new PacketBye(0, 2));
			// tuer connexions
			// tuer l'objet
			// tuer les timers

		}
		if (l2 == 0) {
			player1.sendMessage(new PacketBye(0, 1));
			player2.sendMessage(new PacketBye(0, 1));

		}
	}

	public class UpdateTimerTask extends TimerTask {
		private Game obj;

		public UpdateTimerTask(Game obj) {
			this.obj = obj;
		}

		@Override
		public void run() {
			this.obj.callUpdate();
		}

	}
}
