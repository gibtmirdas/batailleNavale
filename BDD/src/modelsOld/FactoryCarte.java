package modelsOld;
import com.mongodb.DBObject;

import dbOld.TCartes;


public class FactoryCarte{
		
	public FactoryCarte() {
		
	}
	
	public Carte getCarte(DBObject obj) throws ClassNotFoundException{
		
		switch ((String)(obj.get(TCartes.TYPE_FIELD).toString())) {
			case Carte.TYPE_MOVE:
				return new CarteDeplacement(obj);
			case Carte.TYPE_RADAR:
				return new CarteRadar(obj);
			case Carte.TYPE_ATTACK:
				return new CarteAttaque(obj);
			default:
				throw new ClassNotFoundException("Class not found");
			}
		}
}
