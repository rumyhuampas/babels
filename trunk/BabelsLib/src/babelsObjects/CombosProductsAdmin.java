package babelsObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CombosProductsAdmin {
    public static final String TABLENAME = "Combo_Products";
    private static final String FIELD_ID = "Id";
    public static final String FIELD_IDCOMBO = "IdCombo";
    public static final String FIELD_IDPROD = "IdProduct";
    
    public static ArrayList GetComboProducts(Connection conn, Combo combo) throws SQLException{
        String sql = "SELECT * FROM " + TABLENAME + " WHERE "
                + FIELD_IDCOMBO + "=" + combo.getId();
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ArrayList products = new ArrayList();
     //       qry.setInt(1, combo.getId());
            ResultSet results = qry.executeQuery();
            try {
                while (results.next()) {
                    Product prod = new Product(conn);
                    prod.Load(results.getInt(FIELD_IDPROD));
                    products.add(prod);
                }
                return products;
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }
    
    public static boolean DeleteComboProducts(Connection conn, Combo combo) throws SQLException{
        String sql = "DELETE FROM " + TABLENAME + " WHERE "
                + FIELD_IDCOMBO + "=? ";
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            qry.setInt(1, combo.getId());
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
                + FIELD_IDCOMBO + "," + FIELD_IDPROD
                + ") VALUES (?,?)";
    }
    
    static String GetDeleteFromComboSql() {
        return "DELETE FROM " + TABLENAME + " WHERE "
                + FIELD_IDCOMBO + " = ?";
    }
}
