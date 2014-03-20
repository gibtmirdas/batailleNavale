package jUnit;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import db.TCartes;

public class testDB {

	@Test
	public void test() {
		TCartes c = new TCartes();
		List<Object> list = new ArrayList<>();
		list.add("Test");
		list.add(TCartes.DEFAULT_COST);
		list.add("DMG");
		list.add(TCartes.DEFAULT_AREA);
		list.add(TCartes.DEFAULT_DMG);
		//c.insert(list);

		
		int id = c.getIdByCriteria(TCartes.NAME_FIELD, "Test");
		System.out.println(id);
	}
}
