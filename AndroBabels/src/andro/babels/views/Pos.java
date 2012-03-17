package andro.babels.views;

import andro.babels.R;
import andro.babels.models.Pos.SaleItem;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    
    public void InitTabHost(){
        TabHost tabHost = (TabHost) Activity.findViewById(android.R.id.tabhost);
        tabHost.setup();
        tabHost.getTabWidget().setDividerDrawable(R.drawable.tab_divider);
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
    
    public void RefreshSaleList(List<SaleItem> saleList){
        LinearLayout llMain = (LinearLayout)Activity.findViewById(R.id.pos_saleList);
        llMain.removeAllViews();
        for (int i=0;i<saleList.size();i++){
           AddSaleItem(saleList.get(i)); 
        }
    }
    
    private void AddSaleItem(SaleItem item){
        LinearLayout ll = new LinearLayout(Activity);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        TextView saleTitle = new TextView(Activity);
        LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        saleTitle.setLayoutParams(params);
        saleTitle.setText(item.name);
        TextView salePrice = new TextView(Activity);
        salePrice.setLayoutParams(params);
        salePrice.setGravity(Gravity.RIGHT);
        salePrice.setText(item.price);
        ll.addView(saleTitle);
        ll.addView(salePrice);
        LinearLayout llMain = (LinearLayout)Activity.findViewById(R.id.pos_saleList);
        llMain.addView(ll);
    }
}
