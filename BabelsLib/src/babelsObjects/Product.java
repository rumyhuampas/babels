package babelsObjects;

import babelsComponents.ImageManagement;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
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
    private final String FIELD_COSTPRICE = "CostPrice";
    private final String FIELD_PRICEPACKAGING = "PricePackaging";
    private final String FIELD_IVA = "Iva";
    private final String FIELD_IDKITCHEN = "IdKitchen";
    private final String FIELD_IDCATEGORIES = "IdCategories";
    private Connection Conn;
    private int Id;
    public String Name;
    public String Desc;
    public float CostPrice;
    public float PricePackaging;
    public float Iva;
    public int Idkitchen;
    public int IdCategories;
    private Image Img;
    private File ImageFile;
    public boolean ImageChanged;
    public InputStream ImageStream;
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
        if (this.ImageStream != null) {
            try {
                this.Img = ImageIO.read(this.ImageStream);
                this.ImageStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.Img;
    }

    public ImageIcon GetImageIcon() {
        if (this.Img == null) GetImage();
        if (this.Img != null) {
            return new ImageIcon(this.Img);
        } else {
            return null;
        }
    }

    public ImageIcon GetImageIconResized(int width, int height) {
        if (this.Img == null) GetImage();
        if (this.Img != null) {
            Image image2 = this.Img;
            ImageManagement gImg = new ImageManagement(image2);
            image2 = gImg.getImage();
            BufferedImage tnsImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = tnsImg.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(image2, 0, 0, width, height, null);
            //  Icon icon = new ImageIcon(tnsImg);


            return new ImageIcon(tnsImg);
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
        this.CostPrice = Float.parseFloat("0");
        this.PricePackaging = Float.parseFloat("0");
        this.Iva = Float.parseFloat("0");
        this.IdCategories = 0;
        this.Idkitchen = 0;
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
                this.Price = results.getFloat(this.FIELD_PRICE);
                this.CostPrice = results.getFloat(this.FIELD_COSTPRICE);
                this.Iva = results.getFloat(this.FIELD_IVA);
                this.PricePackaging= results.getFloat(this.FIELD_PRICEPACKAGING);
                this.IdCategories= results.getInt(this.FIELD_IDCATEGORIES);
                this.Idkitchen= results.getInt(this.FIELD_IDKITCHEN);
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
                + this.FIELD_COSTPRICE + ", " + this.FIELD_PRICEPACKAGING + ", "
                + this.FIELD_IVA + ", " + this.FIELD_IDKITCHEN + ", "
                + this.FIELD_IDCATEGORIES + ", "
                + this.FIELD_IMAGE + ", " + this.FIELD_PRICE
                + ") VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setString(1, this.Name);
            qry.setString(2, this.Desc);
            qry.setFloat(3, this.CostPrice);
            qry.setFloat(4, this.PricePackaging);
            qry.setFloat(5, Iva);
            qry.setInt(6, this.Idkitchen);
            qry.setInt(7, this.IdCategories);
            if (this.ImageFile != null) {
                qry.setBinaryStream(8, this.ImageStream, (int) (this.ImageFile.length()));
            } else {
                qry.setBinaryStream(8, null);
            }
            qry.setFloat(9, this.Price);
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
                + this.FIELD_PRICE + " = ?,"
                + this.FIELD_COSTPRICE + " = ?,"
                + this.FIELD_PRICEPACKAGING + " = ?,"
                + this.FIELD_IVA + " = ?,"
                + this.FIELD_IDKITCHEN + " = ?,"
                + this.FIELD_IDCATEGORIES + " =?"
                + " WHERE " + this.FIELD_ID + " = ?";

        String sql2 = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_NAME + " = ?,"
                + this.FIELD_DESC + " = ?,"
                + this.FIELD_PRICE + " = ?,"
                + this.FIELD_COSTPRICE + " = ?,"
                + this.FIELD_PRICEPACKAGING + " = ?,"
                + this.FIELD_IVA + " = ?,"
                + this.FIELD_IDKITCHEN + " = ?,"
                + this.FIELD_IDCATEGORIES + " =?"
                + " WHERE " + this.FIELD_ID + " = ?";
        if (ImageChanged == true) {
            PreparedStatement qry = this.Conn.prepareStatement(sql);
            try {
                qry.setString(1, this.Name);
                qry.setString(2, this.Desc);
                if (this.ImageFile != null) {
                    // DE DONDE VIENE IMAGEFILE (EL PATH)SI NO ESTA EN LA BASE DE DATOS Y EL MODIFICAR OBTIENE LOS DATOS D AHI
                    qry.setBinaryStream(3, this.ImageStream, (int) (this.ImageFile.length()));
                } else {
                    qry.setBinaryStream(3, null);
                }
                qry.setFloat(4, this.Price);
                qry.setFloat(5, this.CostPrice);
                qry.setFloat(6, this.PricePackaging);
                qry.setFloat(7, this.Iva);
                qry.setInt(8, this.Idkitchen);
                qry.setInt(9, this.IdCategories);
                qry.setInt(10, this.Id);
                return qry.executeUpdate() > 0;
            } finally {
                qry.close();
            }
        } else {
            PreparedStatement qry = this.Conn.prepareStatement(sql2);
            try {
                qry.setString(1, this.Name);
                qry.setString(2, this.Desc);
                qry.setFloat(3, this.Price);
                qry.setFloat(4, this.CostPrice);
                qry.setFloat(5, this.PricePackaging);
                qry.setFloat(6, this.Iva);
                qry.setInt(7, this.Idkitchen);
                qry.setInt(8, this.IdCategories);
                qry.setInt(9, this.Id);
                return qry.executeUpdate() > 0;
            } finally {
                qry.close();
            }
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
