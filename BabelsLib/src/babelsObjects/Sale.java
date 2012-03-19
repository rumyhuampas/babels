package babelsObjects;

import java.sql.*;
import java.util.ArrayList;

public class Sale {

    private final String TABLENAME = "Sales";
    private final String FIELD_ID = "Id";
    private final String FIELD_TOTAL = "Total";
    private Connection Conn;
    private int Id;
    public float Total;
    public ArrayList Items;

    public int getId() {
        return this.Id;
    }

    public Sale(Connection conn) throws SQLException {
        this.Conn = conn;
        Items = new ArrayList();
        this.Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.Total = Float.parseFloat("0");
        this.Items.clear();
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectSale(qry);
        } finally {
            qry.close();
        }
    }

    private boolean SelectSale(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.Total = results.getFloat(this.FIELD_TOTAL);
                this.Items = SaleAdmin.GetSaleItems(this.Conn, this);
                return true;
            } else {
                return false;
            }
        } finally {
            results.close();
        }
    }

    public boolean Save() throws SQLException {
        if (this.Id == -1) {
            if (InsertSale() == true) {
                if (SaveSaleItems() == true) {
                    return true;
                } else {
                    Delete();
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (SaveSaleItems() == true) {
                return UpdateSale();
            } else {
                return false;
            }
        }
    }

    private boolean SaveSaleItems() throws SQLException {
        boolean result = false;
        if (SaleAdmin.DeleteSaleItems(this.Conn, this) == true) {
            this.Conn.setAutoCommit(false);
            try {
                PreparedStatement qry = null;
                qry = this.Conn.prepareStatement(SaleAdmin.GetInsertSql());
                for (int i = 0; i < this.Items.size(); i++) {
                    qry.setInt(1, this.Id);
                    if (((Object[])this.Items.get(i))[0] == SaleAdmin.IT_COMBO){
                        Combo combo = ((Combo) ((Object[])this.Items.get(i))[1]);
                        qry.setInt(2, combo.getId());
                        qry.setString(3, SaleAdmin.IT_COMBO);
                    }
                    else{
                        Product prod = ((Product) ((Object[])this.Items.get(i))[1]);
                        qry.setInt(2, prod.getId());
                        qry.setString(3, SaleAdmin.IT_PROD);
                    }
                    qry.addBatch();
                }
                qry.executeBatch();
                this.Conn.commit();

                result = true;
            } catch (Exception ex) {
                this.Conn.rollback();
                result = false;
            } finally {
                this.Conn.setAutoCommit(true);
            }
        }
        return result;
    }

    private boolean InsertSale() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_TOTAL
                + ") VALUES (?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setFloat(1, this.Total);
            if (qry.executeUpdate() > 0) {
                ResultSet result = qry.getGeneratedKeys();
                result.next();
                this.Id = result.getInt("GENERATED_KEY");
                return true;
            } else {
                return false;
            }
        } finally {
            qry.close();
        }
    }

    private boolean UpdateSale() throws SQLException {
        String sql = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_TOTAL + " = ? "
                + "WHERE " + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setFloat(1, this.Total);
            qry.setInt(2, this.Id);
            return qry.executeUpdate() > 0;
        } finally {
            qry.close();
        }
    }

    public boolean Delete() throws SQLException {
        if (DeleteSaleItems()) {
            if (DeleteSale() == true) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean DeleteSaleItems() throws SQLException {
        PreparedStatement qry = this.Conn.prepareStatement(SaleAdmin.GetDeleteSaleItemsSql());
        try {
            qry.setInt(1, this.Id);
            try {
                qry.executeUpdate();
                return true;
            } catch (Exception ex) {
                return false;
            }
        } finally {
            qry.close();
        }
    }

    private boolean DeleteSale() throws SQLException {
        String sql = "DELETE FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, this.Id);
            if (qry.executeUpdate() > 0) {
                this.Id = -1;
                return true;
            } else {
                return false;
            }
        } finally {
            qry.close();
        }
    }
}
