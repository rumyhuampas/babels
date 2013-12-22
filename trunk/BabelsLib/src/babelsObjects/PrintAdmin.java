package babelsObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrintAdmin {
    private static final String TABLENAME = "Prints";
    private static final String FIELD_ID = "Id";
    private static final String FIELD_IDMOVE = "IdMove";
    private static final String FIELD_DATEPOSTED = "DatePosted";
    private static final String FIELD_STATUS = "Status";
    private static final String FIELD_DATEPRINTED = "DatePrinted";
    private static final String FIELD_PRINTER = "Printer";
    private static final String FIELD_DATA = "Data";
    private static final String FIELD_RETRIES = "Retries";
    
    public static final String ST_PEND = "Pending";
    public static final String ST_PRIN = "Printing";
    public static final String ST_COMP = "Completed";
    public static final String ST_FAIL = "Failed";

    public static final String PR_FIS = "FISCAL";
    public static final String PR_NOFIS = "NOFISCAL";
    public static final String PR_CLIENTE = "CLIENTE";

    public static Object[] GetAllPrints(Connection conn, String printer, int limit) throws SQLException {
        String sql = "SELECT * FROM " + TABLENAME;
        if(printer != ""){
            sql = sql + " WHERE PRINTER IN (" + printer + ") ";
        }
        sql = sql + " ORDER BY " + FIELD_DATEPOSTED;
        if(limit > 0){
            sql = sql + " LIMIT " + String.valueOf(limit);
        }
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ArrayList rows = new ArrayList();
            ArrayList row = new ArrayList();
            ResultSet results = qry.executeQuery();
            try {
                while (results.next()) {
                    row.add(results.getInt(FIELD_ID));
                    row.add(results.getString(FIELD_IDMOVE));
                    row.add(results.getString(FIELD_DATEPOSTED));
                    row.add(results.getString(FIELD_STATUS));
                    row.add(results.getString(FIELD_PRINTER));
                    row.add(results.getString(FIELD_RETRIES));
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
