package babelsObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalesItemsAdmin {
    private static final String TABLENAME = "sale_details";
    private static final String FIELD_ID = "Id";
    private static final String FIELD_IDMOVE = "IdMove";
    private static final String FIELD_IDITEM = "IdItem";
    private static final String FIELD_ITEMTYPE = "ItemType";
    
    public static final String IT_COMBO = "COMBO";
    public static final String IT_PROD = "PRODUCT";
    
    public static ArrayList GetSaleItems(Connection conn, Sale sale) throws SQLException{
        String sql = "SELECT * FROM " + TABLENAME + " WHERE "
                + FIELD_IDMOVE + "=? ";
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ArrayList items = new ArrayList();
            qry.setInt(1, sale.getId());
            ResultSet results = qry.executeQuery();
            try {
                String itemtype = "";
                Object[] item = null;
                while (results.next()) {
                    itemtype = results.getString(FIELD_ITEMTYPE);
                    item = new Object[2];
                    if (itemtype.equals(IT_COMBO)){
                        Combo obj = new Combo(conn);
                        obj.Load(results.getInt(FIELD_IDITEM));
                        item[0] = IT_COMBO;
                        item[1] = obj;
                    }
                    else{
                        Product obj = new Product(conn);
                        obj.Load(results.getInt(FIELD_IDITEM));
                        item[0] = IT_PROD;
                        item[1] = obj;
                    }
                    items.add(item);
                }
                return items;
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }
    
    public static boolean DeleteSaleItems(Connection conn, Sale sale) throws SQLException{
        String sql = "DELETE FROM " + TABLENAME + " WHERE "
                + FIELD_IDMOVE + "=? ";
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            qry.setInt(1, sale.getId());
            try{
                qry.executeUpdate();
                return true;
            } 
            catch(Exception ex){
                return false;
            }
        } finally {
            qry.close();
        }
    } 
    
    static String GetInsertSql() {
        return "INSERT INTO " + TABLENAME + " ("
                + FIELD_IDMOVE + "," + FIELD_IDITEM + "," + FIELD_ITEMTYPE
                + ") VALUES (?,?,?)";
    }
    
    static String GetDeleteSaleItemsSql() {
        return "DELETE FROM " + TABLENAME + " WHERE "
                + FIELD_IDMOVE + " = ?";
    }
}
