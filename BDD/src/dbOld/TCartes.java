/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbOld;

import java.util.List;

import com.mongodb.BasicDBObject;

/**
 *
 * @author antho
 */
public class TCartes extends AbstractTable{
    public static final String ID_FIELD      = "id",
                                NAME_FIELD    = "name",
                                COST_FIELD    = "cost",
                                TYPE_FIELD    = "type",
                                AREA_FIELD    = "area",
                                DAMAGES_FIELD = "damages";
    public static final int DEFAULT_ID    = 0,
    		                DEFAULT_COST  = 0,
    		                DEFAULT_AREA  = 1,
    		                DEFAULT_DMG   = 1;
    private final String tableName = "cartes";
    public TCartes (){
        linkToTable = Storage.getInstance().getCollection(tableName);
    }
    

    /**
     * add card to database
     * @param args[0] Nom
     * @param args[1] Coûts
     * @param args[2] Type
     * @param args[3] Zone d'Action
     * @param args[4] Dommage
     * @return void 
     */
    @Override
    public void insert(List<Object> args) {      
        BasicDBObject insertQuery  = new BasicDBObject();
        
        insertQuery.put(TCartes.ID_FIELD    , this.getLastID()+1 );
        insertQuery.put(TCartes.NAME_FIELD   , args.get(0));
        insertQuery.put(TCartes.COST_FIELD , args.get(1));
        insertQuery.put(TCartes.TYPE_FIELD  , args.get(2));
        insertQuery.put(TCartes.AREA_FIELD  , args.get(3));
        insertQuery.put(TCartes.DAMAGES_FIELD  , args.get(4));
        
        linkToTable.insert(insertQuery);    
    }


}