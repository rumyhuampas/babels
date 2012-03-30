package andro.babels.models;

import android.os.Message;
import babelsObjects.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Pos extends andro.babels.models.Base {

    public class SaleItem {

        public int id;
        public String name;
        public float price;
        public String type;
    }
    private List<SaleItem> combos;
    private List<SaleItem> products;

    public List<SaleItem> GetComboList() {
        return combos;
    }

    public List<SaleItem> GetProductList() {
        return products;
    }

    public List GetGeneralList() {
        ArrayList general = new ArrayList();
        general.addAll(combos);
        general.addAll(products);
        return general;
    }

    public Pos() {
        combos = new ArrayList();
        products = new ArrayList();
    }

    public void AddSaleItem(int id, String name, float price, String type) {
        SaleItem item = new SaleItem();
        item.id = id;
        item.name = name;
        item.price = price;
        item.type = type;
        if (item.type == SalesItemsAdmin.IT_COMBO) {
            combos.add(item);
        } else {
            products.add(item);
        }
    }

    public void RemoveSaleItem(String type, int saleItemId) {
        List<SaleItem> list = combos;
        if (type == SalesItemsAdmin.IT_PROD) {
            list = products;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).id == saleItemId) {
                list.remove(i);
                break;
            }
        }
    }

    public void ClearSalelist() {
        combos.clear();
        products.clear();
    }

    public float GetSaleTotal() {
        float total = 0;
        for (int i = 0; i < combos.size(); i++) {
            total = total + combos.get(i).price;
        }
        for (int i = 0; i < products.size(); i++) {
            total = total + products.get(i).price;
        }
        return total;
    }

    public void SaveSale(String type) throws SQLException {
        Sale sale = new Sale(andro.babels.controllers.Welcome.mysql.Conn);
        sale.Total = GetSaleTotal();
        sale.Type = type;
        List<SaleItem> items = GetGeneralList();
        SaleItem saleItem;
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
