package window;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import packet.Packet;

import connection.ClientConnection;

public class LauncherTabUserPanel extends JPanel implements PanelNotifiable{
	private static final long serialVersionUID = -78596758792477352L;
		int nbrVictories=100,nbrDefeats=35,nbrCards=321;
		ClientConnection c;
		public LauncherTabUserPanel(ClientConnection conn){
			c = conn;
			JLabel userNameLabel = new JLabel();
			JLabel victoriesLabel = new JLabel();
			JLabel defeatsLabel = new JLabel();
			JLabel possessedCards = new JLabel();
			userNameLabel.setText("My User Name");
			victoriesLabel.setText("Victories : "+nbrVictories);
			defeatsLabel.setText("Defeats : "+nbrDefeats);
			possessedCards.setText("Possessed cards : "+nbrCards);
			this.setLayout(new GridLayout(4,1));
			this.add(userNameLabel);
			this.add(victoriesLabel);
			this.add(defeatsLabel);
			this.add(possessedCards);
		}
		@Override
		public void receivePacket(Packet p) {			
		}
}
