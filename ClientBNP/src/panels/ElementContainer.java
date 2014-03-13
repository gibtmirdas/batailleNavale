package panels;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import lib.Tuple;


public class ElementContainer extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int posX;
	private int posY;
	private Color color;
	private int active;
	private boolean side;
	Tuple posReal;
	//19x19
	
	public ElementContainer(int posX, int posY, int sizeX, int sizeY, Color color, boolean side){
		this.posX = posX;
		this.posY = posY;
		this.color = color;
		this.side = side;
//		posReal = Canvas.matchRectangle(posX, posY);
		setBackground(color);
		setPreferredSize(new Dimension(sizeX, sizeY));
		setOpaque(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				System.out.println(ElementContainer.this.posX+" "+ElementContainer.this.posY);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
			}
			
		});
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
}
