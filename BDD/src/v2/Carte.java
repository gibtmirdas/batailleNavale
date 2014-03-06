package v2;

import com.mongodb.DBObject;

public class Carte {

	private int id, cost, damages, area;
	private String name, type;

	public Carte(int id, int cost, int damages, int area, String name,
			String type) {
		super();
		this.id = id;
		this.cost = cost;
		this.damages = damages;
		this.area = area;
		this.name = name;
		this.type = type;
	}

	public Carte(DBObject o) {
		name = (String) o.get("name");
		type = (String) o.get("type");
		id = (int) o.get("id");
		cost = (int) o.get("cost");
		damages = (int) o.get("damages");
		area = (int) o.get("area");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getDamages() {
		return damages;
	}

	public void setDamages(int damages) {
		this.damages = damages;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
