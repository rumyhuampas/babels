package babelsObjects;

import java.sql.*;

public class MovementTypes {
    public static final String TABLENAME = "movement_types";
    public static final String FIELD_ID = "Id";
    public static final String FIELD_NAME = "Name";
    
    public static final String MT_APER = "APERTURA";
    public static final String MT_CIERRE = "CIERRE";
    public static final String MT_CIERREPARC = "CIERREPARCIAL";
    
    private Connection Conn;
    private int Id;
    public String Name;

    public int getId() {
        return this.Id;
    }

    public MovementTypes(Connection conn) throws SQLException {
        this.Conn = conn;
        this.Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.Name = "";
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectType(qry);
        } finally {
            qry.close();
        }
    }

    public boolean Load(String name) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_NAME + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, name);
            return SelectType(qry);
        } finally {
            qry.close();
        }
    }

    private boolean SelectType(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.Name = results.getString(this.FIELD_NAME);
                return true;
            } else {
                return false;
            }
        } finally {
            results.close();
        }
    }
}
