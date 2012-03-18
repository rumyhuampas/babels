package andro.babels.models;

import java.util.ArrayList;
import java.util.List;

public class Pos extends andro.babels.models.Base {

    public class SaleItem {

        public int id;
        public String name;
        public float price;
    }
    
    private List<SaleItem> combos;
    private List<SaleItem> products;
    public List<SaleItem> GetComboList(){
        return combos;
    }
    public List<SaleItem> GetProductList(){
        return products;
    }
    public List GetGeneralList(){
        ArrayList general = new ArrayList();
        general.addAll(combos);
        general.addAll(products);
        return general;
    }
    
    public Pos(){
        combos = new ArrayList();
        products = new ArrayList();
    }

    public void AddSaleItem(List<SaleItem> list, int id, String name, float price) {
        SaleItem item = new SaleItem();
        item.id = id;
        item.name = name;
        item.price = price;
        list.add(item);
    }
    
    public void RemoveSaleItem(List<SaleItem> list, int saleItemId){
        for(int i=0;i<list.size();i++){
            if (list.get(i).id == saleItemId){
                list.remove(i);
                break;
            }
        }
    }
    
    public void ClearSalelist(List list){
        list.clear();
    }
    
    public float GetSaleTotal(){
        float total = 0;
        for (int i=0;i<combos.size();i++){
            total = total + combos.get(i).price;
        }
        for (int i=0;i<products.size();i++){
            total = total + products.get(i).price;
        }
        return total;
    }
}
