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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import packet.Packet;
import packet.PacketLogin;

/**
 *
 * @author antho
 */
public class LoginFrame extends JPanel implements ActionListener , PanelNotifiable{

    private JPanel pane;
    private JTextField uf,pf;
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
        JLabel l = new JLabel(labels[0], JLabel.TRAILING);
        add(l);
        uf = new JTextField(10);
        l.setLabelFor(uf);
        add(uf);
        
        l = new JLabel(labels[1], JLabel.TRAILING);
        add(l);
        pf = new JPasswordField(10);
        l.setLabelFor(pf);
        add(pf);
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

        String uname = uf.getText(), pwd = pf.getText();
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

    @Override
    public void receivePacket(Packet p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
