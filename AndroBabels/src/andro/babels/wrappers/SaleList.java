package andro.babels.wrappers;

import andro.babels.R;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import babelsObjects.SalesItemsAdmin;
import java.util.ArrayList;
import java.util.List;

public class SaleList {

    public class SaleItem {

        public int id;
        public String name;
        public float price;
        public String type;
        public int ammount;
    }
    private List<SaleItem> combos;
    private List<SaleItem> products;
    private List<SaleItem> cancelations;
    private int TextSize;

    public List<SaleItem> GetComboList() {
        return combos;
    }

    public List<SaleItem> GetProductList() {
        return products;
    }

    public List<SaleItem> GetCancelationList() {
        return cancelations;
    }

    public List GetGeneralList() {
        ArrayList general = new ArrayList();
        general.addAll(combos);
        general.addAll(products);
        return general;
    }

    public SaleList() {
        combos = new ArrayList();
        products = new ArrayList();
        cancelations = new ArrayList();
        TextSize = Integer.parseInt(andro.babels.controllers.Welcome.settings.GetAppSetting(BabelsSettings.SALEITEMTEXTSIZEKEY, BabelsSettings.SALEITEMTEXTSIZEDEFAULT));
    }

    private SaleItem GetProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (((SaleItem) products.get(i)).id == id) {
                return (SaleItem) products.get(i);
            }
        }
        return null;
    }

    private SaleItem GetCombo(int id) {
        for (int i = 0; i < combos.size(); i++) {
            if (((SaleItem) combos.get(i)).id == id) {
                return (SaleItem) combos.get(i);
            }
        }
        return null;
    }

    public void AddSaleItem(int id, String name, float price, String type) {
        boolean addNew = true;
        if (type.equals(SalesItemsAdmin.IT_COMBO)) {
            SaleItem item = GetCombo(id);
            if (item != null) {
                item.ammount++;
                addNew = false;
            }
        } else {
            if (type.equals(SalesItemsAdmin.IT_PROD)) {
                SaleItem item = GetProduct(id);
                if (item != null) {
                    item.ammount++;
                    addNew = false;
                }
            }
        }
        if (addNew == true) {
            SaleItem item = new SaleItem();
            item.id = id;
            item.name = name;
            item.price = price;
            item.type = type;
            item.ammount = 1;
            if (item.type.equals(SalesItemsAdmin.IT_COMBO)) {
                combos.add(item);
            } else {
                if (item.type.equals(SalesItemsAdmin.IT_PROD)) {
                    products.add(item);
                } else {
                    cancelations.add(item);
                }
            }
        }
    }

    public void RemoveSaleItem(String type, int saleItemId) {
        List<SaleItem> list = combos;
        if (type.equals(SalesItemsAdmin.IT_PROD)) {
            list = products;
        } else {
            list = combos;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).id == saleItemId) {
                list.get(i).ammount--;
                if (list.get(i).ammount == 0) {
                    list.remove(i);
                }
                break;
            }
        }
    }

    public void ClearSalelist() {
        combos.clear();
        products.clear();
        cancelations.clear();
    }

    public float GetSaleTotal() {
        float total = 0;
        for (int i = 0; i < combos.size(); i++) {
            total = total + (combos.get(i).price * combos.get(i).ammount);
        }
        for (int i = 0; i < products.size(); i++) {
            total = total + (products.get(i).price * products.get(i).ammount);
        }
        return total;
    }

    public void DrawSaleList(Context context, LinearLayout llMain, View.OnClickListener SaleItemOnClickHandler, View.OnLongClickListener SaleItemOnLongClickHandler) {
        List<SaleItem> saleList = GetGeneralList();
        for (int i = 0; i < saleList.size(); i++) {
            llMain.addView(CreateSaleItem(context, saleList.get(i), i + 1, SaleItemOnClickHandler, SaleItemOnLongClickHandler));
        }
    }

    private LinearLayout CreateSaleItem(Context context, SaleItem item, int number, View.OnClickListener SaleItemOnClickHandler, View.OnLongClickListener SaleItemOnLongClickHandler) {
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.addView(CreateSaleId(context, item));
        ll.addView(CreateSaleValueView(context, String.valueOf(number) + ". ", false));
        ll.addView(CreateSaleValueView(context, item.name, true));
        ll.addView(CreateSaleValueView(context, String.valueOf(item.ammount), false));
        ll.addView(CreateSaleType(context, item));
        ll.setOnClickListener(SaleItemOnClickHandler);
        ll.setOnLongClickListener(SaleItemOnLongClickHandler);
        return ll;
    }

    private TextView CreateSaleId(Context context, SaleItem item) {
        TextView saleId = new TextView(context);
        saleId.setText(String.valueOf(item.id));
        //saleId.setHeight(0);
        saleId.setWidth(0);
        return saleId;
    }

    private LinearLayout CreateSaleValueView(Context context, String value, boolean fillParent) {
        LinearLayout ll = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
        if (fillParent == true) {
            params.weight = 1;
        }
        ll.setLayoutParams(params);
        TextView saleValueView = new TextView(context);
        saleValueView.setTextSize(TextSize);
        saleValueView.setTextColor(Color.WHITE);
        saleValueView.setText(value);
        ll.addView(saleValueView);
        return ll;
    }

    private TextView CreateSaleType(Context context, SaleItem item) {
        TextView saleType = new TextView(context);
        saleType.setText(item.type);
        saleType.setHeight(0);
        saleType.setWidth(0);
        return saleType;
    }
}
