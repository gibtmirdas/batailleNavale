package panels;


public class Case {
	
	private boolean plein;
	
	public Case(){
		plein = false;
	}
	public void remplir(){
		plein = true;
	}
	public void vider(){
		plein = false;
	}
	public boolean isEmpty() {
		return !plein;
	}
}
