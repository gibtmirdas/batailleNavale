package panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class GridDraw extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int canvasLength;
	private int sizeSquare;
	
	public GridDraw(int canL, int sizeL) {
		canvasLength=canL;
		sizeSquare=sizeL;
		setAlignmentX(0.0f);
		setAlignmentY(0.0f);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		System.out.println("  A A A A A A A -->");
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLACK);
		
		for( int i = 0; i < canvasLength; i ++){
			for( int j = 0; j < (canvasLength/4)*3; j++){
//				Rectangle grid = new Rectangle(i*size, j*size, size, size);
				Rectangle rect = new Rectangle(i*sizeSquare, j*sizeSquare, sizeSquare, sizeSquare);
				g2d.draw(rect);             
			}
		}

		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawLine(381, 0, 381, 585);
		
	}
	
	@Override
	public boolean isOpaque() {
		return true;
	}
	
	@Override
	public Dimension getPreferredSize() {
//		return new Dimension(canvasLength*20, (canvasLength/4)*3*20);
		return new Dimension(1000, 1000);

	}
	
	@Override
	public Dimension getMaximumSize() {
		return new Dimension(1000, 1000);
	}
	/*
	  public void displayShips(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for(ElementContainer s: ContainerGame.ShipList){		
			g2.setColor(s.getColor());
			g2.fillRect(s.getRealPosX(),s.getRealPosY(), sizeSquare, sizeSquare);
		}
		g2.fillRect(740, 280, 20, 20);
	}
	 */
}
