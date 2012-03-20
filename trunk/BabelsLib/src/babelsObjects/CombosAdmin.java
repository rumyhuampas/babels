package babelsObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CombosAdmin {

    private static final String TABLENAME = "Combos";
    private static final String FIELD_ID = "Id";
    private static final String FIELD_NAME = "Name";
    private static final String FIELD_DESC = "Description";
    private static final String FIELD_PRICE = "Price";

    public static Object[] GetAllCombos(Connection conn) throws SQLException {
        String sql = "SELECT * FROM " + TABLENAME + " ORDER BY " + FIELD_NAME;
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ArrayList rows = new ArrayList();
            ArrayList row = new ArrayList();
            ResultSet results = qry.executeQuery();
            try {
                while (results.next()) {
                    row.add(results.getInt(FIELD_ID));
                    row.add(results.getString(FIELD_NAME));
                    row.add(results.getString(FIELD_DESC));
                    row.add(results.getFloat(FIELD_PRICE));
                    rows.add(row.toArray());
                    row.clear();
                }
                return rows.toArray();
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }
}
