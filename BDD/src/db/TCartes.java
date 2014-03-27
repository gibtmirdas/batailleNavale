/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.mongodb.BasicDBObject;
import java.util.ArrayList;
import java.util.Map;
import models.Carte;
import models.FactoryCarte;

/**
 *
 * @author antho
 */
public class TCartes extends AbstractTable {

    public static final String ID_FIELD = "id", NAME_FIELD = "name",
            COST_FIELD = "cost", TYPE_FIELD = "type", AREA_FIELD = "area",
            DAMAGES_FIELD = "damages";
    public static final int DEFAULT_ID = 0, DEFAULT_COST = 0, DEFAULT_AREA = 1,
            DEFAULT_DMG = 1;

    private final String tableName = "cartes";

    public TCartes() {
        linkToTable = Storage.getInstance().getCollection(tableName);
    }

    /**
     * Insert a new card in the database
     *
     * @param args args[0]: Nom args[2]: coûts args[3]: type
     */
    @Override
    public void insert(Map<String, Object> args) {
        assert args.containsKey(NAME_FIELD) && args.containsKey(TYPE_FIELD);
        BasicDBObject insertQuery = new BasicDBObject();
        
        Object cost_field, type_field, area_field, damages_field, name_field;
        
        name_field = args.containsKey(NAME_FIELD);
        type_field = args.containsKey(TYPE_FIELD);
        cost_field = args.containsKey(COST_FIELD) ? args.get(COST_FIELD) : DEFAULT_COST;
        area_field = args.containsKey(AREA_FIELD) ? args.get(AREA_FIELD) : DEFAULT_AREA;
        damages_field = args.containsKey(DAMAGES_FIELD) ? args.get(DAMAGES_FIELD) : DEFAULT_DMG;
        
        insertQuery.put(TCartes.ID_FIELD, this.getLastID() + 1);
        insertQuery.put(TCartes.NAME_FIELD, name_field);
        insertQuery.put(TCartes.COST_FIELD, type_field);
        insertQuery.put(TCartes.TYPE_FIELD, cost_field);
        insertQuery.put(TCartes.AREA_FIELD, area_field);
        insertQuery.put(TCartes.DAMAGES_FIELD, damages_field);

        linkToTable.insert(insertQuery);
    }

    public ArrayList<Carte> getAll() throws ClassNotFoundException {
        int nb = this.getLastID();
        ArrayList<Carte> l = new ArrayList<>();
        for (int i = 0; i <= nb; i++) {
            l.add(FactoryCarte.getCarte(this.getById(i)));
        }
        assert l.size() > 0;
        return l;
    }
}
