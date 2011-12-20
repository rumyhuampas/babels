package babelsObjects;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Product {

    private final String TABLENAME = "Products";
    private final String FIELD_ID = "Id";
    private final String FIELD_NAME = "Name";
    private final String FIELD_DESC = "Desc";
    private final String FIELD_IMAGE = "Image";
    private final String FIELD_PRICE = "Price";
    private Connection Conn;
    private int Id;
    public String Name;
    public String Desc;
    public InputStream Image;
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
        this.Desc = "";
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
                this.Desc = results.getString(this.FIELD_DESC);
                this.Image = results.getBinaryStream(this.FIELD_IMAGE);
                this.Price = results.getFloat(this.FIELD_PRICE);
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
            if (!Exists()) {
                return InsertProduct();
            } else {
                return false;
            }
        } else {
            return UpdateProduct();
        }
    }

    private boolean InsertProduct() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_NAME + ", " + this.FIELD_DESC + ", "
                + this.FIELD_IMAGE + ", " + this.FIELD_PRICE
                + ") VALUES (?,?,?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setString(1, this.Name);
            qry.setString(2, this.Desc);
            qry.setBinaryStream(3, this.Image);
            qry.setFloat(4, this.Price);
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

    private boolean UpdateProduct() throws SQLException {
        String sql = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_NAME + " = ?,"
                + this.FIELD_DESC + " = ?,"
                + this.FIELD_IMAGE + " = ?,"
                + this.FIELD_PRICE + " = ?"
                + "WHERE " + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Name);
            qry.setString(2, this.Desc);
            qry.setBinaryStream(3, this.Image);
            qry.setFloat(4, this.Price);
            qry.setInt(5, this.Id);
            return qry.executeUpdate() > 0;
        } finally {
            qry.close();
        }
    }

    public boolean Exists() throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_NAME + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Name);
            ResultSet results = qry.executeQuery();
            try {
                if (results.next()) {
                    return true;
                } else {
                    return false;
                }
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }
}
