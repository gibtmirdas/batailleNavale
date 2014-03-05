/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testmongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author antho
 */
public class TCartes extends AbstractTable {
	private static final String ID_FIELD = "id", NAME_FIELD = "name",
			COST_FIELD = "cost", TYPE_FIELD = "type", AREA_FIELD = "area",
			DAMAGES_FIELD = "damages";
	private final String tableName = "cartes";

	public TCartes() {
		linkToTable = Storage.getInstance().getCollection(tableName);
	}

	/**
	 * 
	 * @param args
	 *            args[0]: Nom args[2]: coûts args[3]: type
	 */
	@Override
	public void insert(List<Object> args) {
		BasicDBObject insertQuery = new BasicDBObject();

		insertQuery.put(TCartes.ID_FIELD, this.getLastID() + 1);
		insertQuery.put(TCartes.NAME_FIELD, args.get(0));
		insertQuery.put(TCartes.COST_FIELD, args.get(1));
		insertQuery.put(TCartes.TYPE_FIELD, args.get(2));
		insertQuery.put(TCartes.AREA_FIELD, args.get(3));
		insertQuery.put(TCartes.DAMAGES_FIELD, args.get(4));

		linkToTable.insert(insertQuery);
	}

}
