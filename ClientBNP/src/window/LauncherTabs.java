package window;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import connection.ClientConnection;
import packet.Packet;

public class LauncherTabs extends JPanel implements PanelNotifiable{

	private static final long serialVersionUID = 1L;

	public LauncherTabs(int width, int height,ClientConnection conn) {
		super(new GridLayout(1, 1));     
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(width,height));
		JComponent panel1 = new LauncherHomeTab(conn);
		tabbedPane.addTab("Home", null, panel1,"En construction");
		JComponent panel2 = new LauncherProfileTabsPanel(width, height,conn);
		tabbedPane.addTab("Profile", null, panel2,"En construction");
		add(tabbedPane);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

    @Override
    public void receivePacket(Packet p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
