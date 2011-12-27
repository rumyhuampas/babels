package babelsObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersAdmin {

    private static final String TABLENAME = "Users";
    private static final String FIELD_ID = "Id";
    private static final String FIELD_NAME = "Name";
    private static final String FIELD_ISADMIN = "IsAdmin";
    private static final String FIELD_ACTIVE = "Active";

    public static Object[] GetAllUsers(Connection conn) throws SQLException {
        String sql = "SELECT * FROM " + TABLENAME + " ORDER BY NAME ";
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ArrayList rows = new ArrayList();
            ArrayList row = new ArrayList();
            ResultSet results = qry.executeQuery(sql);
            try {
                while (results.next()) {
                    row.add(results.getInt(FIELD_ID));
                    row.add(results.getString(FIELD_NAME));
                    row.add(results.getBoolean(FIELD_ISADMIN));
                    row.add(results.getBoolean(FIELD_ACTIVE));
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
