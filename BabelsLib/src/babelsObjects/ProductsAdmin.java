package babelsObjects;

import babelsComponents.ImageManagement;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ProductsAdmin {

    private static final String TABLENAME = "Products";
    private static final String FIELD_ID = "Id";
    private static final String FIELD_NAME = "Name";
    private static final String FIELD_DESC = "Description";
    private static final String FIELD_PRICE = "Price";
    private static final String FIELD_IMAGE = "Image";

    public static Object[] GetAllProducts(Connection conn) throws SQLException {
        String sql = "SELECT * FROM " + TABLENAME + " ORDER BY NAME ";
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ArrayList rows = new ArrayList();
            ArrayList row = new ArrayList();
            ResultSet results = qry.executeQuery();
            try {
                while (results.next()) {
                    row.add(results.getInt(FIELD_ID));
                    row.add(results.getString(FIELD_NAME));
                    row.add(results.getString(FIELD_DESC));
                    row.add(results.getFloat(FIELD_PRICE));
                    rows.add(row.toArray());
                    row.clear();
                }
                return rows.toArray();
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }

    public static Object[] GetAllProductsWithImage(Connection conn) throws SQLException {
        String sql = "SELECT * FROM " + TABLENAME + " ORDER BY NAME ";
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ArrayList rows = new ArrayList();
            ArrayList row = new ArrayList();
            ResultSet results = qry.executeQuery();
            try {
                while (results.next()) {
                    row.add(results.getInt(FIELD_ID));
                    row.add(results.getString(FIELD_NAME));
                    row.add(results.getString(FIELD_DESC));
                    row.add(results.getFloat(FIELD_PRICE));
                    InputStream ImageStream = results.getBinaryStream(FIELD_IMAGE);
                    if (ImageStream != null) {
                        try {
                            Image image2 = ImageIO.read(ImageStream);

                            ImageManagement gImg = new ImageManagement(image2);
                            image2 = gImg.getImage();
                            BufferedImage tnsImg = new BufferedImage(85, 45, BufferedImage.TYPE_INT_RGB);
                            Graphics2D graphics2D = tnsImg.createGraphics();
                            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                            graphics2D.drawImage(image2, 0, 0, 85, 45, null);

                            row.add(new ImageIcon(tnsImg));
                            ImageStream.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    rows.add(row.toArray());
                    row.clear();
                }
                return rows.toArray();
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }
}
