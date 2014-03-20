package connection;

import GUIManager.GUIManager;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Enumeration;

import lib.Constantes;
import lib.Tuple;
import packet.Packet;
import packet.PacketBye;
import packet.PacketCardAction;
import packet.PacketInfoBoat;
import packet.PacketLogin;
import packet.PacketNewCard;
import packet.PacketNewGame;
import packet.PacketUpdate;
import panels.Boat;
import panels.Carte;
import window.ConnectWindow;
import window.FrameMain;

public class ClientConnection implements Constantes {
    private boolean connected;
    private Socket s;
    private OutputStream os;
    private ReadThead readTh;
    private ConnectWindow connectWin;
    private FrameMain frameMain;
    private final int IDSOURCE = 1;
    private final GUIManager gui = GUIManager.getInstance();

    public ClientConnection() {
        try {
            connected = false;
            s = new Socket(ADDRESS, PORT);
            System.out.println("Client launched!");
            os = s.getOutputStream();
            readTh = new ReadThead(s, this);
            new Thread(readTh).start();
            gui.launchLoginFrame(this);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//		PacketNewGame p = null;
//		PacketNewCard p1 = new PacketNewCard(1, 0);
//		PacketNewCard p4 = new PacketNewCard(1, 0);
//
//		PacketInfoBoat p2 = new PacketInfoBoat(1,1,0,0,2,2,3);
//		PacketInfoBoat p5 = new PacketInfoBoat(1,1,5,5,5,9,3);
//
//		PacketUpdate p3 = new PacketUpdate(1, 1);
//		packetReceivedNewGame(p);
////		packetReceivedNewCard(p1);
////		packetReceivedNewCard(p4);
//		packetReceivedInfoBoat(p2);
//		packetReceivedInfoBoat(p5);
//		packetReceivedUpdate(p3);
    }

    public void analysePacket(byte[] datas) throws ClassNotFoundException, UnsupportedEncodingException {
        switch (datas[0]) {
            case 0:
                //PacketHello pHello = new PacketHello(datas);
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
            case 0x07:
                packetReceivedLogin(new PacketLogin(datas));
                break;
            case 0x08:
                break;
            case 0x09:
                break;
            case 0x0A:
                break;
            case 0x0B:
                break;
            case 0x0C:
                break;
            default:
                throw new ClassNotFoundException("Unknown packet");
        }
    }

    public void packetReceivedNewGame(PacketNewGame p) {
        System.out.println("###############	PacketNewGame	############");
        System.out.println("Client: Packet newGame received!");
        //		connectWin.dispose();
        // now do gui.launchMainFrame()
        System.out.println("############# END PacketNewGame ######");

    }

    public void packetReceivedNewCard(PacketNewCard p) {
        System.out.println("###############	PacketNewCard	############");
        System.out.println("Client: Packet newCard received!");
        if (IDSOURCE == p.getIdSource()) {
            Carte carte = null;

            switch (p.getCardId()) {
                case 0:
                    carte = new Carte(p.getCardId(), "Missile");
                    break;
                default:
                    break;
            }
//			frameMain.getCanvas().addCarte(carte);
//now do gui.getCurrentCanvas().addCarte(carte);
        }
        System.out.println("############# END PacketNewCard ######");
    }

    public void packetReceivedInfoBoat(PacketInfoBoat p) {
        System.out.println("###############	PacketInfoBoat	############");
        System.out.println("Client: Packet InfoBoat received!");
        System.out.println("1.- IdBoat: " + p.getIdBoat());
        System.out.println("2.- xStart: " + p.getXStart());
        System.out.println("3.- yStart: " + p.getYStart());
        System.out.println("4.- xEnd: " + p.getXEnd());
        System.out.println("5.- yEnd: " + p.getYEnd());
        System.out.println("6.- life: " + p.getLife());

//		Boat boat = new Boat(p.getIdBoat(), p.getXStart(), p.getYStart(), p.getXEnd(), p.getYEnd(), p.getLife());	
//		frameMain.getCanvas().addBoat(boat);
//		frameMain.getCanvas().Fill(p.getXStart(), p.getYStart(), p.getXEnd(), p.getYEnd());
        //gui.getCurrentCanvas().Fill(new Tuple(p.getXStart(), p.getYStart()), new Tuple(p.getXEnd(), p.getYEnd()));

        System.out.println("############# END PacketInfoBoat ######");
    }

    public void packetReceivedUpdate(PacketUpdate p) {
        System.out.println("###############	PacketUpdate	############");
        System.out.println("Client: Packet update received!");
//		System.out.println("1.- IdUser: "+p.getIdUser());
        System.out.println("############# END PacketUpdate ######");

    }

    public void packetReceivedCardAction(PacketCardAction p) {
        System.out.println("###############	PacketCardAction	############");
        System.out.println("Client: Packet cardAction received!");
        System.out.println("1.- IdCard: " + p.getIdCard());
        System.out.println("2.- X: " + p.getX());
        System.out.println("3.- Y: " + p.getY());
        System.out.println("############# END PacketCardAction ######");
    }

    public void packetReceivedBye(PacketBye p) {
        System.out.println("Client: Packet Bye received!");
    }
    
    public void packetReceivedLogin(PacketLogin p) throws UnsupportedEncodingException{
        if(p.isAccepted()){
            connected = true;
            gui.launchMainFrame();
        }else{
            gui.buildAlertDialog("Login error", "Bad informations", false);
        }
            
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

    public byte[] getMacAddress() {
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            while (networks.hasMoreElements()) {
                NetworkInterface network = networks.nextElement();
                byte[] mac = network.getHardwareAddress();
                if (mac != null) {
                    return mac;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}
