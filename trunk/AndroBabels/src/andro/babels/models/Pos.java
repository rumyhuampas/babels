package andro.babels.models;

import java.util.ArrayList;
import java.util.List;

public class Pos extends andro.babels.models.Base {

    public class SaleItem {

        public int id;
        public String name;
        public float price;
    }
    
    private List<SaleItem> saleList;
    public List<SaleItem> GetSaleList(){
        return saleList;
    }
    
    public Pos(){
        saleList = new ArrayList();
    }

    public void AddSaleItem(int id, String name, float price) {
        SaleItem item = new SaleItem();
        item.id = id;
        item.name = name;
        item.price = price;
        saleList.add(item);
    }
    
    public void RemoveSaleItem(int saleItemId){
        for(int i=0;i<saleList.size();i++){
            if (saleList.get(i).id == saleItemId){
                saleList.remove(i);
                break;
            }
        }
    }
    
    public void ClearSalelist(){
        saleList.clear();
    }
    
    public float GetSaleTotal(){
        float total = 0;
        for (int i=0;i<saleList.size();i++){
            total = total + saleList.get(i).price;
        }
        return total;
    }
}
