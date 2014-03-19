package panels;

public class Carte{

	private int id ;
	private String type;
	
	public Carte(int id, String type){
		this.type = type;
		this.id   =  id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
