package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;

import lib.Tuple;

public class Canvas extends JPanel{

	private static final long serialVersionUID = 1L;
	private int side; 
	public static int sizeSquare;
	public static int currentCard;

	public Canvas(int n){
		Canvas.currentCard = 0;
		side = n;
		setPreferredSize(new Dimension(1000, 1000));
		sizeSquare = 20;
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		for(int row = 0; row< side; row++){
			for(int col = 0; col < (side/4)*3; col++){
				gbc.gridx = row;
				gbc.gridy = col;
				ElementContainer elementContain = new ElementContainer(row, col, 1, 1, Color.BLUE, true);
				add(elementContain, gbc);
			}
		}

		//    	for(int row = 0; row< side; row++){
		//    		for(int col = 0; col < side; col++){
		//    			gbc.gridx = row;
		//    			gbc.gridy = col;
		//    			if(col < ((side/4)*(3))){
		//        			ElementContainer elementContain = new ElementContainer(row, col, 1, 1, Color.GREEN, true);
		//        	    	add(elementContain, gbc);	
		//    			}else{
		//        			ElementContainer elementContain = new ElementContainer(row, col, 1, 1, Color.RED, true);
		//        	    	add(elementContain, gbc);
		//    			}
		//    		}
		//    	}
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//	    displayShips(g);
//		drawGrid(g, sizeSquare);
		//	    repaint();
	}

	private void drawGrid(Graphics g, int size){
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.black);
		for( int i = 0; i < side; i ++){
			for( int j = 0; j < (side/4)*3; j++){
				Rectangle grid = new Rectangle(i*size, j*size, size, size);
				g2d.draw(grid);             
			}
		}
		g2d.setColor(Color.blue);
		g2d.drawLine(381, 0, 381, 585);
	}

	public void displayShips(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for(ElementContainer s: ContainerGame.ShipList){		
			g2.setColor(s.getColor());
			g2.fillRect(s.getRealPosX(),s.getRealPosY(), sizeSquare, sizeSquare);
		}
		g2.fillRect(740, 280, 20, 20);
	}

	static public Tuple matchRectangle(int x, int y){
		int realY=0;
		int realX=0;
		realX =  (x-1)*sizeSquare;
		realY = (y-1)*sizeSquare;
		Tuple posReal = new Tuple(realX,realY);
		return posReal;
	}
	
	
}	

