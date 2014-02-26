/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testmongo.Storage.tables;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author antho
 */
public abstract  class AbstractTable {
    
    protected DBCollection linkToTable;

    public abstract void insert(List<Object> args);
    
    public DBObject getById(int ID) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("id", ID);
        DBCursor cursor = linkToTable.find(searchQuery);
        return cursor.next();
    }
    
    public int getLastID() {
        BasicDBObject lastIDQuery = new BasicDBObject("id",-1);
        try{
            int id = (int) linkToTable.find().sort(lastIDQuery).next().get("id");
            return id;
        }catch(RuntimeException re){
            return -1;
        }
    }  

    public int getIdByCriteria(String criteria, Object value) {
        /*BasicDBObject searchQuery = new BasicDBObject(criteria,value);
        DBCursor cursor = linkToTable.find(searchQuery);
        try{
            int id = (int) cursor.next().get("id");
            return id;
        }catch(RuntimeException re){
            System.out.println(re.getMessage());
            return -1;
        }*/
        try{
            return ((Double)linkToTable.find(new BasicDBObject("nom", "torpille")).next().get("id")).intValue();
        }catch(RuntimeException re){
            return -1;
        }
    }
    
    public void deleteById(int ID) {
        BasicDBObject deleteQuery = new BasicDBObject();
        deleteQuery.put("id", ID);
        linkToTable.remove(deleteQuery);
    }
    
    public void updateById(int ID, HashMap<String, Object> args) {
        BasicDBObject query = new BasicDBObject();
        query.put("id", ID);
        
        BasicDBObject updateQuery = new BasicDBObject();
        
        for(String s : args.keySet()){
            updateQuery.put(s, args.get(s));
        }
        
        BasicDBObject update = new BasicDBObject();
        update.put("$set", updateQuery);
        linkToTable.update(query, update);
    } 
    
    public void printAll(){
        DBCursor c = linkToTable.find();
        for (DBObject o : c) {
            System.err.println(o);
        }
    }
}

