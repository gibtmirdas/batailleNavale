package window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import packet.PacketHello;
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

	public ConnectWindow(ClientConnection clientConnection) {
		this.clientConnection = clientConnection;
		setSize(windowWidth, windowHeight);
		panel = new JPanel();
		label = new JLabel("");
		button = new JButton("Start Game");
		button.addActionListener(this);
		panel.add(button, BorderLayout.PAGE_START);
		panel.add(label, BorderLayout.PAGE_END);
		add(panel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PacketHello pHello = new PacketHello(idSource,
				clientConnection.getMacAddress());
		clientConnection.sendMessage(pHello);
		label.setText("Waiting another player...");
	}
}
