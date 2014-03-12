package panels;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Carte extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	private int id ;
	private String type;
	private JLabel label;
	
	public Carte(int id, String type){
		this.type = type;
		this.id   =  id;
		//this.setBackground(Color.BLUE);
		addMouseListener(this);
		label = new JLabel(type);
		add(label);
	}
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(50,100);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("type: "+ type);
		Canvas.currentCard=1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
