package andro.babels.views;

import andro.babels.R;
import andro.babels.models.Pos.SaleItem;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import java.util.List;

public class Pos extends andro.babels.views.Base {

    private andro.babels.Pos Activity;

    public Pos(andro.babels.Pos activity) {
        Activity = activity;
    }
    
    public void InitTabHost(){
        TabHost tabHost = (TabHost) Activity.findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);
    }

    public void CreateTab(Intent content, String tabName, String tabTitle) {
        TabHost tabHost = (TabHost) Activity.findViewById(android.R.id.tabhost);
        TabSpec spec = tabHost.newTabSpec(tabName).setIndicator(tabTitle);
        View view = prepareTabView(tabTitle);
        spec.setIndicator(view);
        spec.setContent(content);
        tabHost.addTab(spec);
    }

    private View prepareTabView(String title) {
        View view = LayoutInflater.from(Activity).inflate(R.layout.tabs_bg, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);
        tv.setTextSize(9);
        tv.setText(title);
        return view;
    }
    
    public Button GetDoneButton(){
        return (Button)Activity.findViewById(R.id.pos_btnDone);
    }
    
    public void RefreshSaleList(List<SaleItem> saleList, OnClickListener SaleItemOnClickHandler){
        LinearLayout llMain = (LinearLayout)Activity.findViewById(R.id.pos_saleList);
        llMain.removeAllViews();
        for (int i=0;i<saleList.size();i++){
           AddSaleItem(saleList.get(i), i+1, SaleItemOnClickHandler); 
        }
    }
    
    private void AddSaleItem(SaleItem item, int number, OnClickListener SaleItemOnClickHandler){
        LinearLayout ll = new LinearLayout(Activity);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.addView(CreateSaleId(item));
        ll.addView(CreateSaleValueView(String.valueOf(number) + ". ", false));
        ll.addView(CreateSaleValueView(item.name, true));
        ll.addView(CreateSaleValueView("$" + String.valueOf(item.price), false));
        ll.setOnClickListener(SaleItemOnClickHandler);
        LinearLayout llMain = (LinearLayout)Activity.findViewById(R.id.pos_saleList);
        llMain.addView(ll);
    }
    
    private TextView CreateSaleId(SaleItem item){
        TextView saleId = new TextView(Activity);
        saleId.setText(String.valueOf(item.id));
        saleId.setWidth(0);
        return saleId;
    }
    
    private LinearLayout CreateSaleValueView(String value, boolean fillParent){
        LinearLayout ll = new LinearLayout(Activity);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        if (fillParent == true){
            params.weight = 1;
        }
        ll.setLayoutParams(params);
        TextView saleValueView = new TextView(Activity);
        saleValueView.setText(value);
        ll.addView(saleValueView);
        return ll;
    }
    
    public void SetSaleTotal(String value){
        TextView txtTotal = (TextView) Activity.findViewById(R.id.pos_txtTotal);
        txtTotal.setText("Total: $" + value);
    }
    
    public int GetComboId(View comboView){
        TextView itemId = (TextView)((LinearLayout)comboView).getChildAt(1);
        return Integer.parseInt(itemId.getText().toString());
    }
    
    public String GetComboName(View comboView){
        TextView itemName = (TextView)((LinearLayout)comboView).getChildAt(2);
        return itemName.getText().toString();
    }
    
    public String GetComboPrice(View comboView){
        TextView itemPrice = (TextView)((LinearLayout)comboView).getChildAt(3);
        return itemPrice.getText().toString();
    }
    
    public int GetSaleItemId(View saleItemView){
        TextView itemId = (TextView)((LinearLayout)saleItemView).getChildAt(0);
        return Integer.parseInt(itemId.getText().toString());
    }
}
