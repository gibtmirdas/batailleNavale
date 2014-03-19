package panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import lib.Tuple;


public class Canvas extends JPanel   {

	private static final long serialVersionUID = 1L;
	private final int widthCanvas;
	private final int heightCanvas;
	private final Tuple caseDim;
	private ArrayList<ArrayList<Case>> DataCases;
	private ArrayList<Boat> boats;
	private ArrayList<Carte> cartes;
	
	public Canvas(int widthCanvas, int heightCanvas){
		this.widthCanvas = widthCanvas;
		this.heightCanvas = heightCanvas;
		boats = new ArrayList<Boat>();
		
		//Calculating the dimension of the "Case" 
		this.caseDim = computeCaseSize(widthCanvas, heightCanvas);
		
		//DataCases contain every case object of the canvas
		this.DataCases = new ArrayList<ArrayList<Case>>();
		for(int i = 0; i<widthCanvas/caseDim.x ; i++){
			DataCases.add(new ArrayList<Case>());
			for(int j = 0; j<heightCanvas/caseDim.y; j++){
				DataCases.get(i).add(new Case());
			}
		}
		
		//Adding the mouseClick listenner to the JPANEL and linkin it to a function
		addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e)
    		{
    			System.out.println(DataCases.size());
    			System.out.println("e.getX(): "+e.getX());
    			System.out.println("e.getY(): "+e.getY());
    			Fill(e.getX(), e.getY(), 1, 3);
    		}
    	});
	}
	
	public void Fill(int x, int y, int sx, int sy){
		Tuple pos = getFakePos(x, y);
		System.out.println("FAGHJKKSAHGFGDHAJSKH");
		//Handling Edge of Array Exceptions
		try{
			DataCases.get(pos.x+sx-1).get(pos.y+sy-1).isEmpty();
		}catch(java.lang.IndexOutOfBoundsException e){
			return;
		}
				
		//Lets Create a copy of the current Data
		ArrayList<ArrayList<Case>> DataCasesCopy; 
		DataCasesCopy = DataCases;

		for(int i = pos.x; i<pos.x+sx; i++){
			for(int j = pos.y; j<pos.y+sy; j++){
				System.out.println(i+" "+j);
				if(DataCases.get(i).get(j).isEmpty())
					DataCases.get(i).get(j).remplir();
				//if one of the cases we're trinna fill is not empty.. just fall back into the old copy
				else{
					DataCases=DataCasesCopy;
					return;
				}
			}
		}
		repaint();
	}
	
//	public void addBoat(Boat boat){
//		boats.add(boat);
//	}
//	
//	public void addCarte(Carte carte){
//		cartes.add(carte);
//	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		System.out.println("Dessin des rectangles");
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLACK);
		
		//Drawing The Grid
		for( int i = 0; i < widthCanvas; i ++){
			for( int j = 0; j < heightCanvas; j++){
				Rectangle rect = new Rectangle(i*caseDim.x, j*caseDim.y, caseDim.x, caseDim.y);
				g2d.draw(rect);
			}
		}
		
		//Drawing The Boats
		for(int i=0; i<DataCases.size(); i++){
			for(int j = 0; j<DataCases.get(0).size();j++){
				if(!DataCases.get(i).get(j).isEmpty())
					g2d.fillRect(i*caseDim.x, j*caseDim.y, caseDim.x, caseDim.y);
			}
		}
		
		
		//Drawing the middle Stroke
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawLine(widthCanvas/2, 0, widthCanvas/2, heightCanvas);
		
	}
	
	private Tuple computeCaseSize(int width, int height){
    	int sx = width/40;
    	int sy = height/30;
    	return new Tuple(sx,sy);
    }
	
	private Tuple getFakePos(int x,int y){
		int xr = x/caseDim.x;
		int yr = y/caseDim.y;
		return new Tuple(xr,yr);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(widthCanvas, heightCanvas);
	}
	
	@Override
	public Dimension getMaximumSize() {
		return new Dimension(widthCanvas, heightCanvas);
	}
	
}
