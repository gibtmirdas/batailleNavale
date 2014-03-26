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
public class TDecks extends AbstractTable {

    public static final String ID_FIELD = "id", 
                               OWNER_FIELD = "owner",
                               CARDS_FIELD = "cardID";
    private final String tableName = "decks";

    public TDecks() {
        linkToTable = Storage.getInstance().getCollection(tableName);
    }

    @Override
    public void insert(Map<String, Object> args) {
        if(args.containsKey(ID_FIELD)){
            if(args.containsKey(OWNER_FIELD)){
                if(args.containsKey(CARDS_FIELD)){
                    BasicDBObject insertQuery = new BasicDBObject();
                    insertQuery.put(ID_FIELD, args.get(ID_FIELD));
                    insertQuery.put(OWNER_FIELD,args.get(OWNER_FIELD));
                    insertQuery.put(CARDS_FIELD, args.get(CARDS_FIELD));
                    linkToTable.insert(insertQuery);
                }
            }
        }   
    }

}
