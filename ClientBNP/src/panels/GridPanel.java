package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

public class GridPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int canvasLength;
	private int sizeSquare;
	
	public GridPanel(int canL, int sizeL) {
		canvasLength=canL;
		sizeSquare=sizeL;
		
		GridBagConstraints gbc = new GridBagConstraints();
		for(int row = 0; row< canvasLength; row++){
			for(int col = 0; col < (canvasLength/4)*3; col++){
				gbc.gridx = row;
				gbc.gridy = col;
				ElementContainer elementContain = new ElementContainer(row, col, sizeSquare, sizeSquare, Color.BLUE, true);
				add(elementContain, gbc);
			}
		}
		setAlignmentX(0.0f);
		setAlignmentY(0.0f);
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
//		return new Dimension(canvasLength*20, (canvasLength/4)*3*20);
		return new Dimension(1000, 1000);
	}

}
