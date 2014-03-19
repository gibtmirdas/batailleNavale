package panels;


public class Case {
	private boolean plein;
	public Case(){
		plein = false;
	}
	public Case(boolean s){
		plein = s;
	}
	
	public void remplir(){
		plein = true;
	}
	public boolean state(){
		return plein;
	}
	public void vider(){
		plein = false;
	}
	public boolean isEmpty() {
		return !plein;
	}
	public void print(){
		if(plein)
			System.out.println("plein");
		else
			System.out.println("vide");
	}
}
