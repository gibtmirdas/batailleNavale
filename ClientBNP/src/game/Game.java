/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import lib.Tuple;
import packet.Packet;
import packet.PacketBuilder;
import packet.PacketCardAction;
import packet.PacketInfoBoat;
import packet.PacketNewCard;
import packet.PacketUpdate;
import panels.CartePan;
import GUIManager.GUIManager;
import connection.ClientConnection;

/**
 * 
 * @author antho
 */
public class Game {

	private ClientConnection conn;
	private final GUIManager gui = GUIManager.getInstance();

	public Game(ClientConnection conn) {
		this.conn = conn;
	}

	public void handlePacket(Packet pkt) {
		switch (PacketBuilder.getPacketClass(pkt).getName()) {
		case "packet.PacketUpdate":
			PacketUpdate pu = (PacketUpdate) pkt;
			System.out.println("###############	PacketUpdate	############");
			System.out.println("Client: Packet update received!");
			System.out.println("############# END PacketUpdate ######");
			gui.getContainCard().addCartesContent();
			gui.getContainCard().repaint();
			gui.resize();
			break;
		case "packet.PacketInfoBoat":
			PacketInfoBoat pib = (PacketInfoBoat) pkt;
			System.out.println("###############	PacketInfoBoat	############");
			System.out.println("Client: Packet InfoBoat received!");
			System.out.println("1.- IdBoat: " + pib.getIdBoat());
			System.out.println("2.- xStart: " + pib.getXStart());
			System.out.println("3.- yStart: " + pib.getYStart());
			System.out.println("4.- xEnd: " + pib.getXEnd());
			System.out.println("5.- yEnd: " + pib.getYEnd());
			System.out.println("6.- life: " + pib.getLife());
			gui.getCurrentCanvas().Fill(
					new Tuple(pib.getXStart(), pib.getYStart()),
					new Tuple(pib.getXEnd(), pib.getYEnd()));
			System.out.println("############# END PacketInfoBoat ######");
			break;
		case "packet.PacketNewCard":
			PacketNewCard pnc = (PacketNewCard) pkt;
			System.out.println("###############	PacketNewCard	############");
			System.out.println("Client: Packet newCard received!");
			CartePan carte = new CartePan(pnc.getCardId(), "Missile");
			gui.getContainCard().add(carte);
			gui.getContainCard().addCartesContent();
			gui.resize();
			System.out.println("############# END PacketNewCard ######");
			break;
		case "packet.PacketCardAction":
			PacketCardAction pca = (PacketCardAction) pkt;
			System.out.println("###############	PacketCardAction	############");
			System.out.println("Client: Packet cardAction received!");
			System.out.println("1.- idSource: " + pca.getIdSource());
			System.out.println("2.- IdCard: " + pca.getIdCard());
			System.out.println("3.- X: " + pca.getX());
			System.out.println("4.- Y: " + pca.getY());
			gui.getCurrentCanvas().attackMissile(pca.getX(), pca.getY());
			System.out.println("############# END PacketCardAction ######");
			break;
		}
	}
}
