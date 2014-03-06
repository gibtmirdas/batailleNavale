/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package v2;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	// @Override
	// public void insertDefault(List<Object> args) {
	// TCartes tcartes = new TCartes();
	// Integer cardID = 1, defaultScore = 0, defaultCredit = 0;
	// String defaultPassword = "";
	// BasicDBObject insertQuery = new BasicDBObject();
	// List<Integer> cardList = new ArrayList<>();
	// cardList.add(cardID);
	// cardList.add(cardID);
	//
	// insertQuery.put("id", this.getLastID() + 1);
	// insertQuery.put("pseudo", args.get(0));
	// insertQuery.put("cardID", cardList);
	// insertQuery.put("score", defaultScore);
	// insertQuery.put("password", defaultPassword);
	// insertQuery.put("credit", defaultCredit);
	// linkToTable.insert(insertQuery);
	// }

	/**
	 * 
	 * @param args
	 *            args[0]:Pseudo ...
	 * @return
	 */
	@Override
	public void insert(Map<String, Object> args) {
		TCartes tcartes = new TCartes();
		Integer cardID = 1;
		BasicDBObject insertQuery = new BasicDBObject();
		List<Integer> cardList = new ArrayList<>();
		cardList.add(cardID);
		cardList.add(cardID);

		insertQuery.put("id", this.getLastID() + 1);
		insertQuery.put("pseudo", args.get("pseudo"));
		insertQuery.put("cardID", args.get("cardID"));
		insertQuery.put("score", args.get("score"));
		insertQuery.put("password", args.get("password"));
		insertQuery.put("credit", args.get("credit"));
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
