package andro.babels.views;

import andro.babels.R;
import andro.babels.wrappers.BabelsSettings;
import andro.babels.wrappers.SaleList;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CancelSale extends andro.babels.views.Base {
    private andro.babels.CancelSale Activity;
    private int TextSize;

    public CancelSale(andro.babels.CancelSale activity) {
        Activity = activity;
        TextSize = Integer.parseInt(andro.babels.controllers.Welcome.settings.GetAppSetting(BabelsSettings.TITLETEXTSIZEKEY, BabelsSettings.TITLETEXTSIZEDEFAULT));
        GetSearchButton().setTextSize(TextSize);
        GetCancelButton().setTextSize(TextSize);
        GetSearchLabel().setTextSize(TextSize);
        GetSaleLabel().setTextSize(TextSize);
        GetTotalLabel().setTextSize(TextSize);
        GetSearchTextBox().setTextSize(TextSize);
    }
    
    public TextView GetSearchLabel(){
        return (TextView)Activity.findViewById(R.id.cs_lblSearch);
    }
     
    public Button GetSearchButton(){
        return (Button)Activity.findViewById(R.id.cs_btnSearch);
    }
    
    public TextView GetSaleLabel(){
        return (TextView)Activity.findViewById(R.id.cs_lblSale);
    }
    
    public TextView GetTotalLabel(){
        return (TextView)Activity.findViewById(R.id.cs_lblTotal);
    }
    
    public Button GetCancelButton(){
        return (Button)Activity.findViewById(R.id.cs_btnCancelSale);
    }
    
    public TextView GetSearchTextBox(){
        return (TextView)Activity.findViewById(R.id.cs_txtSearch);
    }
    
    public String GetSearchText(){
        return ((TextView)Activity.findViewById(R.id.cs_txtSearch)).getText().toString();
    }
    
    public void RefreshSaleList(SaleList saleList){
        LinearLayout llMain = (LinearLayout)Activity.findViewById(R.id.cs_saleList);
        llMain.removeAllViews();
        if (saleList.GetGeneralList().size() > 0){
            saleList.DrawSaleList(Activity, llMain, null, null);
            SetSaleListVisibility(LinearLayout.VISIBLE);
            SetCancelButtonEnabled(true);
            if(saleList.GetCancelationList().size() > 0){
                SetCancelButtonEnabled(false);
            }
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
        TextView txtTotal = (TextView) Activity.findViewById(R.id.cs_lblTotal);
        txtTotal.setText("Total: $" + value);
    }
}
