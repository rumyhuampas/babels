package andro.babels.models;

import andro.babels.controllers.Welcome;
import babelsObjects.Product;
import java.io.InputStream;
import java.sql.SQLException;

public class Products extends andro.babels.models.Base{
    public InputStream GetProductImage(Integer id) throws SQLException {
        Product product = new Product(Welcome.mysql.Conn);
        product.Load(id);
        return product.ImageStream;
    }
}
