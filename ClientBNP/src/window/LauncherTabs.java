package window;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class LauncherTabs extends JPanel {

	private static final long serialVersionUID = 1L;

	public LauncherTabs(int width, int height) {
		super(new GridLayout(1, 1));     
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(width,height));
		JComponent panel1 = new LauncherHomeTab();
		tabbedPane.addTab("Home", null, panel1,"En construction");
		JComponent panel2 = new LauncherProfileTabsPanel(width, height);
		tabbedPane.addTab("Profile", null, panel2,"En construction");
		add(tabbedPane);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
}
