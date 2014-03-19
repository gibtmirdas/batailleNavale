package window;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import connection.ClientConnection;

public class LauncherTabs extends JPanel {

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
}
