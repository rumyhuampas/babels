package andro.babels.views;

import andro.babels.R;
import andro.babels.wrappers.SaleList;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CancelSale extends andro.babels.views.Base {
    private andro.babels.CancelSale Activity;

    public CancelSale(andro.babels.CancelSale activity) {
        Activity = activity;
    }
     
    public Button GetSearchButton(){
        return (Button)Activity.findViewById(R.id.cs_btnSearch);
    }
    
    public Button GetCancelButton(){
        return (Button)Activity.findViewById(R.id.cs_btnCancelSale);
    }
    
    public String GetSearchText(){
        return ((TextView)Activity.findViewById(R.id.cs_txtSearch)).getText().toString();
    }
    
    public void RefreshSaleList(SaleList saleList){
        LinearLayout llMain = (LinearLayout)Activity.findViewById(R.id.cs_saleList);
        llMain.removeAllViews();
        if (saleList.GetGeneralList().size() > 0){
            saleList.DrawSaleList(Activity, llMain, null, null);
            SetCancelButtonEnabled(true);
            SetSaleListVisibility(LinearLayout.VISIBLE);
        }
        else{
            SetCancelButtonEnabled(false);
            SetSaleListVisibility(LinearLayout.INVISIBLE);
        }
    }
    
    private void SetCancelButtonEnabled(boolean value){
        ((Button)Activity.findViewById(R.id.cs_btnCancelSale)).setEnabled(value);
    }
    
    private void SetSaleListVisibility(int value){
        ((LinearLayout)Activity.findViewById(R.id.cs_saleList)).setVisibility(value);
    }
    
    public void SetSaleTotal(String value){
        TextView txtTotal = (TextView) Activity.findViewById(R.id.cs_txtTotal);
        txtTotal.setText("Total: $" + value);
    }
}
