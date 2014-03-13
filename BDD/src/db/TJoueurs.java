/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import com.mongodb.BasicDBObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Carte;
import models.Joueur;

/**
 * 
 * @author antho
 */
public class TJoueurs extends AbstractTable {

	public static final String ID_FIELD = "id", NAME_FIELD = "pseudo",
			CARDS_FIELD = "cardID", SCORE_FIELD = "score";
	public static final int DEFAULT_SCORE = 0;
	private final String tableName = "joueurs";

	public TJoueurs() {
		linkToTable = Storage.getInstance().getCollection(tableName);
	}

	/**
	 * Insert a new player in the database
	 * 
	 * @param args
	 *            args[0]:Pseudo ...
	 * @return
	 */
	@Override
	public void insert(Map<String, Object> args) {
		Integer cardID = this.getLastID() + 1;
		BasicDBObject insertQuery = new BasicDBObject();
		List<Integer> cardList = new ArrayList<>();
		cardList.add(cardID);

		insertQuery.put("id", this.getLastID() + 1);
		insertQuery.put("pseudo", args.get("pseudo"));
		insertQuery.put("cardID", cardID);
		insertQuery.put("score", args.get("score"));
		insertQuery.put("password", args.get("password"));
		insertQuery.put("credit", args.get("credit"));
		linkToTable.insert(insertQuery);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
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

	/**
	 * Get cards list from list id
	 * 
	 * @param id
	 * @return
	 */
	public List<Carte> getCardsListById(int id) {
		List<Integer> cardsId = getCardsById(id);
		Carte cTmp;
		List<Carte> cartes = new ArrayList<Carte>();
		for (Integer i : cardsId) {
			cTmp = Carte.createCard(getById(i));
			cartes.add(cTmp);
		}
		return cartes;
	}

	/**
	 * Add a card to the player's card list
	 * 
	 * @param cardName
	 * @param playerID
	 */
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
        
	public boolean canLogin(String username, String password) {
		Joueur jDB = new Joueur(getById(getIdByCriteria("username", username)));
		return jDB.getPassword().equals(password);
	}
        
	public boolean canBuyCard(Joueur j, Carte c) {
		return j.getCredit() >= c.getCost();
	}
} 
    