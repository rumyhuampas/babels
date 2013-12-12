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
            saleList.AddSaleItem(cancel.getId(), "CANCELACION", cancel.CancellerMove.Amount, "CANCELACION");
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

    public void CancelSale(String ticketNumber) throws SQLException {
        Sale cancelSale = new Sale(andro.babels.controllers.Welcome.mysql.Conn);
        cancelSale.Amount = -CurrentSale.Amount;
        cancelSale.Type = new MovementTypes(andro.babels.controllers.Welcome.mysql.Conn);
        cancelSale.Type.Load("CANCELACION");
        cancelSale.Save();
        Cancelation cancelation = new Cancelation(andro.babels.controllers.Welcome.mysql.Conn);
        cancelation.CancellerMove = cancelSale;
        cancelation.CanceledMove = CurrentSale;
        cancelation.SetTicketNumber(ticketNumber);
        cancelation.Save();
        
        SavePrintJob(cancelSale);
    }
    
    private void SavePrintJob(Sale sale) throws SQLException{
        Print print = new Print(andro.babels.controllers.Welcome.mysql.Conn);
        //print.Sale = sale;
        print.Move = sale;
        print.Printer = "FISCAL";
        print.Save();
    }
}
