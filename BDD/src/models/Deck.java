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

    public Deck(int id, boolean deckid) {
        TDecks td = new TDecks();
        DBObject dck;
        TJoueurs tj = new TJoueurs();
        if (deckid) {
            dck = td.getById(id);
            owner = new Joueur(tj.getById((int) dck.get(TDecks.OWNER_FIELD)));
        } else {
            owner = new Joueur(tj.getById(id));
            dck = td.getById(td.getIdByCriteria(TDecks.OWNER_FIELD, owner.getId()));
        }
        cardID = (ArrayList<Carte>) dck.get(TDecks.CARDS_FIELD);
        this.deckID = (Integer) dck.get(TDecks.ID_FIELD);
    }

    public ArrayList<Carte> getCardID() {
        return cardID;
    }

    public void addCard(Carte c) {
        cardID.add(c);
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
