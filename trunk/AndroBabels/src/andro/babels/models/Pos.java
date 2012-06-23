package andro.babels.models;

import andro.babels.wrappers.SaleList;
import andro.babels.wrappers.SaleList.SaleItem;
import babelsObjects.*;
import java.sql.SQLException;
import java.util.List;

public class Pos extends andro.babels.models.Base {

    public void SaveSale(SaleList saleList, String type) throws SQLException {
        Sale sale = new Sale(andro.babels.controllers.Welcome.mysql.Conn);
        sale.Amount = saleList.GetSaleTotal();
        sale.Type = new MovementTypes(andro.babels.controllers.Welcome.mysql.Conn);
        sale.Type.Load(type);
        List<SaleList.SaleItem> items = saleList.GetGeneralList();
        SaleList.SaleItem saleItem;
        for (int i = 0; i < items.size(); i++) {
            saleItem = items.get(i);
            for (int j = 0; j < saleItem.ammount; j++) {
                Object[] item = new Object[2];
                item[0] = saleItem.type;
                if (saleItem.type.equals(SalesItemsAdmin.IT_COMBO)) {
                    Combo combo = new Combo(andro.babels.controllers.Welcome.mysql.Conn);
                    combo.Load(saleItem.id);
                    item[1] = combo;
                } else {
                    Product prod = new Product(andro.babels.controllers.Welcome.mysql.Conn);
                    prod.Load(saleItem.id);
                    item[1] = prod;
                }
                sale.Items.add(item);
            }
        }
        sale.Save();
        SavePrint(sale);
    }

    private void SavePrint(Sale sale) throws SQLException {
        Print print = new Print(andro.babels.controllers.Welcome.mysql.Conn);
        print.Sale = sale;
        if (sale.Type.Name.equals(Sale.TYPE_A) || sale.Type.Name.equals(Sale.TYPE_B)) {
            print.Printer = "FISCAL";
        } else {
            if (sale.Type.Name.equals(Sale.TYPE_X)) {
                print.Printer = "NOFISCAL";
            }
        }
        if (print.Save() == true) {
            for (int i = 0; i < sale.Items.size(); i++) {
                Object[] item = (Object[]) sale.Items.get(i);
                if(item[0].equals(SalesItemsAdmin.IT_COMBO)){
                    Combo combo = (Combo)item[1];
                    for (int j = 0; j < combo.Products.size(); j++) {
                        Product prod = (Product)combo.Products.get(j);
                        CreateProdPrint(prod, sale);
                    }
                }
                else{
                    CreateProdPrint((Product)item[1], sale);
                }
            }
        }
    }
    
    private void CreateProdPrint(Product prod, Sale sale) throws SQLException{
        Print printKitchen = new Print(andro.babels.controllers.Welcome.mysql.Conn);
        printKitchen.Sale = sale;
        printKitchen.Printer = "COCINA_" + prod.Idkitchen;
        printKitchen.Save();
    }
    
    public boolean IsCashOpen() throws SQLException{
        return MovementAdmin.IsCashOpen(andro.babels.controllers.Welcome.mysql.Conn);
    }
}
