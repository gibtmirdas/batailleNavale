package panels;



public class Boat{
	
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
	}
	
	public int getIdBoat() {
		return idBoat;
	}

	public Boolean isDead(){
		if(life <= 0){
			return false;
		}
		return true;
	}
	
	public void impact(){
		life--;
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
