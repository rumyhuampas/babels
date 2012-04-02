package andro.babels.models;

import andro.babels.wrappers.SaleList;
import babelsObjects.Combo;
import babelsObjects.Product;
import babelsObjects.Sale;
import babelsObjects.SalesItemsAdmin;
import java.sql.SQLException;

public class CancelSale extends andro.babels.models.Base {

    public void Search(SaleList saleList, String search) throws SQLException {
        saleList.ClearSalelist();
        Sale sale = new Sale(andro.babels.controllers.Welcome.mysql.Conn);
        if (sale.Load(Integer.parseInt(search))) {
            for (int i = 0; i < sale.Items.size(); i++) {
                if (((Object[]) sale.Items.get(i))[0] == SalesItemsAdmin.IT_COMBO) {
                    Combo combo = ((Combo) ((Object[]) sale.Items.get(i))[1]);
                    saleList.AddSaleItem(combo.getId(), combo.Name, combo.Price, SalesItemsAdmin.IT_COMBO);
                } else {
                    Product prod = ((Product) ((Object[]) sale.Items.get(i))[1]);
                    saleList.AddSaleItem(prod.getId(), prod.Name, prod.Price, SalesItemsAdmin.IT_PROD);
                }
            }
        }
    }
}
