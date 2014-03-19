package lib;

public class Tuple { 
	  public int x; 
	  public int y; 
	  public Tuple(int x, int y) { 
		  this.x = x; 
		  this.y = y; 
	  }
	  public void ChangeData(int x, int y){
		  this.x = x; 
		  this.y = y; 
	  }
	  public String print(){
		  return "X: "+x+" Y: "+y;
	  }
}