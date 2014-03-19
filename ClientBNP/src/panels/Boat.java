package panels;

import javax.swing.ImageIcon;


public class Boat{
	
	private int idBoat;
	private int xStart;
	private int yStart;
	private int xEnd;
	private int yEnd;
	private int life;
	private ImageIcon icon;
	
	public Boat(int idBoat, int xStart, int yStart, int xEnd, int yEnd, int life) {
		this.idBoat = idBoat;
		this.xStart = xStart;
		this.yStart = yStart;
		this.xEnd = xEnd;
		this.yEnd = yEnd;
		this.life = life;
		icon = new ImageIcon("src/imgs/boat.png");
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

	public ImageIcon getIcon() {
		return icon;
	}
}
