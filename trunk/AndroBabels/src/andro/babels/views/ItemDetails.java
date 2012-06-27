package andro.babels.views;

import andro.babels.R;
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

    public ItemDetails(andro.babels.ItemDetails activity) {
        Activity = activity;
    }

    public void DrawItem(String itemType, Object item) {
        ImageView img = (ImageView) Activity.findViewById(R.id.id_imgLogo);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(80, 80);
        img.setLayoutParams(params);
        TextView txtTitle = (TextView) Activity.findViewById(R.id.id_txtTitle);
        TextView txtName = (TextView) Activity.findViewById(R.id.id_txtName);
        TextView txtDesc = (TextView) Activity.findViewById(R.id.id_txtDesc);
        TextView txtPrice = (TextView) Activity.findViewById(R.id.id_txtPrice);
        if (itemType.equals(SalesItemsAdmin.IT_COMBO)) {
            Combo combo = (Combo) item;
            //img.setImageBitmap(BitmapFactory.decodeStream(combo.ImageStream));
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