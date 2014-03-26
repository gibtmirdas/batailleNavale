/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mongodb.DBObject;

import db.TDecks;
import db.TJoueurs;

import java.util.ArrayList;

/**
 * 
 * @author antho
 */
public class Deck {

	private final ArrayList<Carte> cardID;
	private Integer deckID;
	private final Joueur owner;

	@SuppressWarnings("unchecked")
	public Deck(int id, boolean deckid) {
		TDecks td = new TDecks();
		DBObject dck;
		TJoueurs tj = new TJoueurs();
		if (deckid) {
			dck = td.getById(id);
			owner = new Joueur(tj.getById((int) dck.get(TDecks.OWNER_FIELD)));
		} else {
			owner = new Joueur(tj.getById(id));
			dck = td.getById(td.getIdByCriteria(TDecks.OWNER_FIELD,
					owner.getId()));
		}
		cardID = (ArrayList<Carte>) dck.get(TDecks.CARDS_FIELD);
		this.deckID = (Integer) dck.get(TDecks.ID_FIELD);
	}

	public ArrayList<Carte> getCardID() {
		return cardID;
	}

	public boolean exist(Carte c) {
		assert c.getId() >= 0;
		for (Carte cur : cardID)
			if (c.getId() == cur.getId())
				return true;
		return false;
	}

	public void addCard(Carte c) {
		assert !exist(c);
		cardID.add(c);
	}

	public void removeCard(Carte c) {
		assert exist(c);
		cardID.remove(c);
	}

	public Joueur getOwner() {
		return owner;
	}

	public Integer getDeckID() {
		return deckID;
	}

	public void setDeckID(Integer deckID) {
		this.deckID = deckID;
	}
}
