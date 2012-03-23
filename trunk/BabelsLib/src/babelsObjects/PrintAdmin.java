package babelsObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrintAdmin {
    private static final String TABLENAME = "SalesPrints";
    private static final String FIELD_ID = "Id";
    private static final String FIELD_IDSALE = "IdSale";
    private static final String FIELD_DATEPOSTED = "DatePosted";
    private static final String FIELD_STATUS = "Status";
    private static final String FIELD_DATEPRINTED = "DatePrinted";
    
    public static final String ST_PEND = "PENDING";
    public static final String ST_PRIN = "PRINTING";
    public static final String ST_COMP = "COMPLETED";
    public static final String ST_FAIL = "FAILED";
    
    public static ArrayList GetPendingPrints(Connection conn) throws SQLException{
        String sql = "SELECT * FROM " + TABLENAME + " WHERE "
                + FIELD_STATUS + "=? ";
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ArrayList items = new ArrayList();
            qry.setString(1, ST_PEND);
            ResultSet results = qry.executeQuery();
            try {
                while (results.next()) {
                    Sale sale = new Sale(conn);
                    sale.Load(results.getInt(FIELD_IDSALE));
                    items.add(sale);
                }
                return items;
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }
}
