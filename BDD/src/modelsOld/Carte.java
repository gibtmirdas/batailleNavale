package modelsOld;

import com.mongodb.DBObject;

import dbOld.TCartes;

public abstract class Carte {
	public static final String TYPE_ATTACK = "Attaque",
							   TYPE_MOVE   = "Deplacement",
							   TYPE_RADAR  = "Radar";
	protected int id;
	protected String name;
	protected int cost;
	protected String type;
	protected int area;
	protected int damages;
	protected DBObject link;
	
	public Carte(DBObject obj) {
		link = obj;
		id = (int)link.get(TCartes.ID_FIELD);
		name = (String)link.get(TCartes.NAME_FIELD);
		cost = (int)link.get(TCartes.COST_FIELD);
		type = (String)link.get(TCartes.TYPE_FIELD);
		area = (int)link.get(TCartes.AREA_FIELD);
		damages = (int)link.get(TCartes.DAMAGES_FIELD);
	}
	
	public Carte(int id){
		TCartes c = new TCartes();
		link = c.getById(id);
		this.id = id;
		name = (String)link.get(TCartes.NAME_FIELD);
		cost = (int)link.get(TCartes.COST_FIELD);
		type = (String)link.get(TCartes.TYPE_FIELD);
		area = (int)link.get(TCartes.AREA_FIELD);
		damages = (int)link.get(TCartes.DAMAGES_FIELD);
	}
	
	public abstract void afficherCarte();
}
