package window;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;


public class LauncherFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	int WIDTH = 600, HEIGHT = 500;
	public LauncherFrame(){
		this.setTitle("Launcher v0.1");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
    	//Add content to the window.
    	this.add(new LauncherTabs(WIDTH,HEIGHT), BorderLayout.CENTER);
    	//Display the window.
    	this.pack();
    	this.setVisible(true);
    }
 
    public static void main(String[] args) {
    	LauncherFrame frame = new LauncherFrame();
    	
    }
}
