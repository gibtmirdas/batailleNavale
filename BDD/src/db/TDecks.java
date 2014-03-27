/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author antho
 */
public class TDecks extends AbstractTable {

	public static final String ID_FIELD = "id", OWNER_FIELD = "owner",
			CARDS_FIELD = "cardID";
	private final String tableName = "decks";

	public TDecks() {
		linkToTable = Storage.getInstance().getCollection(tableName);
	}

	@Override
	public void insert(Map<String, Object> args) {
		boolean valid = false;
		assert args.containsKey(ID_FIELD) && args.containsKey(OWNER_FIELD)
				&& args.containsKey(CARDS_FIELD);
		assert args.get(CARDS_FIELD) instanceof List;
		
                BasicDBObject insertQuery = new BasicDBObject();
		insertQuery.put(ID_FIELD, args.get(ID_FIELD));
		insertQuery.put(OWNER_FIELD, args.get(OWNER_FIELD));
		insertQuery.put(CARDS_FIELD, args.get(CARDS_FIELD));
		try {
			linkToTable.insert(insertQuery);
			valid = true;
		} catch (MongoException me) {
		} finally {
			assert valid;
		}
	}
        

        
}
