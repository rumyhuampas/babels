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
    private Image Img;
    private File ImageFile;
    private FileInputStream ImageStream;
    public float Price;

    public int getId() {
        return this.Id;
    }
    
    public void SetImage(String imagePath){
        this.ImageFile = new File(imagePath);
        if (this.ImageFile.exists()){
            try {
                this.ImageStream = new FileInputStream(this.ImageFile);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Image GetImage(){
        return this.Img;
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
        this.Img = null;
        this.ImageFile = null;
        this.ImageStream = null;
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
                this.ImageStream = (FileInputStream) results.getBinaryStream(this.FIELD_IMAGE);
                try {
                    this.Img = ImageIO.read(this.ImageStream);
                    this.ImageStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
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
                
            File f = new File("C:\\fondo1.jpg");
            try {
                qry.setBlob(3, new FileInputStream(f), f.length());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            //qry.setBinaryStream(3, (InputStream)this.ImageStream, (int)(this.ImageFile.length()));
            //qry.setBinaryStream(3, this.Image);
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
            qry.setBinaryStream(3, (InputStream)this.ImageStream, (int)this.ImageFile.length());
            //qry.setBinaryStream(3, this.Image);
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
