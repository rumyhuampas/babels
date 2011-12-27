package babelsObjects;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Product {

    private final String TABLENAME = "Products";
    private final String FIELD_ID = "Id";
    private final String FIELD_NAME = "Name";
    private final String FIELD_DESC = "Description";
    private final String FIELD_IMAGE = "Image";
    private final String FIELD_PRICE = "Price";
    private Connection Conn;
    private int Id;
    public String Name;
    public String Desc;
    private Image Img;
    private File ImageFile;
    private InputStream ImageStream;
    public float Price;

    public int getId() {
        return this.Id;
    }

    public void SetImage(String imagePath) {
        if (imagePath != null) {
            this.ImageFile = new File(imagePath);
            if (this.ImageFile.exists()) {
                try {
                    this.ImageStream = new FileInputStream(this.ImageFile);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                this.ImageFile = null;
            }
        }
    }

    public Image GetImage() {
        return this.Img;
    }

    public ImageIcon GetImageIcon() {
        if (this.Img != null) {
            return new ImageIcon(this.Img);
        } else {
            return null;
        }
    }

    public void ClearImage() {
        this.Img = null;
        this.ImageFile = null;
        this.ImageStream = null;
    }

    public Product(Connection conn) throws SQLException {
        this.Conn = conn;
        this.Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.Name = "";
        this.Desc = "";
        this.Price = Float.parseFloat("0");
        this.ClearImage();
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
                this.ImageStream = results.getBinaryStream(this.FIELD_IMAGE);
                if (this.ImageStream != null) {
                    try {
                        this.Img = ImageIO.read(this.ImageStream);
                        this.ImageStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
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
        if (!Exists()) {
            if (this.Id == -1) {
                return InsertProduct();
            } else {
                return UpdateProduct();
            }
        } else {
            return false;
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
            if (this.ImageFile != null) {
                qry.setBinaryStream(3, this.ImageStream, (int) (this.ImageFile.length()));
            } else {
                qry.setBinaryStream(3, null);
            }
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
            if (this.ImageFile != null) {
                qry.setBinaryStream(3, this.ImageStream, (int) (this.ImageFile.length()));
            } else {
                qry.setBinaryStream(3, null);
            }
            qry.setFloat(4, this.Price);
            qry.setInt(5, this.Id);
            return qry.executeUpdate() > 0;
        } finally {
            qry.close();
        }
    }

    public boolean Exists() throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_NAME + " = ? AND "
                + this.FIELD_ID + " <> ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Name);
            qry.setInt(2, this.Id);
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

    public boolean Delete() throws SQLException {
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
