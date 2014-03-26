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

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import lib.Tuple;


public class Canvas extends JPanel   {

	private static final long serialVersionUID = 1L;
	private final int widthCanvas;
	private final int heightCanvas;
	private final Tuple caseDim;
	private final int nbCasesX = 40;
	private final int nbCasesY = 30;
	private ArrayList<ArrayList<Case>> DataCases;
	public static ArrayList<Tuple> listeMissiles ;
	private final int side;
	public static int cardIdSelected=-1;
	private ImageIcon iconMissile;
	private ArrayList<Tuple> attackMissiles;
	private ArrayList<Boat> boats;

	
	public Canvas(int widthCanvas, int heightCanvas, int side){
		listeMissiles = new ArrayList<Tuple>();
		attackMissiles = new ArrayList<Tuple>();
		boats = new ArrayList<Boat>();		
		iconMissile = new ImageIcon(getClass().getResource("/images/croix.jpg"));
		this.side = side;
		this.widthCanvas = widthCanvas;
		this.heightCanvas = heightCanvas;
		
		//Calculating the dimension of the "Case" 
		this.caseDim = computeCaseSize(widthCanvas, heightCanvas, nbCasesX, nbCasesY);
		
		//DataCases contain every case object of the canvas
		this.DataCases = new ArrayList<ArrayList<Case>>();
		for(int i = 0; i< widthCanvas/caseDim.x ; i++){
			DataCases.add(new ArrayList<Case>());
			for(int j = 0; j<heightCanvas/caseDim.y; j++){
				DataCases.get(i).add(new Case());
			}
		}
		
		//Adding the mouseClick listenner to the JPANEL and linkin it to a function
		addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e)
    		{    			
    			//Uncomment This to test the first method:
    			/*
    			Tuple DebutPos = new Tuple(2,3);
    			Tuple FinPos = new Tuple(4,5);
    			Fill(DebutPos,FinPos);
    			*/
    			if(cardIdSelected!=-1){
    				if(!isRightSide(getFakePos(e.getX(), e.getY()).x)){
    					listeMissiles.add(getFakePos(e.getX(), e.getY()));
    					cardIdSelected = -1;
    					repaint();
    				}
    			}
    			if(isRightSide(getFakePos(e.getX(), e.getY()).x))
    				Fill(e.getX(), e.getY(),(e.getX()+1*caseDim.x),(e.getY()+1*caseDim.y));

    		}
    	});
	}
	
	//This function is made to make a copy of the current data by VALUE not by Reference
	private ArrayList<ArrayList<Case>> getCurrentData(){
		ArrayList<ArrayList<Case>> d = new ArrayList<ArrayList<Case>>();
		for(int i=0; i<DataCases.size(); i++){
			d.add(new ArrayList<Case>());
			for(int j = 0; j<DataCases.get(0).size();j++){
				d.get(i).add(new Case(DataCases.get(i).get(j).state()));
			}
		}
		return d;
	}
	
	public void Fill(int x, int y, int ex, int ey){
		if(side == 1){
			if(ex > widthCanvas/2)
				return;
		}
		else if(side == 2){
			if(ex < (widthCanvas/2)+caseDim.x)
				return;
		}
		Tuple pos = getFakePos(x, y);
		Tuple edgePos = getFakePos(ex,ey);
		Fill(pos,edgePos);	
	}

	public void Fill(Tuple fakeBeginPos, Tuple fakeEndPos){
		//Handling Edge of Array Exceptions
		System.out.println("A A A A A ");
		try{DataCases.get(fakeBeginPos.x).get(fakeEndPos.y).isEmpty();
			DataCases.get(fakeEndPos.x).get(fakeEndPos.y).isEmpty();}
		catch(java.lang.IndexOutOfBoundsException e){return;}
		
		//Lets Create a copy of the current Data
		//ArrayList<ArrayList<Case>> DataCasesCopy = new ArrayList<ArrayList<Case>>();
		//DataCasesCopy=getCurrentData();
		
		for(int i = fakeBeginPos.x; i<=fakeEndPos.x; i++){
			for(int j = fakeBeginPos.y; j<=fakeEndPos.y; j++){
				if(DataCases.get(i).get(j).isEmpty())
					continue;
					//DataCases.get(i).get(j).remplir();
				//if one of the cases we're trynna fill is not empty.. just fall back into the old copy
				else{
					//DataCases=DataCasesCopy;
					//repaint();
					return;
				}
			}
		}
		boats.add(new Boat(0, fakeBeginPos.x, fakeBeginPos.y, fakeEndPos.x, fakeEndPos.y, 3));

		updateDataCases();
	}
	
	private void updateDataCases(){
		emptyDataCase();
		int index = 0;
		
		for(Boat b: boats){
			Tuple posStart = new Tuple(b.getxStart(),b.getyStart());
			Tuple posEnd   = new Tuple(b.getxEnd(),b.getyEnd());
			for(int i = posStart.x; i<= posEnd.x;i++){
				for(int j = posStart.y; j<= posEnd.y;j++){
					DataCases.get(i).get(j).remplir();
					DataCases.get(i).get(j).setIndex(index);
				}
			}
			index++;
		}
		repaint();
	}
	
	private void emptyDataCase(){
		for (int i = 0; i < DataCases.size(); i++) {
			for (int j = 0; j < DataCases.get(0).size(); j++) {
				DataCases.get(i).get(j).vider();
			}
		}
	}
	
	public void attackMissile(int posX, int posY){
		Tuple posMissile = new Tuple(posX, posY);
		attackMissiles.add(posMissile);
		checkCollision(posMissile);
		repaint();
	}
	
	private void checkCollision(Tuple posMissile){
		if(!DataCases.get(posMissile.x).get(posMissile.y).isEmpty()){
			int index = DataCases.get(posMissile.x).get(posMissile.y).getIndex();
			Boat b = boats.get(index);
			b.impact();
			if(b.isDead()){
				boats.remove(index);
			}
		}
		updateDataCases();
	}
	
	private Tuple computeCaseSize(int width, int height, int nbX, int nbY){
    	int sx = width/nbCasesX;
    	int sy = height/nbCasesY;
    	return new Tuple(sx,sy);
    }
	
	private Tuple getFakePos(int x,int y){
		int xr = x/caseDim.x;
		int yr = y/caseDim.y;
		return new Tuple(xr,yr);
	}
	
	private boolean isRightSide(int i){
		if(side == 1){
			if(i < nbCasesX/2)
				return true;
			else
				return false;
		}else{
			if(i >= nbCasesX/2)
				return true;
			else
				return false;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
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
				//Si je suis dans la bonne partie dessin des bateaux
				if(isRightSide(i)){
					if(!DataCases.get(i).get(j).isEmpty()){						
						g2d.fillRect(i*caseDim.x, j*caseDim.y, caseDim.x, caseDim.y);
					}
				}else{ //Sinon dessin grisé
					g2d.setColor(Color.GRAY);
					g2d.fillRect(i*caseDim.x, j*caseDim.y, caseDim.x, caseDim.y);
					g2d.setColor(Color.BLACK);
				}
			}
		}
		
		//Drawing Missiles 
		for(int i=0; i<listeMissiles.size(); i++){
//			g2d.setColor(Color.RED);
//			g2d.fillRect(ListeMissiles.get(i).x*caseDim.x, ListeMissiles.get(i).y*caseDim.y, caseDim.x, caseDim.y);
			iconMissile.paintIcon(this, g2d, listeMissiles.get(i).x*caseDim.x, listeMissiles.get(i).y*caseDim.y);
		}
		
		
		//Drawing attackMissiles
		for (int i = 0; i < attackMissiles.size(); i++) {
			g2d.setColor(Color.GREEN);
			g2d.fillRect(attackMissiles.get(i).x*caseDim.x, attackMissiles.get(i).y*caseDim.y, caseDim.x, caseDim.y);
		}
		
		
		//Drawing the middle Stroke
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawLine(widthCanvas/2, 0, widthCanvas/2, heightCanvas);
		
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