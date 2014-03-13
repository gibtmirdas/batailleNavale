package panels;

import javax.swing.JPanel;
import javax.swing.OverlayLayout;

public class Canvas extends JPanel{

	private static final long serialVersionUID = 1L;
	private int canvaLenght;
	private int sizeSquare = 20;
	private GridDraw gridDraw;
	private GridPanel gridPanel;
	

	public Canvas(int canvasLength){
		this.canvaLenght = canvasLength;
		gridDraw = new GridDraw(canvasLength, sizeSquare);
		gridPanel = new GridPanel(canvasLength, sizeSquare);
		setLayout(new OverlayLayout(this));
		add(gridPanel);
		add(gridDraw);
	}


	

//	static public Tuple matchRectangle(int x, int y){
//		int realY = 0;
//		int realX = 0;
//		realX =  (x-1)*sizeSquare;
//		realY = (y-1)*sizeSquare;
//		Tuple posReal = new Tuple(realX,realY);
//		return posReal;
//	}

}	

