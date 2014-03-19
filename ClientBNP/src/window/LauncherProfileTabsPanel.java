package window;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import connection.ClientConnection;

public class LauncherProfileTabsPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public LauncherProfileTabsPanel(int width, int height,ClientConnection conn) {
		super(new GridLayout(1, 1));     
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(width,height));
		JComponent panel1 = new LauncherTabUserPanel(conn);
		tabbedPane.addTab("User", null, panel1,"En construction");
		JComponent panel2 = makeTextPanel("En construction");
		tabbedPane.addTab("Cards", null, panel2,"Does twice as much nothing");
		//tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
	         
		JComponent panel3 = makeTextPanel("Prochaine itération");
		tabbedPane.addTab("Decks", null, panel3,"Still does nothing");
	    //tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
	         
		//Add the tabbed pane to this panel.
		add(tabbedPane);
		//The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
	     
	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}
}
