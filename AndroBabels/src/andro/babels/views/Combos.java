package andro.babels.views;

import andro.babels.R;
import andro.babels.wrappers.GifImageView;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.LinearLayout.LayoutParams;

public class Combos extends andro.babels.views.Base {

    private andro.babels.Combos Activity;
    private LinearLayout ll;
    private LinearLayout row;

    public Combos(andro.babels.Combos activity) {
        Activity = activity;
        ll = (LinearLayout) Activity.findViewById(R.id.cmb_llMain);
    }

    public void DrawCombo(Object[] combo, int index, OnClickListener onClickHandler) {
        try {
            if (index % 4 == 0) {
                row = new LinearLayout(Activity);
                row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
                ll.addView(row);
            }
            row.addView(GetComboView(combo, onClickHandler));
        } catch (Exception ex) {
            //ShowError(Activity, "Error", ex.getMessage(), null);
        }
    }

    private LinearLayout GetComboView(Object[] combo, OnClickListener onClickHandler) {
        LinearLayout comboView = new LinearLayout(Activity);
        comboView.setOrientation(LinearLayout.VERTICAL);
        comboView.setBackgroundResource(R.drawable.border);
        comboView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        params.setMargins(2, 2, 2, 2);
        comboView.setLayoutParams(params);
        comboView.addView(CreateComboImage(combo));
        comboView.addView(CreateComboId(combo));
        comboView.addView(CreateComboTitle(combo));
        comboView.addView(CreateComboPrice(combo));
        comboView.setOnClickListener(onClickHandler);
        return comboView;
    }
    
    private ImageView CreateComboImage(Object[] combo){
        ImageView img = new ImageView(Activity);
        img.setImageBitmap(null);
        return img;
    }
    
    private TextView CreateComboId(Object[] combo){
        TextView lblId = new TextView(Activity);
        lblId.setWidth(0);
        lblId.setHeight(0);
        lblId.setText(combo[0].toString());
        lblId.setVisibility(LinearLayout.INVISIBLE);
        return lblId;
    }
    
    private TextView CreateComboTitle(Object[] combo){
        TextView lblTitle = new TextView(Activity);
        lblTitle.setLines(1);
        lblTitle.setTextSize(8);
        lblTitle.setGravity(Gravity.CENTER);
        lblTitle.setEllipsize(TextUtils.TruncateAt.END);
        lblTitle.setText(combo[1].toString());
        return lblTitle;
    }
    
    private TextView CreateComboPrice(Object[] combo){
        TextView lblPrice = new TextView(Activity);
        lblPrice.setLines(1);
        lblPrice.setTextSize(8);
        lblPrice.setGravity(Gravity.CENTER);
        lblPrice.setEllipsize(TextUtils.TruncateAt.END);
        lblPrice.setText("$ " + combo[3].toString());
        return lblPrice;
    }
}
