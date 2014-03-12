/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbOld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mongodb.BasicDBObject;

/**
 *
 * @author antho
 */
public class TJoueurs extends AbstractTable{
	public static final String ID_FIELD    = "id",
							   NAME_FIELD  = "pseudo",
							   CARDS_FIELD = "cardID",
							   SCORE_FIELD = "score";
	public static final int DEFAULT_SCORE  = 0;
    private final String tableName = "joueurs";
    public TJoueurs() {
        linkToTable = Storage.getInstance().getCollection(tableName);
    }
    /**
     * 
     * @param args args[0]:Pseudo ...
     * @return 
     */
    @Override
    public void insert(List<Object> args) {
        Integer  cardID        = TCartes.DEFAULT_ID,
                 defaultScore  = TJoueurs.DEFAULT_SCORE;
        BasicDBObject insertQuery  = new BasicDBObject();
        List<Integer> cardList= new ArrayList<>();
        cardList.add(cardID);
        
        insertQuery.put(TJoueurs.ID_FIELD    , this.getLastID()+1 );
        insertQuery.put(TJoueurs.NAME_FIELD  , args.get(0));
        insertQuery.put(TJoueurs.CARDS_FIELD , cardList);
        insertQuery.put(TJoueurs.SCORE_FIELD , defaultScore);
        linkToTable.insert(insertQuery);    
    }    
    public List<Integer> getCardsById(int id){
        
        BasicDBObject searchQuery = new BasicDBObject();
        BasicDBObject fields      = new BasicDBObject().append(TJoueurs.CARDS_FIELD, 1);
        searchQuery.put(TJoueurs.ID_FIELD , id);
        try{
            return (List<Integer>) linkToTable.find(searchQuery, fields).next().get(TJoueurs.CARDS_FIELD); 
        }catch(RuntimeException re){
            System.err.println("Player "+id+" has no card /!\\");
            return null;
        }
    }
    public void addCardToPlayer(String cardName,int playerID){
        TCartes cartes = new TCartes();
        int cardID = cartes.getIdByCriteria(TCartes.NAME_FIELD, cardName);
        System.err.print(cardID);
        List<Integer> cardList = getCardsById(playerID);
        cardList.add(cardID);
        HashMap<String, Object> updateList = new HashMap<>();
        updateList.put(TJoueurs.CARDS_FIELD,cardList);
        updateById(playerID, updateList);
    }
}

