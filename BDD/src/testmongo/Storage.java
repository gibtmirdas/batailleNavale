/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testmongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;

/**
 * 
 * @author antho
 */
public class Storage {
	private final String host = "localhost";
	private final int port = 27017;
	private final String db_name = "db_bataille";
	private static Storage instance;
	private final MongoClient mg_client;
	private final DB database;

	private Storage() throws UnknownHostException {
		mg_client = new MongoClient(host, port);
		database = mg_client.getDB(db_name);
	}

	public static Storage getInstance() {
		if (instance == null) {
			try {
				instance = new Storage();
			} catch (UnknownHostException ex) {
				System.err.println("Cannot find host");
			}
		}
		return instance;
	}

	/**
	 * @return MongoClient client connexion
	 * @throws UnknownHost ..
	 */
	public MongoClient getMongoClient() throws UnknownHostException {
		return mg_client;
	}

	public DBCollection getCollection(String collectionName) {
		DBCollection table = database.getCollection(collectionName);
		return table;
	}

	public DBCursor findAll(DBCollection table) {
		// return cursor = table.find();
		return table.find();
	}

}
