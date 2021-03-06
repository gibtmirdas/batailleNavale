import db.TCartes;
import db.TJoueurs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Carte;
import models.FactoryCarte;
import models.Joueur;
import packet.Packet;
import packet.PacketBuilder;
import packet.PacketBuyCard;
import packet.PacketClientList;
import packet.PacketConsultShop;
import packet.PacketHello;
import packet.PacketInfoProfile;
import packet.PacketLogin;
import packet.PacketMatchMaking;
import packet.PacketNewCard;
import packet.PacketSubscribe;
import packet.PacketTransactionUpdate;

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
		}
	}

	// TODO gerer reconnection
	@Override
	public void run() {
		try {
			Packet p;
			while (true) {
				p = PacketBuilder.build(PacketBuilder.readPacket(is));
				if (g != null) {
					g.traiterPacket(p);
				} else {

					if (p.getOpCode() >= 0x7 && p.getOpCode() <= 0xC
							|| p.getOpCode() == 0) {
						handleLauncherPacket(p);
					}
				}
			}
		} catch (IOException e) {
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
		}
	}

	public void setG(Game g) {
		this.g = g;
	}

	public void handleLauncherPacket(Packet p) throws ClassNotFoundException,
			UnsupportedEncodingException {
		Class c = packet.PacketBuilder.getPacketClass(p);
		Packet response;
		String uname, pwd;
		TJoueurs tjoueurs = new TJoueurs();
		TCartes tcartes = new TCartes();
		int pid;
		switch (c.getName()) {
		case "packet.PacketLogin":
			PacketLogin pl = new PacketLogin(p.encodedPacket);
			uname = pl.getUsername();
			pwd = pl.getPassword();
			player = Joueur.getJoueur(uname, pwd);
			System.err.println(player);
			response = player != null ? new PacketLogin(0, uname, pwd)
					: new PacketLogin(0, "", "");
			this.sendMessage(response);
			break;
		case "packet.PacketSubscribe":
			PacketSubscribe ps = new PacketSubscribe(p.encodedPacket);
			uname = ps.getUsername();
			pwd = ps.getPassword();
			pid = tjoueurs.getIdByCriteria(TJoueurs.NAME_FIELD, uname);
			if (tjoueurs.existJoueur(uname)) {
				response = new PacketSubscribe(0, "0", "0");
			} else {
				HashMap<String, Object> prms = new HashMap<>();
				prms.put(TJoueurs.NAME_FIELD, uname);
				prms.put(TJoueurs.PASSWORD_FIELD, pwd);
				tjoueurs.insert(prms);
				response = new PacketSubscribe(0, uname, pwd);
			}
			this.sendMessage(response);
			break;
		case "packet.PacketBuyCard":
			PacketBuyCard pbc = new PacketBuyCard(p.encodedPacket);
			if (player == null) {
				response = new PacketLogin(0, "0", "0");
				this.sendMessage(response);
				break;
			}
			Carte askedCard = FactoryCarte.getCarte(tcartes.getById(pbc
					.getCardID()));

			if (player.canBuyCard(askedCard)) {
				player.buyCard(askedCard);
				response = new PacketTransactionUpdate(0, pbc.getCardID(), 1);
				this.sendMessage(response);
				response = new PacketInfoProfile(0, 0, 0, 0, player.getCredit());
				this.sendMessage(response);
			} else {
				response = new PacketTransactionUpdate(0, pbc.getCardID(), 0);
				this.sendMessage(response);
			}
			break;
		case "packet.PacketConsultShop":
			/**
			 * Have to send the list of the card (by categoria)
			 */
			PacketConsultShop pcs = new PacketConsultShop(p.encodedPacket);
			TCartes tc = new TCartes();
			List<Integer> lst = tc.getAllIdByCriteria(TCartes.TYPE_FIELD,
					pcs.getCategory());
			for (Integer cardID : lst)
				sendMessage(new PacketNewCard(0, cardID));
		case "packet.PacketHello":
			PacketHello ph = new PacketHello(p.encodedPacket);
			System.out.println(Arrays.toString(ph.getMacAddress()));
			mapping.put(Arrays.toString(ph.getMacAddress()), this);
			serv.queue.notifyQueue(Arrays.toString(ph.getMacAddress()));
			// Notify every client => send PacketClientList
			notifyClientList();
			break;
		case "packet.PacketMatchMaking":
			PacketMatchMaking pmm = new PacketMatchMaking(p.encodedPacket);
			serv.queue.createGame(pmm.getIdSource(),
					new String(pmm.getUsername()));
			break;
		default:
			throw new ClassNotFoundException("Unknown packet");

		}
	}

	private void notifyClientList() {
		String tmpList = "";
		for (Connection c : mapping.values()) {
			tmpList += c.player.getId() + "/";
			tmpList += c.player.getPseudo() + "/";
			tmpList += c.player.getScore() + "-";
		}
		PacketClientList pCl = new PacketClientList(0, tmpList.getBytes());
		for (Connection c : mapping.values())
			c.sendMessage(pCl);
	}

}
