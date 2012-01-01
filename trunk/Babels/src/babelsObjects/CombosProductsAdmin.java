package babelsObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CombosProductsAdmin {
    private static final String TABLENAME = "CombosProducts";
    private static final String FIELD_ID = "Id";
    private static final String FIELD_IDCOMBO = "IdCombo";
    private static final String FIELD_IDPROD = "IdProduct";
    
    public static ArrayList GetComboProducts(Connection conn, Combo combo) throws SQLException{
        String sql = "SELECT * FROM " + TABLENAME + " WHERE "
                + FIELD_IDCOMBO + "=? ";
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ArrayList products = new ArrayList();
            qry.setInt(1, combo.getId());
            ResultSet results = qry.executeQuery(sql);
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
}
