package andro.babels.models;

import babelsObjects.Combo;
import babelsObjects.Product;
import babelsObjects.SaleAdmin;
import java.sql.SQLException;

public class ItemDetails extends andro.babels.models.Base {

    public Object GetItem(int itemID, String itemType) throws SQLException {
        if (itemType.equals(SaleAdmin.IT_COMBO)){
            Combo combo = new Combo(andro.babels.controllers.Welcome.mysql.Conn);
            combo.Load(itemID);
            return combo;
        }
        else{
            Product prod = new Product(andro.babels.controllers.Welcome.mysql.Conn);
            prod.Load(itemID);
            return prod;
        }
    }
}
