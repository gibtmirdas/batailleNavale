package window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import packet.Packet;
import packet.PacketHello;
import packet.PacketMatchMaking;
import connection.ClientConnection;

public class ConnectWindow extends JPanel implements ActionListener,
		PanelNotifiable, MouseListener {

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
	private JTable table;
	private String[][] list;

	public static final String title = "Connection";

	public ConnectWindow(ClientConnection clientConnection, String[][] list) {
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

		JTable table = createTable(list);
		if (table != null)
			panel.add(createTable(list));
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

	private JTable createTable(String[][] list) {
		this.list = list;
		if (list == null) {
			return null;
		}
		String[] entetes = { "Id", "Username", "Score" };
		JTable table = new JTable(list, entetes);
		return table;
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		int row = table.getSelectedRow();
		fieldUsername.setText(list[row][1]);
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receivePacket(Packet p) {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}
}
