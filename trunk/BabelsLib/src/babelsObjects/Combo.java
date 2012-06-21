package babelsObjects;

import babelsComponents.ImageManagement;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Combo {

    private final String TABLENAME = "Combos";
    private final String FIELD_ID = "Id";
    private final String FIELD_NAME = "Name";
    private final String FIELD_DESC = "Description";
    private final String FIELD_PRICE = "Price";
    private final String FIELD_IMAGE = "Image";
    private Connection Conn;
    private int Id;
    public String Name;
    public String Desc;
    public float Price;
    private Image Img;
    private File ImageFile;
    public ArrayList Products;
    public boolean ImageChanged;
    public InputStream ImageStream;

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

    public Combo(Connection conn) throws SQLException {
        this.Conn = conn;
        Products = new ArrayList();
        this.Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.Name = "";
        this.Desc = "";
        this.Price = Float.parseFloat("0");
        this.Products.clear();
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectCombo(qry);
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
            return SelectCombo(qry);
        } finally {
            qry.close();
        }
    }

    public void ClearImage() {
        this.Img = null;
        this.ImageFile = null;
        this.ImageStream = null;
    }

    public ImageIcon GetImageIconResized(int width, int height) {
        if (this.Img == null) {
            GetImage();
        }
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
        if (this.Img == null) {
            GetImage();
        }
        if (this.Img != null) {
            return new ImageIcon(this.Img);
        } else {
            return null;
        }
    }

    private boolean SelectCombo(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.Name = results.getString(this.FIELD_NAME);
                this.Desc = results.getString(this.FIELD_DESC);
                this.Price = results.getFloat(this.FIELD_PRICE);
                this.Products = CombosProductsAdmin.GetComboProducts(this.Conn, this);
                this.ImageStream = results.getBinaryStream(this.FIELD_IMAGE);
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
                if (InsertCombo() == true) {
                    if (SaveComboProducts() == true) {
                        return true;
                    } else {
                        Delete();
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                if (SaveComboProducts() == true) {
                    return UpdateCombo();
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    private boolean SaveComboProducts() throws SQLException {
        boolean result = false;
        if (CombosProductsAdmin.DeleteComboProducts(this.Conn, this) == true) {
            this.Conn.setAutoCommit(false);
            try {
                PreparedStatement qry = null;
                qry = this.Conn.prepareStatement(CombosProductsAdmin.GetInsertSql());
                for (int i = 0; i < this.Products.size(); i++) {
                    Product prod = ((Product) this.Products.get(i));
                    qry.setInt(1, this.Id);
                    qry.setInt(2, prod.getId());
                    // qry.executeUpdate();
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

    private boolean InsertCombo() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_NAME + ", " + this.FIELD_DESC + ", "
                + this.FIELD_PRICE + ", " + this.FIELD_IMAGE + " "
                + ") VALUES (?,?,?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setString(1, this.Name);
            qry.setString(2, this.Desc);
            qry.setFloat(3, this.Price);
            if (this.ImageFile != null) {
                qry.setBinaryStream(4, this.ImageStream, (int) (this.ImageFile.length()));
            } else {
                qry.setBinaryStream(4, null);
            }
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

    private boolean UpdateCombo() throws SQLException {
        String sql = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_NAME + " = ?,"
                + this.FIELD_DESC + " = ?,"
                + this.FIELD_PRICE + " = ? "
                + "WHERE " + this.FIELD_ID + " = ?";
        String sql2 = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_NAME + " = ?,"
                + this.FIELD_DESC + " = ?,"
                + this.FIELD_PRICE + " = ?, "
                + this.FIELD_IMAGE + " = ? "
                + "WHERE " + this.FIELD_ID + " = ?";
        if (ImageChanged == true) {
            PreparedStatement qry = this.Conn.prepareStatement(sql2);
            try {
                qry.setString(1, this.Name);
                qry.setString(2, this.Desc);
                qry.setFloat(3, this.Price);
                if (this.ImageFile != null) {
                    // DE DONDE VIENE IMAGEFILE (EL PATH)SI NO ESTA EN LA BASE DE DATOS Y EL MODIFICAR OBTIENE LOS DATOS D AHI
                    qry.setBinaryStream(4, this.ImageStream, (int) (this.ImageFile.length()));
                } else {
                    qry.setBinaryStream(4, null);
                }
                qry.setInt(5, this.Id);
                return qry.executeUpdate() > 0;
            } finally {
                qry.close();
            }
        } else {
            PreparedStatement qry = this.Conn.prepareStatement(sql);
            try {
                qry.setString(1, this.Name);
                qry.setString(2, this.Desc);
                qry.setFloat(3, this.Price);
                qry.setInt(4, this.Id);
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
        if (DeleteComboProducts()) {
            if (DeleteCombo() == true) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean DeleteComboProducts() throws SQLException {
        PreparedStatement qry = this.Conn.prepareStatement(CombosProductsAdmin.GetDeleteFromComboSql());
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

    private boolean DeleteCombo() throws SQLException {
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
