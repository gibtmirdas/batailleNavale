package connection;

import lib.Constantes;
import packet.PacketCardAction;

public class ControllerClient implements Constantes {
	
	private ClientConnection client;
	
	public ControllerClient(){

	}
	
	public void setClient(ClientConnection c){
		client = c;
	}
	
	public void sendMissile(int idSource, int posX,int posY){
		System.out.println("---------------------------------");
		System.out.println("MISSILE ENVOYÉ:");
		System.out.println("idSource: "+idSource);
		System.out.println("posX: "+posX);
		System.out.println("posY: "+posY);
		client.sendMessage(new PacketCardAction(idSource, 0, posX, posY));
	}
	
}
