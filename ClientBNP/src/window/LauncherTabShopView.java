package window;

import javax.swing.JPanel;

import connection.ClientConnection;

import packet.Packet;

public class LauncherTabShopView extends JPanel implements PanelNotifiable{
	private static final long serialVersionUID = -5081041334585699399L;
	ClientConnection c;
	
	public LauncherTabShopView(ClientConnection conn){
		c = conn;
	}
	
	@Override
	public void receivePacket(Packet p) {
		// TODO Auto-generated method stub
		
	}

}
