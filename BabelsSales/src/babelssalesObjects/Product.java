package babelssalesObjects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Product {

    private final String TABLENAME = "Products";
    private final String FIELD_ID = "Id";
    private final String FIELD_NAME = "Name";
    private final String FIELD_PRICE = "Price";
    private Connection Conn;
    private int Id;
    public String Name;
    public float Price;

    public int getId() {
        return this.Id;
    }

    public Product(Connection conn) throws SQLException {
        this.Conn = conn;
        Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.Name = "";
        this.Price = Float.parseFloat("0");
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectProduct(qry);
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
            return SelectProduct(qry);
        } finally {
            qry.close();
        }
    }

    private boolean SelectProduct(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.Name = results.getString(this.FIELD_NAME);
                this.Price = results.getFloat(this.FIELD_PRICE);
                return true;
            } else {
                return false;
            }
        } finally {
            results.close();
        }
    }
}
