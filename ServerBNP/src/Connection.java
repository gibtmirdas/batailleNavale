
import db.TJoueurs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Joueur;

import packet.Packet;
import packet.PacketBuilder;
import packet.PacketCardAction;
import packet.PacketHello;
import packet.PacketLogin;
import packet.PacketUpdate;

public class Connection implements Runnable {

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
        switch (c.getName()) {
            case "packet.PacketLogin":
                PacketLogin pl = new PacketLogin(p.encodedPacket);
                String uname = pl.getUsername(), pwd = pl.getPassword();
                TJoueurs tjoueurs = new TJoueurs();
                int pid = tjoueurs.getIdByCriteria(TJoueurs.NAME_FIELD, uname);
                Joueur player = new Joueur(tjoueurs.getById(pid));
                PacketLogin r = player.getPassword().equals(pl.getPassword()) ? new PacketLogin(pid, uname, pwd) : new PacketLogin(pid, "0", "0");
                this.sendMessage(pl);
                break;
            case "packet.PacketSubscribe":
                //todo
                break;
            case "packet.PacketInfoProfile":
                //todo
                break;
            case "packet.PacketBuyCard":
                //todo
                break;
            case "packet.PacketTransactionUpdate":
                //todo
                break;
            case "packet.PacketConsultShop":
                //todo
                break;
            default:
                throw new ClassNotFoundException("Unknown packet");

        }
    }

}
