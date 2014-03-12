package models;

import com.mongodb.DBObject;

import dbOld.TCartes;

/**
 * Card object for database
 * 
 * @author thomas
 * 
 */
public abstract class Carte {
	public static final String TYPE_ATTACK = "Attaque",
			TYPE_MOVE = "Deplacement", TYPE_RADAR = "Radar";

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

	public Carte(int id) {
		DBObject o;
		TCartes c = new TCartes();
		o = c.getById(id);
		this.id = id;
		name = (String) o.get(TCartes.NAME_FIELD);
		cost = (int) o.get(TCartes.COST_FIELD);
		type = (String) o.get(TCartes.TYPE_FIELD);
		area = (int) o.get(TCartes.AREA_FIELD);
		damages = (int) o.get(TCartes.DAMAGES_FIELD);
	}

	public static Carte createCard(DBObject o) {
		switch ((String) o.get(TCartes.TYPE_FIELD)) {
		case Carte.TYPE_ATTACK:
			return new CarteAttaque(o);
		case Carte.TYPE_MOVE:
			return new CarteDeplacement(o);
		case Carte.TYPE_RADAR:
			return new CarteRadar(o);
		}
		System.out.println((String) o.get(TCartes.TYPE_FIELD));
		return null;
	}

	public abstract void afficherCarte();

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
