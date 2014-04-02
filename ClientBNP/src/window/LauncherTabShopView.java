package window;

import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import connection.ClientConnection;

import packet.Packet;

public class LauncherTabShopView extends JPanel implements PanelNotifiable{
	private static final long serialVersionUID = -5081041334585699399L;
	ClientConnection c;
	
	public LauncherTabShopView(ClientConnection conn){
		c = conn;
		setLayout(new GridBagLayout());
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,10);
		c.gridwidth = 1;
		c.gridheight=1;
		c.weightx = 0.7;
		c.weighty=1;
		c.gridx = 0;
		c.gridy = 0;
		CardDescriptionView d = new CardDescriptionView();
		LauncherShopCardList panel1 = new LauncherShopCardList(conn,d);
		panel1.init();
		c.ipady = 10000;
		add(panel1,c);   
		c.weightx = 0.3;
		c.gridx = 1;
		c.gridy = 0;
		
		add(d,c);
	}
	
	@Override
	public void receivePacket(Packet p) {
		// TODO Auto-generated method stub
		
	}

}
