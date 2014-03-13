package panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Boat extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private int idBoat;
	private int xStart;
	private int yStart;
	private int xEnd;
	private int yEnd;
	private int life;
	
	public Boat(){
//		setBackground(Color.ORANGE);

		
		addMouseListener(new MouseAdapter() {
			
			/*backgroup => on peut changer la couleur du bateau dans on appuie dessus
			C'est ici qu'on doit recuperer les coordonnées quand on bouge en bateau!
			*/
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				System.out.println("hay hay!");
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				
			}
			
		});	
	}
	
	public Boat(int idBoat, int xStart, int yStart, int xEnd, int yEnd, int life) {
		this.idBoat = idBoat;
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
		this.life = life;
//		setBackground(Color.ORANGE);
		setPreferredSize(new Dimension(xEnd-xStart, yEnd-yStart));		
		
		addMouseListener(new MouseAdapter() {
			
			/*backgroup => on peut changer la couleur du bateau dans on appuie dessus
			C'est ici qu'on doit recuperer les coordonnées quand on bouge en bateau!
			*/
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				System.out.println("hay hay!");
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				super.mouseDragged(e);
				
			}
			
		});		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("FGAHSJKLDAS");
		ImageIcon i = new ImageIcon("src/imgs/boat.png");
		i.paintIcon(this, g, xStart, yStart);
	}

	public int getIdBoat() {
		return idBoat;
	}

	public int getxStart() {
		return xStart;
	}

	public int getyStart() {
		return yStart;
	}

	public int getxEnd() {
		return xEnd;
	}

	public int getyEnd() {
		return yEnd;
	}

	public int getLife() {
		return life;
	}
}
