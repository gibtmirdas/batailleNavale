/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testmongo.Storage.tables;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.List;
import testmongo.Storage.Storage;

/**
 *
 * @author antho
 */
public class TCartes extends AbstractTable{
    private final String tableName = "cartes";
    public TCartes (){
        linkToTable = Storage.getInstance().getCollection(tableName);
    }
    

    /**
     * 
     * @param args args[0]: Nom args[2]: coûts args[3]: type 
     */
    @Override
    public void insert(List<Object> args) {      
        BasicDBObject insertQuery  = new BasicDBObject();
        
        insertQuery.put("id"    , this.getLastID()+1 );
        insertQuery.put("nom"   , args.get(0));
        insertQuery.put("couts" , args.get(1));
        insertQuery.put("type"  , args.get(2));
        
        linkToTable.insert(insertQuery);    
    }


}
