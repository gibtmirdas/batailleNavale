package JUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import v2.*;

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
		p2.getIdByCriteria("pseudo", "test");

		assertEquals(p1, p2);

	}

}
