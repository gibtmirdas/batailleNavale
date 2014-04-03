package window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import packet.PacketHello;
import packet.PacketMatchMaking;
import connection.ClientConnection;

public class ConnectWindow extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton button;
	private int windowWidth = 300;
	private int windowHeight = 200;
	private ClientConnection clientConnection;
	private int idSource = 1;
	private JLabel label;
	private JTextField fieldUsername;

	public static final String title = "Connection";

	public ConnectWindow(ClientConnection clientConnection) {
		this.clientConnection = clientConnection;
		setSize(windowWidth, windowHeight);
		panel = new JPanel();
		label = new JLabel("");

		fieldUsername = new JTextField();
		button = new JButton("Start Game");
		button.addActionListener(this);
		panel.add(fieldUsername, BorderLayout.PAGE_START);
		panel.add(button, BorderLayout.PAGE_START);
		panel.add(label, BorderLayout.PAGE_END);
		add(panel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
                GUIManager.GUIManager.getInstance().launchTab(clientConnection);
		if (e.getSource() == button) {
			PacketHello pHello = new PacketHello(idSource,
					clientConnection.getMacAddress());
			clientConnection.sendMessage(pHello);
			label.setText("Waiting another player...");

			// Player2 start game with a player identified by his username
			if (fieldUsername.getText().length() != 0) {
				PacketMatchMaking pmm = new PacketMatchMaking(idSource,
						fieldUsername.getText().getBytes());
				clientConnection.sendMessage(pmm);
			}
		}
	}
}
