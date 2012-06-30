package andro.babels.views;

import andro.babels.R;
import andro.babels.wrappers.BabelsSettings;
import andro.babels.wrappers.SaleList;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Pos extends andro.babels.views.Base {

    private andro.babels.Pos Activity;
    private int TextSize;

    public Pos(andro.babels.Pos activity) {
        Activity = activity;
        TextSize = Integer.parseInt(andro.babels.controllers.Welcome.settings.GetAppSetting(BabelsSettings.TITLETEXTSIZEKEY, BabelsSettings.TITLETEXTSIZEDEFAULT));
        GetDoneButton().setTextSize(TextSize);
        GetSaleListTitle().setTextSize(TextSize);
    }

    public void InitTabHost() {
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
        tv.setTextSize(TextSize);
        tv.setText(title);
        return view;
    }

    public Button GetDoneButton() {
        return (Button) Activity.findViewById(R.id.pos_btnDone);
    }

    public TextView GetSaleListTitle() {
        return (TextView) Activity.findViewById(R.id.pos_saleListTitle);
    }

    public void RefreshSaleList(SaleList saleList, OnClickListener SaleItemOnClickHandler, OnLongClickListener SaleItemOnLongClickHandler) {
        LinearLayout llMain = (LinearLayout) Activity.findViewById(R.id.pos_saleList);
        llMain.removeAllViews();
        if (saleList.GetGeneralList().size() > 0) {
            saleList.DrawSaleList(Activity, llMain, SaleItemOnClickHandler, SaleItemOnLongClickHandler);
            SetDoneButtonEnabled(true);
            SetSaleListVisibility(LinearLayout.VISIBLE);
        } else {
            SetDoneButtonEnabled(false);
            SetSaleListVisibility(LinearLayout.INVISIBLE);
        }
    }

    private void SetDoneButtonEnabled(boolean value) {
        ((Button) Activity.findViewById(R.id.pos_btnDone)).setEnabled(value);
    }

    private void SetSaleListVisibility(int value) {
        ((LinearLayout) Activity.findViewById(R.id.pos_saleList)).setVisibility(value);
    }

    public void SetSaleTotal(String value) {
        TextView txtTotal = (TextView) Activity.findViewById(R.id.pos_txtTotal);
        txtTotal.setTextSize(TextSize);
        txtTotal.setText("Total: $" + value);
    }

    public int GetObjectId(View objView) {
        TextView itemId = (TextView) ((LinearLayout) objView).getChildAt(0);
        return Integer.parseInt(itemId.getText().toString());
    }

    public String GetObjectName(View objView) {
        TextView itemName = (TextView) ((LinearLayout) objView).getChildAt(1);
        return itemName.getText().toString();
    }

    public String GetObjectPrice(View objView) {
        TextView itemPrice = (TextView) ((LinearLayout) objView).getChildAt(2);
        return itemPrice.getText().toString();
    }

    public String GetObjectType(View objView) {
        TextView itemType = (TextView) ((LinearLayout) objView).getChildAt(3);
        return itemType.getText().toString();
    }

    public int GetSaleItemId(View saleItemView) {
        TextView itemId = (TextView) ((LinearLayout) saleItemView).getChildAt(0);
        return Integer.parseInt(itemId.getText().toString());
    }

    public String GetSaleItemType(View saleItemView) {
        TextView itemId = (TextView) ((LinearLayout) saleItemView).getChildAt(4);
        return itemId.getText().toString();
    }
}
