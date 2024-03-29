package babelsObjects;

import java.sql.*;
import java.util.ArrayList;

public class Sale extends Movement {

    public static final String TYPE_A = "VENTA_A";
    public static final String TYPE_B = "VENTA_B";
    public static final String TYPE_X = "VENTA_X";
    public ArrayList Items;

    public Date getDate() {
        return this.DatePosted;
    }

    public Sale(Connection conn) throws SQLException {
        super(conn);
        Items = new ArrayList();
        this.Clear();
    }

    public void Clear() {
        this.Items.clear();
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ? AND "
                + this.FIELD_TYPE + " IN "
                + "(SELECT " + MovementTypes.FIELD_ID + " FROM " + MovementTypes.TABLENAME
                + " WHERE " + MovementTypes.FIELD_NAME + " in ('" + TYPE_A + "','" + TYPE_B + "','" + TYPE_X + "'))";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        boolean result = false;
        try {
            qry.setInt(1, id);
            result = SelectMovement(qry);
        } finally {
            qry.close();
        }

        if (result == true) {
            this.Items = SalesItemsAdmin.GetSaleItems(this.Conn, this);
        }
        return result;
    }

    public boolean Save() throws SQLException {
        if (this.Id == -1) {
            if (InsertMovement() == true) {
                if (SaveSaleItems() == true) {
                    return true;
                } else {
                    this.Delete();
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean SaveSaleItems() throws SQLException {
        boolean result = false;
        if (SalesItemsAdmin.DeleteSaleItems(this.Conn, this) == true) {
            this.Conn.setAutoCommit(false);
            try {
                PreparedStatement qry = null;
                qry = this.Conn.prepareStatement(SalesItemsAdmin.GetInsertSql());
                for (int i = 0; i < this.Items.size(); i++) {
                    qry.setInt(1, this.Id);
                    if (((Object[]) this.Items.get(i))[0] == SalesItemsAdmin.IT_COMBO) {
                        Combo combo = ((Combo) ((Object[]) this.Items.get(i))[1]);
                        qry.setInt(2, combo.getId());
                        qry.setString(3, SalesItemsAdmin.IT_COMBO);
                    } else {
                        Product prod = ((Product) ((Object[]) this.Items.get(i))[1]);
                        qry.setInt(2, prod.getId());
                        qry.setString(3, SalesItemsAdmin.IT_PROD);
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

    public boolean Delete() throws SQLException {
        if (DeleteSaleItems()) {
            if (super.Delete() == true) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean DeleteSaleItems() throws SQLException {
        PreparedStatement qry = this.Conn.prepareStatement(SalesItemsAdmin.GetDeleteSaleItemsSql());
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
}
