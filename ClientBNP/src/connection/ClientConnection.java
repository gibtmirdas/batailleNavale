package connection;

import java.io.IOException;
import java.io.OutputStream;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;

import lib.Constantes;
import packet.Packet;
import packet.PacketBye;
import packet.PacketCardAction;
import packet.PacketInfoBoat;
import packet.PacketNewCard;
import packet.PacketNewGame;
import packet.PacketUpdate;
import panels.Boat;
import panels.ContainerGame;
import window.ConnectWindow;
import window.WindowMain;

public class ClientConnection implements Constantes{
	
	private Socket s;
	private OutputStream os;
	private ReadThead readTh;
	private ConnectWindow connectWin;
	private WindowMain mainWin;
	private ContainerGame containGame;

	public ClientConnection() {
//		try {
//			s = new Socket(ADDRESS, PORT);
//			System.out.println("Client launched!");
//			os = s.getOutputStream();
//			connectWin = new ConnectWindow(this);
//			readTh = new ReadThead(s, this);
//			new Thread(readTh).start();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		PacketNewGame p = null;
		PacketNewCard p1 = new PacketNewCard(1, 0);
		PacketNewCard p4 = new PacketNewCard(1, 0);

		PacketInfoBoat p2 = new PacketInfoBoat(1,1,0,0,2,0,3);
		PacketInfoBoat p5 = new PacketInfoBoat(1,1,10,0,2,0,3);

		PacketUpdate p3 = new PacketUpdate(1, 1);
		packetReceivedNewGame(p);
		packetReceivedNewCard(p1);
		packetReceivedNewCard(p4);
		packetReceivedInfoBoat(p2);
		packetReceivedInfoBoat(p5);
		packetReceivedUpdate(p3);
	}
	
	public void analysePacket(byte[] datas) throws ClassNotFoundException {		
		switch (datas[0]) {
			case 0:
//				PacketHello pHello = new PacketHello(datas);
				System.out.println("packet hello");
				break;
			case 1:
				PacketNewGame pNewGame = new PacketNewGame(datas);
				packetReceivedNewGame(pNewGame);
				break;
			case 2:
				PacketNewCard pNewCard = new PacketNewCard(datas);
				packetReceivedNewCard(pNewCard);
				break;
			case 3:
				PacketInfoBoat pInfoBoat = new PacketInfoBoat(datas);
				packetReceivedInfoBoat(pInfoBoat);
				break;
			case 4:
				PacketCardAction pCardAction = new PacketCardAction(datas);
				packetReceivedCardAction(pCardAction);
				break;
			case 5:
				PacketUpdate pUpdate = new PacketUpdate(datas);
				packetReceivedUpdate(pUpdate);
				break;
			case 6:
				PacketBye pBye = new PacketBye(datas);
				packetReceivedBye(pBye);
				break;
			default:
	          throw new ClassNotFoundException("Unknown packet");
		}
	}
	
	public void packetReceivedNewGame(PacketNewGame p) {
//		connectWin.dispose();
		mainWin = new WindowMain();
		containGame = mainWin.getPanelGame();
		System.out.println("###############	PacketNewGame	############");
//		System.out.println("Client: Packet newGame received!");
//		System.out.println("1.- Time: "+p.getTime());
		System.out.println("############# END PacketNewGame ######");

	}

	public void packetReceivedNewCard(PacketNewCard p) {
//		System.out.println("###############	PacketNewCard	############");
//		System.out.println("Client: Packet newCard received!");
////		System.out.println("1.- CardId: "+p.getCardId());
//		System.out.println("############# END PacketNewCard ######");
//		containGame.addCarte(p.getCardId(), "Missile");
	}

	public void packetReceivedInfoBoat(PacketInfoBoat p) {
		System.out.println("###############	PacketInfoBoat	############");
		System.out.println("Client: Packet InfoBoat received!");
		
//		System.out.println("1.- IdBoat: "+p.getIdBoat());
//		System.out.println("2.- xStart: "+p.getXStart());
//		System.out.println("3.- yStart: "+p.getYStart());
//		System.out.println("4.- xEnd: "+p.getXEnd());
//		System.out.println("5.- yEnd: "+p.getYEnd());
//		System.out.println("6.- life: "+p.getLife());
		System.out.println("############# END PacketInfoBoat ######");
		Boat boat = new Boat(p.getIdBoat(), p.getXStart(), p.getYStart(), p.getXEnd(), p.getYEnd(), p.getLife());
//		containGame.getCanva().getel

	}
	
	public void packetReceivedUpdate(PacketUpdate p) {
//		System.out.println("###############	PacketUpdate	############");
//		System.out.println("Client: Packet update received!");
////		System.out.println("1.- IdUser: "+p.getIdUser());
//		System.out.println("############# END PacketUpdate ######");
		containGame.addCartes();
	}
	
	public void packetReceivedCardAction(PacketCardAction p){
		System.out.println("###############	PacketCardAction	############");
		System.out.println("Client: Packet cardAction received!");	
		System.out.println("1.- IdCard: "+p.getIdCard());
		System.out.println("2.- X: "+p.getX());
		System.out.println("3.- Y: "+p.getY());
		System.out.println("############# END PacketCardAction ######");
	}
	
	public void packetReceivedBye(PacketBye p){
		System.out.println("Client: Packet Bye received!");
	}
	
	public void sendMessage(Packet p) {
		try {
			System.out.println(Arrays.toString(p.encodedPacket));
			os.write(p.encodedPacket);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] getMacAddress(){
		try {
//			InetAddress ip = InetAddress.getLocalHost();
			Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
			while(networks.hasMoreElements()) {
				NetworkInterface network = networks.nextElement();
				byte[] mac = network.getHardwareAddress();
				if(mac != null) {
//			        StringBuilder sb = new StringBuilder();
//			        for (int i = 0; i < mac.length; i++) {
//			          sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
//			        }
//			        return sb.toString();
//			      }
					return mac;
				}
			}
		  } catch (SocketException e){
		    e.printStackTrace();
		  }
		return null;
	}
}
