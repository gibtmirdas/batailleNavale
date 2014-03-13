
import db.TJoueurs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Carte;
import models.Joueur;

import packet.Packet;
import packet.PacketBuilder;
import packet.PacketBuyCard;
import packet.PacketCardAction;
import packet.PacketHello;
import packet.PacketLogin;
import packet.PacketSubscribe;
import packet.PacketUpdate;

public class Connection implements Runnable {
	Joueur player = null;
    Socket s;
    OutputStream os;
    InputStream is;
    Game g;
    Server serv;
    Map<String, Connection> mapping;
    String id;

    public Connection(Socket s, Server srv) {
        this.s = s;
        this.serv = srv;
        this.mapping = serv.mapping;
        try {
            os = s.getOutputStream();
            is = s.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO gerer reconnection
    @Override
    public void run() {
        Packet p = null;
        try {

            p = PacketBuilder.build(PacketBuilder.readPacket(is));
            PacketHello ph = new PacketHello(p.encodedPacket);
            mapping.put(Arrays.toString(ph.getMacAddress()), this);
            serv.queue.notifyQueue(Arrays.toString(ph.getMacAddress()));
            while (true) {
                p = PacketBuilder.build(PacketBuilder.readPacket(is));
                if (g != null) {
                    g.traiterPacket(p);
                } else {
                    if (p.getOpCode() >= 0x7 && p.getOpCode() <= 0xC) {
                        handleLauncherPacket(p);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    public void sendMessage(Packet p) {
        try {
            os.write(p.encodedPacket);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setG(Game g) {
        this.g = g;
    }

    public void handleLauncherPacket(Packet p) throws ClassNotFoundException {
        Class c = packet.PacketBuilder.getPacketClass(p);
        Packet r;
        String uname,pwd;
        TJoueurs tjoueurs = new TJoueurs();
        int pid;
        switch (c.getName()) {
            case "packet.PacketLogin":
                PacketLogin pl = new PacketLogin(p.encodedPacket);
                uname = pl.getUsername();
                pwd = pl.getPassword();
                pid = tjoueurs.getIdByCriteria(TJoueurs.NAME_FIELD, uname);
                player = Joueur.getJoueur(uname, pwd);
                r = player != null? new PacketLogin(0, uname, pwd) : new PacketLogin(0, "0", "0");
                this.sendMessage(r);
                break;
            case "packet.PacketSubscribe":
                PacketSubscribe ps = new PacketSubscribe(p.encodedPacket);
                uname = ps.getUsername();
                pwd = ps.getPassword();
                pid = tjoueurs.getIdByCriteria(TJoueurs.NAME_FIELD, uname);
                if(tjoueurs.getById(pid) != null){
                    r = new PacketSubscribe(0, "0", "0");
                }else{
                    HashMap<String, Object> prms = new HashMap<String, Object>();
                    prms.put(TJoueurs.NAME_FIELD, uname);
                    prms.put(TJoueurs.PASSWORD_FIELD, pwd);
                    tjoueurs.insert(prms);
                    r = new PacketSubscribe(0, uname, pwd);
                }
                
                this.sendMessage(r);
                break;
            case "packet.PacketBuyCard":
            	PacketBuyCard pbc = new PacketBuyCard(p.encodedPacket);
            	
                //todo
            	//Si connecte
            	//Check solde + prix carte
            	//Si Solde - prix carte >= 0
            	//Ok
            		//Packet Transaction Update
            		//Packet info profile (maj)
            		//maj DB
            	//Sinon pas ok
            	//Packet Transaction Update
            	if(player==null)
            	{
            		//Packet
            		break;
            	}
            	player.canBuyCard(new Carte(pbc.getCardID()));
            	
            	Carte c = new Carte(1);
            	
            	
                break;
            case "packet.PacketConsultShop":
            	//INUTILISE
                //todo
            	//Si connecte
            	//Packet ConsultShop pcs = new PacketConsultShop(p.encoded packet);
            	//Cards = getCardsByCategory(pcs.getCategory())
            	//For each card in Cards
            	//send New Card....
            	//
                break;
            default:
                throw new ClassNotFoundException("Unknown packet");

        }
    }

}
