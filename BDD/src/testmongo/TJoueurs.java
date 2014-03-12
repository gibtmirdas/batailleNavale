/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testmongo;

import com.mongodb.BasicDBObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author antho
 */
public class TJoueurs extends AbstractTable {

	private final String tableName = "joueurs";

	public TJoueurs() {
		linkToTable = Storage.getInstance().getCollection(tableName);
	}

	/**
	 * 
	 * @param args
	 *            args[0]:Pseudo ...
	 * @return
	 */
	@Override
	public void insert(List<Object> args) {
		Integer cardID = 1, defaultScore = 0;
		BasicDBObject insertQuery = new BasicDBObject();
		List<Integer> cardList = new ArrayList<>();
		cardList.add(cardID);
		cardList.add(cardID);

		insertQuery.put("id", this.getLastID() + 1);
		insertQuery.put("pseudo", args.get(0));
		insertQuery.put("cardID", cardList);
		insertQuery.put("score", defaultScore);
		linkToTable.insert(insertQuery);
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getCardsById(int id) {

		BasicDBObject searchQuery = new BasicDBObject();
		BasicDBObject fields = new BasicDBObject().append("cardID", 1);
		searchQuery.put("id", id);
		try {
			return (List<Integer>) linkToTable.find(searchQuery, fields).next()
					.get("cardID");
		} catch (RuntimeException re) {
			System.err.println("Player " + id + " has no card /!\\");
			return null;
		}

	}

	public void addCardToPlayer(String cardName, int playerID) {
		TCartes cartes = new TCartes();
		int cardID = cartes.getIdByCriteria("nom", cardName);
		System.err.print(cardID);
		List<Integer> cardList = getCardsById(playerID);
		cardList.add(cardID);
		HashMap<String, Object> updateList = new HashMap<>();
		updateList.put("cardID", cardList);
		updateById(playerID, updateList);
	}
}
