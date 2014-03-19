package window;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import packet.Packet;

import connection.ClientConnection;

public class LauncherHomeTab extends JPanel implements PanelNotifiable{
	private static final long serialVersionUID = 2324234L;
	ClientConnection c;
	Button startBattle;
	public LauncherHomeTab(ClientConnection conn){
		c=conn;
		this.setLayout(new SpringLayout());
		startBattle = new Button("Start Battle !");
		startBattle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("You clicked the button");
            }
        });
		this.add(startBattle);
 
	}
	@Override
	public void receivePacket(Packet p) {
		// TODO Auto-generated method stub
		
	}
}
