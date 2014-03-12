package panels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import lib.Tuple;


public class ElementContainer extends JPanel  implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int posX;
	private int posY;
	private int sizeX;
	private int sizeY;
	private Color color;
	private int active;
	private boolean side;
	Tuple posReal;
	
	public ElementContainer(int posX, int posY, int sizeX, int sizeY, Color color, boolean side){
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.color = color;
		this.side = side;
		posReal = Canvas.matchRectangle(posX, posY);
		setBackground(color);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		addMouseListener(this);
	}
	
	public int getRealPosX(){
		return posReal.x;
	}
	
	public int getRealPosY(){
		return posReal.y;
	}
	
	public Color getColor() {
		return color;
	}

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(19,19);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

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
		System.out.println(posX+" "+posY);
		if(Canvas.currentCard == 1){
			this.setOpaque(true);
			this.active = 1;
			this.setBackground(Color.BLUE);
			Canvas.currentCard=0;
			return;
		}
		if(active==1){
			this.setBackground(Color.RED);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}
