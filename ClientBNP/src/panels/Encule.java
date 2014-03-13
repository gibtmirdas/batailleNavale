package panels;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Encule extends JFrame{
	
	public Encule() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(600, 600));
		Boat boat = new Boat();
		add(boat);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new Encule();
	}

}
