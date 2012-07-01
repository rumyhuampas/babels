package andro.babels.views;

import andro.babels.R;
import andro.babels.wrappers.BabelsSettings;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import babelsObjects.Combo;
import babelsObjects.Product;
import babelsObjects.SalesItemsAdmin;
import java.io.InputStream;

public class ItemDetails extends andro.babels.views.Base {

    private andro.babels.ItemDetails Activity;
    private int TextSize;

    public ItemDetails(andro.babels.ItemDetails activity) {
        Activity = activity;
        TextSize = Integer.parseInt(andro.babels.controllers.Welcome.settings.GetAppSetting(BabelsSettings.TITLETEXTSIZEKEY, BabelsSettings.TITLETEXTSIZEDEFAULT));
        GetNameLabel().setTextSize(TextSize);
        GetDescLabel().setTextSize(TextSize);
        GetPriceLabel().setTextSize(TextSize);
    }
    
    public TextView GetNameLabel(){
        return (TextView)Activity.findViewById(R.id.id_lblName);
    }
    
    public TextView GetDescLabel(){
        return (TextView)Activity.findViewById(R.id.id_lblDesc);
    }
    
    public TextView GetPriceLabel(){
        return (TextView)Activity.findViewById(R.id.id_lblPrice);
    }

    public void DrawItem(String itemType, Object item) {
        ImageView img = (ImageView) Activity.findViewById(R.id.id_imgLogo);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
        img.setLayoutParams(params);
        TextView txtTitle = (TextView) Activity.findViewById(R.id.id_txtTitle);
        txtTitle.setTextSize(TextSize);
        TextView txtName = (TextView) Activity.findViewById(R.id.id_txtName);
        txtName.setTextSize(TextSize);
        TextView txtDesc = (TextView) Activity.findViewById(R.id.id_txtDesc);
        txtDesc.setTextSize(TextSize);
        TextView txtPrice = (TextView) Activity.findViewById(R.id.id_txtPrice);
        txtPrice.setTextSize(TextSize);
        if (itemType.equals(SalesItemsAdmin.IT_COMBO)) {
            Combo combo = (Combo) item;
            img.setImageBitmap(BitmapFactory.decodeStream(combo.ImageStream));
            txtTitle.setText(combo.Name);
            txtName.setText(combo.Name);
            txtDesc.setText(combo.Desc);
            txtPrice.setText("$" + String.valueOf(combo.Price));
        }
        else{
            Product prod = (Product) item;
            img.setImageBitmap(BitmapFactory.decodeStream(prod.ImageStream));
            txtTitle.setText(prod.Name);
            txtName.setText(prod.Name);
            txtDesc.setText(prod.Desc);
            txtPrice.setText("$" + String.valueOf(prod.Price));
        }
    }
}
