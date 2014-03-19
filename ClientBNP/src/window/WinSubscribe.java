package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import connection.ClientConnection;
import packet.PacketSubscribe;
import window.SpringUtilities;

public class WinSubscribe extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField usernameField, password1Field, password2Field;
	private JLabel usernameLabel, password1Label, password2Label;
	private JButton confirmButt, cancelButt;
	private ClientConnection clientConnection;
	private int idSource = 1;

	public WinSubscribe(ClientConnection clientConnection) {
		this.clientConnection = clientConnection;
		buildPan();
		this.setVisible(true);
	}

	private void buildPan() {
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);

		usernameLabel = new JLabel("Username");
		password1Label = new JLabel("password");
		password2Label = new JLabel("confirm");
		usernameField = new JTextField("user");
		password1Field = new JPasswordField("pass");
		password2Field = new JPasswordField("pass");
		confirmButt = new JButton("Create");
		cancelButt = new JButton("Cancel");

		confirmButt.addActionListener(this);
		cancelButt.addActionListener(this);

		this.add(usernameLabel);
		this.add(usernameField);
		this.add(password1Label);
		this.add(password1Field);
		this.add(password2Label);
		this.add(password2Field);
		this.add(confirmButt);
		this.add(cancelButt);

		SpringUtilities.makeCompactGrid(this, 4, 2, 10, 10, 6, 20);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Confirm button
		if (e.getSource() == confirmButt) {
			createAction();
			// Cancel button
		} else if (e.getSource() == cancelButt) {
			cancelAction();
		}
	}

	public void createAction() {
		String username = usernameField.getText();
		String pwd1 = password1Field.getText();
		String pwd2 = password2Field.getText();

		if (username == null || username.length() < 4) {
			buildAlertDialog("Error", "Username cannot be empty", false);
			return;
		}
		if (pwd1 == null || pwd1.length() < 4 || pwd2 == null) {
			if (pwd1 == null || pwd1 == null)
				buildAlertDialog("Error", "Password cannot be null", false);
			else
				buildAlertDialog("Error",
						"Password length must be higher than 4", false);
			return;
		}
		if (!pwd2.equals(pwd1)) {
			buildAlertDialog("Error", "Passwords are not equals", false);
			return;
		}
		// Everything ok => send PacketSubscribe
		PacketSubscribe sub = new PacketSubscribe(idSource, username, pwd1);
		clientConnection.sendMessage(sub);
		buildAlertDialog("OK", "Waiting for server response", true);
	}

	public void cancelAction() {
		// this.dispose();
	}

	public void buildAlertDialog(String title, String msg, boolean isOk) {
		int type = isOk ? JOptionPane.INFORMATION_MESSAGE
				: JOptionPane.ERROR_MESSAGE;
		JOptionPane.showMessageDialog(this, msg, title, type);
	}
}
