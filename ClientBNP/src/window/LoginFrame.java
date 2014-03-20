/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package window;

import GUIManager.GUIManager;
import connection.ClientConnection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import packet.PacketLogin;

/**
 *
 * @author antho
 */
public class LoginFrame extends JPanel implements ActionListener {

    private JPanel pane;
    private final int width = 400, height = 200;
    private final String[] labels = {"Nickname: ", "Password: "};
    private ClientConnection connection;

    public LoginFrame(ClientConnection conn) throws HeadlessException {
        super();
        this.connection = conn;
        buildContentPanel();
    }

    public final void buildContentPanel() {
        setLayout(new SpringLayout());

        int numPairs = labels.length;
        //Create and populate the panel.
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            add(l);
            JTextField textField = new JTextField(10);
            textField.setName(labels[i]);
            l.setLabelFor(textField);
            add(textField);
        }
        JButton blogin = new JButton("login");
        blogin.addActionListener(this);
        JButton bsub = new JButton("subscribe");
        bsub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GUIManager.getInstance().launchSubscribeFrame(connection);
            }
        });
        add(bsub);
        add(blogin);

        SpringUtilities.makeCompactGrid(this,
                numPairs + 1, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String uname = new String(), pwd = new String();
        for (Component c : this.getComponents()) {
            if (c instanceof JTextField && c.getName().equals(labels[0])) {
                uname = ((JTextField) c).getText();
            }
            if (c instanceof JTextField && c.getName().equals(labels[1])) {
                pwd = ((JTextField) c).getText();
            }
        }
        if (uname.equals("") || pwd.equals("")) {
            GUIManager.getInstance().buildAlertDialog("Informations error", "Empty infos", false);
            return;
        }
        try {
            PacketLogin p = new packet.PacketLogin(0, uname, pwd);
            connection.sendMessage(p);
        } catch (NullPointerException npe) {
            GUIManager.getInstance().buildAlertDialog("Server error", "Cannot connect to server", false);
        }
    }

}
