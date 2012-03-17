package andro.babels.models;

import java.util.ArrayList;
import java.util.List;

public class Pos extends andro.babels.models.Base {

    public class SaleItem {

        public int id;
        public String name;
        public String price;
    }
    private List<SaleItem> saleList;
    public List<SaleItem> GetSaleList(){
        return saleList;
    }

    public void AddSaleItem(int id, String name, String price) {
        if (saleList == null) {
            saleList = new ArrayList();
        }
        SaleItem item = new SaleItem();
        item.id = id;
        item.name = name;
        item.price = price;
        saleList.add(item);
    }
    
    public void RemoveSaleItem(int saleItemId){
        saleList.remove(saleItemId);
    }
    
    public void ClearSalelist(){
        saleList.clear();
    }
}
