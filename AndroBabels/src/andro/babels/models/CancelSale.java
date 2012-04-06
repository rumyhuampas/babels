package andro.babels.models;

import andro.babels.wrappers.SaleList;
import babelsObjects.*;
import java.sql.SQLException;

public class CancelSale extends andro.babels.models.Base {

    private Sale CurrentSale;

    public void Search(SaleList saleList, String search) throws SQLException {
        saleList.ClearSalelist();

        Cancelation cancel = new Cancelation(andro.babels.controllers.Welcome.mysql.Conn);
        cancel.LoadByCanceled(Integer.parseInt(search));
        if (cancel.getId() != -1) {
            saleList.AddSaleItem(cancel.getId(), "CANCELATION", cancel.CancelerSale.Total, "CANCELATION");
        }

        CurrentSale = null;
        CurrentSale = new Sale(andro.babels.controllers.Welcome.mysql.Conn);
        if (CurrentSale.Load(Integer.parseInt(search))) {
            for (int i = 0; i < CurrentSale.Items.size(); i++) {
                if (((Object[]) CurrentSale.Items.get(i))[0] == SalesItemsAdmin.IT_COMBO) {
                    Combo combo = ((Combo) ((Object[]) CurrentSale.Items.get(i))[1]);
                    saleList.AddSaleItem(combo.getId(), combo.Name, combo.Price, SalesItemsAdmin.IT_COMBO);
                } else {
                    Product prod = ((Product) ((Object[]) CurrentSale.Items.get(i))[1]);
                    saleList.AddSaleItem(prod.getId(), prod.Name, prod.Price, SalesItemsAdmin.IT_PROD);
                }
            }
        }
    }

    public void CancelSale() throws SQLException {
        Sale cancelSale = new Sale(andro.babels.controllers.Welcome.mysql.Conn);
        cancelSale.Total = -CurrentSale.Total;
        cancelSale.Type = CurrentSale.Type;
        cancelSale.Save();
        Cancelation cancelation = new Cancelation(andro.babels.controllers.Welcome.mysql.Conn);
        cancelation.CancelerSale = cancelSale;
        cancelation.CanceledSale = CurrentSale;
        cancelation.Save();
    }
}
