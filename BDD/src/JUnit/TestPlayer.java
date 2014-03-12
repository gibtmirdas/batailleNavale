package JUnit;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import models.*;

import org.junit.Test;

import com.mongodb.DBObject;

import db.TCartes;
import db.TJoueurs;

public class TestPlayer {

	@Test
	public void testCreateTJoueur() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pseudo", "test");
		map.put("password", "aaaaaa");
		map.put("cardID", 1);
		map.put("score", 40);
		map.put("credit", 100);

		TJoueurs p1 = new TJoueurs();
		p1.insert(map);

		TJoueurs p2 = new TJoueurs();
		DBObject o = p2.getById(p2.getIdByCriteria("pseudo", "test"));
		Joueur j = new Joueur(o);

		assertEquals("test", j.getPseudo());
		assertEquals("aaaaaa", j.getPassword());
		assertEquals(1, j.getCardId());
		assertEquals(40, j.getScore());
		assertEquals(100, j.getCredit());
	}

	@Test
	public void testCreateTCarte() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(TCartes.NAME_FIELD, "TestJUnit4");
		map.put(TCartes.COST_FIELD, 10);
		map.put(TCartes.TYPE_FIELD, Carte.TYPE_RADAR);
		map.put(TCartes.AREA_FIELD, 2);
		map.put(TCartes.DAMAGES_FIELD, 20);
		TCartes c1 = new TCartes();
		c1.insert(map);

		TCartes c2 = new TCartes();
		DBObject o = c2.getById(c2.getIdByCriteria(TCartes.NAME_FIELD,
				"TestJUnit4"));
		Carte j = Carte.createCard(o);

		System.out.println(o);
		System.out.println(j);
		assertEquals("TestJUnit4", j.getName());
		assertEquals(10, j.getCost());
		assertEquals(Carte.TYPE_RADAR, j.getType());
		assertEquals(2, j.getArea());
		assertEquals(20, j.getDamages());
	}

}
