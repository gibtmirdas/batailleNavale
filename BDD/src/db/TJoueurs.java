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
import models.FactoryCarte;

/**
 * 
 * @author antho
 */
public class TJoueurs extends AbstractTable {

	public static final String ID_FIELD = "id", NAME_FIELD = "pseudo",
			CARDS_FIELD = "cardID", SCORE_FIELD = "score",
			PASSWORD_FIELD = "password", CREDIT_FIELD = "credit";
	public static final int DEFAULT_SCORE = 0, DEFAULT_CARDS = 0,
			DEFAULT_CREDIT = 0;
	public static final String DEFAULT_NAME = "username",
			DEFAULT_PASSWORD = "password";

	private final String tableName = "joueurs";

	public TJoueurs() {
		linkToTable = Storage.getInstance().getCollection(tableName);
	}

	public boolean existJoueur(String username) {
		TJoueurs tj = new TJoueurs();

		int j = tj.getIdByCriteria(TJoueurs.NAME_FIELD, username);

		if (j == -1)
			return false;
		return true;
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
		BasicDBObject insertQuery = new BasicDBObject();

		insertQuery.put("id", this.getLastID() + 1);

		if (args.containsKey(NAME_FIELD))
			insertQuery.put(NAME_FIELD, args.get(NAME_FIELD));
		else
			insertQuery.put(NAME_FIELD, DEFAULT_NAME);

		if (args.containsKey(CARDS_FIELD)
				&& args.get(CARDS_FIELD) instanceof List)
			insertQuery.put(CARDS_FIELD, args.get(CARDS_FIELD));
		else
			insertQuery.put(CARDS_FIELD, DEFAULT_CARDS);

		if (args.containsKey(SCORE_FIELD))
			insertQuery.put(SCORE_FIELD, args.get(SCORE_FIELD));
		else
			insertQuery.put(SCORE_FIELD, DEFAULT_SCORE);

		if (args.containsKey(PASSWORD_FIELD))
			insertQuery.put(PASSWORD_FIELD, args.get(PASSWORD_FIELD));
		else
			insertQuery.put(PASSWORD_FIELD, DEFAULT_PASSWORD);

		if (args.containsKey(CREDIT_FIELD))
			insertQuery.put(CREDIT_FIELD, args.get(CREDIT_FIELD));
		else
			insertQuery.put(CREDIT_FIELD, DEFAULT_CREDIT);

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
			try {
				cTmp = FactoryCarte.getCarte(getById(i));
				cartes.add(cTmp);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
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
}
