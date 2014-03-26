package window;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import lib.Tuple;
import panels.Canvas;
import panels.ContainerCartes;
import connection.ClientConnection;

public class FrameMain extends JPanel{
	

	private static final long serialVersionUID = 1L;
	private Canvas canvas;
	private ContainerCartes containCarte;
	private JButton button;
	private static int side;
	private JPanel panelButton;
	
	public static final String title = "BATAILLE NAVALE++";
	public static final int canvasWidth = 600, canvasHeight = 600; //Canvas + cartes container compris
//	public static final int canvasWidth = 800, canvasHeight = 625;

	
	public FrameMain(int widthFrame, int heightFrame, int side){		
		this.side = side;
    	setLayout(new BorderLayout());
    	canvas = new Canvas(canvasHeight,canvasHeight-100, side); //On enleve la partie des cartes au niveau de l'hauteur
    	JPanel wrapperCanvas = new JPanel(new FlowLayout());
    	wrapperCanvas.add(canvas);  
    	add(wrapperCanvas,BorderLayout.NORTH);
   
    	button = new JButton("Passer tour");
    	panelButton = new JPanel(new FlowLayout());
    	panelButton.add(button);
    	add(panelButton, BorderLayout.SOUTH);
    	   	
    	button.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mousePressed(MouseEvent e) {
    			for( Tuple missilePos : Canvas.listeMissiles)
    				ClientConnection.controllerClient.sendMissile(FrameMain.side, missilePos.x,missilePos.y);		
    			Canvas.listeMissiles.clear();
    			super.mousePressed(e);
    		}
		});
    	
    	containCarte = new ContainerCartes();
    	JPanel wrapperCards = new JPanel(new FlowLayout());
    	wrapperCards.add(containCarte);
    	add(wrapperCards,BorderLayout.CENTER);
    	containCarte.repaint();
    }
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(canvasWidth, canvasHeight);
	}
	
	@Override
	public Dimension getMaximumSize() {
		return new Dimension(canvasWidth, canvasHeight);
	}
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(canvasWidth, canvasHeight);
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public ContainerCartes getContainCarte() {
		return containCarte;
	}
}