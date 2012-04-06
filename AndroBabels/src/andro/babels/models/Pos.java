package andro.babels.models;

import andro.babels.wrappers.SaleList;
import babelsObjects.Combo;
import babelsObjects.Product;
import babelsObjects.Sale;
import babelsObjects.SalesItemsAdmin;
import java.sql.SQLException;
import java.util.List;

public class Pos extends andro.babels.models.Base {

    public void SaveSale(SaleList saleList, String type) throws SQLException {
        Sale sale = new Sale(andro.babels.controllers.Welcome.mysql.Conn);
        sale.Total = saleList.GetSaleTotal();
        sale.Type = type;
        List<SaleList.SaleItem> items = saleList.GetGeneralList();
        SaleList.SaleItem saleItem;
        for (int i=0; i<items.size(); i++){
            saleItem = items.get(i);
            Object[] item = new Object[2];
            item[0] = saleItem.type;
            if (saleItem.type.equals(SalesItemsAdmin.IT_COMBO)){
                Combo combo = new Combo(andro.babels.controllers.Welcome.mysql.Conn);
                combo.Load(saleItem.id);
                item[1] = combo;
            }
            else{
                Product prod = new Product(andro.babels.controllers.Welcome.mysql.Conn);
                prod.Load(saleItem.id);
                item[1] = prod;
            }
            sale.Items.add(item);
        }
        sale.Save();
    }
}