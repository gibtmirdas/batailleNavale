package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CartePan extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private int id;
	private String type;
	private JLabel label;
	private int width = 50;
	private int height = 100;

	public CartePan(int id, String type) {
		this.type = type;
		this.id = id;
		setSize(width, height);
		setBackground(Color.BLUE);
		label = new JLabel(type, JLabel.LEFT);
		add(label);
		addMouseListener(this);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	@Override
	public Dimension getMaximumSize() {
		return new Dimension(width, height);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Carte appuyee id " + CartePan.this.id + "]\ttype:["
				+ CartePan.this.type + "]");
		Canvas.cardIdSelected = id;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}