package models;

import com.mongodb.DBObject;

import db.TJoueurs;

/**
 * Player object for database
 * 
 * @author JizzTeam
 * 
 */
public class Joueur {

	private String pseudo, password;
	private int cardId, score, credit, id;

	public Joueur(String pseudo, String password, int cardId, int score,
			int credit, int id) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.password = password;
		this.cardId = cardId;
		this.score = score;
		this.credit = credit;
	}
	
	public Joueur(DBObject o) {
		id = (int) o.get("id");
		pseudo = (String) o.get("pseudo");
		password = (String) o.get("password");
		cardId = (int) o.get("cardID");
		score = (int) o.get("score");
		credit = (int) o.get("credit");
	}
	
	
	public boolean canBuyCard(Carte c) {
		return this.getCredit() >= c.getCost();
	}
	
	public static boolean canLogin(String username, String password) {
		TJoueurs tj = new TJoueurs();
		return tj.getById(tj.getIdByCriteria("username", username)).get(TJoueurs.PASSWORD_FIELD).equals(password);
	}
	
	
	/**
	 * Return a player based on the username, if password is correct. Otherwise,
	 * return null
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	
	
	public static Joueur getJoueur(String username, String password) {
		TJoueurs tj = new TJoueurs();
		
		if(tj.existJoueur(username))
			if(canLogin(username, password)){		
				Joueur j = new Joueur(tj.getById(tj.getIdByCriteria(TJoueurs.NAME_FIELD,username)));	
				return j;
			}
		
		return null;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
