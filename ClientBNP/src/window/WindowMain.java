package window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import panels.ContainerGame;


public class WindowMain extends JFrame implements ActionListener, ItemListener{
	
	private static final long serialVersionUID = 1L;
	private ContainerGame panelGame;
//	private int width = 927;
//	private int height = 749;
	private int width = 1000;
	private int height = 1000;
	
	public WindowMain(){
		runGUI();
	}
	
	public void runGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar bar = createBar();
		setJMenuBar(bar);
		panelGame = new ContainerGame(getContentPane());
		//setSize(width, height);
		setTitle("Bataille Navale");
		add(panelGame);
		pack();
		setLocationByPlatform(true);
		setVisible(true);		
	}
	
	private JMenuBar createBar(){
		JMenuBar menuBar;
		JMenu menu;
//		JMenuItem menuItem;
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("Home");
		menu.setName("ddd");
		//VK_A => key released
        menu.setMnemonic(KeyEvent.VK_M);
        menu.addMenuListener(new TopMenuListener());
		menuBar.add(menu);
		
		//Build second menu in the menu bar.
		menu = new JMenu("Profile");
		menu.setName("asdasd");
        menu.setMnemonic(KeyEvent.VK_M);
        menu.addMenuListener(new TopMenuListener());
		menuBar.add(menu);
		
		/*
		//a group of JMenuItems
		menuItem = new JMenuItem("USER",KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);

		menu.add(menuItem);
		menuItem = new JMenuItem("CARTES",KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);

		menu.add(menuItem);
		menuItem = new JMenuItem("DECKS",KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.addActionListener(this);
		menu.add(menuItem);
       */
		return menuBar;
	}
	
	public ContainerGame getPanelGame() {
		return panelGame;
	}

	public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}
	
}
