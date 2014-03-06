package v2;

import com.mongodb.DBObject;

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
