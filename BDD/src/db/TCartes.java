/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import com.mongodb.BasicDBObject;
import java.util.Map;

/**
 * 
 * @author antho
 */
public class TCartes extends AbstractTable {
	public static final String ID_FIELD = "id", NAME_FIELD = "name",
			COST_FIELD = "cost", TYPE_FIELD = "type", AREA_FIELD = "area",
			DAMAGES_FIELD = "damages";
	public static final int DEFAULT_ID = 0, DEFAULT_COST = 0, DEFAULT_AREA = 1,
			DEFAULT_DMG = 1;

	private final String tableName = "cartes";

	public TCartes() {
		linkToTable = Storage.getInstance().getCollection(tableName);
	}

	/**
	 * Insert a new card in the database
	 * 
	 * @param args
	 *            args[0]: Nom args[2]: coûts args[3]: type
	 */
	@Override
	public void insert(Map<String, Object> args) {
		BasicDBObject insertQuery = new BasicDBObject();

		insertQuery.put(TCartes.ID_FIELD, this.getLastID() + 1);
		insertQuery.put(TCartes.NAME_FIELD, args.get(NAME_FIELD));
		insertQuery.put(TCartes.COST_FIELD, args.get(COST_FIELD));
		insertQuery.put(TCartes.TYPE_FIELD, args.get(TYPE_FIELD));
		insertQuery.put(TCartes.AREA_FIELD, args.get(AREA_FIELD));
		insertQuery.put(TCartes.DAMAGES_FIELD, args.get(DAMAGES_FIELD));

		linkToTable.insert(insertQuery);
	}

}
