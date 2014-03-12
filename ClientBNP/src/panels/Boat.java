package panels;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.prefs.BackingStoreException;

import javax.swing.JPanel;

public class Boat extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private int idBoat;
	private int xStart;
	private int yStart;
	private int xEnd;
	private int yEnd;
	private int life;
	
	public Boat(int idBoat, int xStart, int yStart, int xEnd, int yEnd, int life) {
		this.idBoat = idBoat;
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
		this.life = life;
		setBackground(Color.ORANGE);
		addMouseListener(new MouseAdapter() {
			
			/*backgroup => on peut changer la couleur du bateau dans on appuie dessus
			C'est ici qu'on doit recuperer les coordonnées quand on bouge en bateau!
			*/
			@Override
			public void mousePressed(MouseEvent e) {
				
				super.mousePressed(e);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {

				super.mouseReleased(e);
			}
			
		});
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
