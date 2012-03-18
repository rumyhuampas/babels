package andro.babels.views;

import andro.babels.R;
import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Tab extends andro.babels.views.Base {
    private Activity Activity;
    private LinearLayout ll;
    private LinearLayout row;

    public Tab(Activity activity, int llMainId) {
        Activity = activity;
        ll = (LinearLayout) Activity.findViewById(llMainId);
    }

    public void DrawObject(Object[] obj, int index, View.OnClickListener onClickHandler) {
        try {
            if (index % 4 == 0) {
                row = new LinearLayout(Activity);
                row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                ll.addView(row);
            }
            row.addView(CreateObjectView(obj, onClickHandler));
        } catch (Exception ex) {
            //ShowError(Activity, "Error", ex.getMessage(), null);
        }
    }

    private LinearLayout CreateObjectView(Object[] obj, View.OnClickListener onClickHandler) {
        LinearLayout ObjectView = new LinearLayout(Activity);
        ObjectView.setOrientation(LinearLayout.VERTICAL);
        ObjectView.setBackgroundResource(R.drawable.border);
        ObjectView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        params.setMargins(2, 2, 2, 2);
        ObjectView.setLayoutParams(params);
        ObjectView.addView(CreateObjectImage(obj));
        ObjectView.addView(CreateObjectId(obj));
        ObjectView.addView(CreateObjectTitle(obj));
        ObjectView.addView(CreateObjectPrice(obj));
        ObjectView.setOnClickListener(onClickHandler);
        return ObjectView;
    }
    
    private ImageView CreateObjectImage(Object[] obj){
        ImageView img = new ImageView(Activity);
        img.setImageBitmap(null);
        return img;
    }
    
    private TextView CreateObjectId(Object[] obj){
        TextView lblId = new TextView(Activity);
        lblId.setWidth(0);
        lblId.setHeight(0);
        lblId.setText(obj[0].toString());
        lblId.setVisibility(LinearLayout.INVISIBLE);
        return lblId;
    }
    
    private TextView CreateObjectTitle(Object[] obj){
        TextView lblTitle = new TextView(Activity);
        lblTitle.setLines(1);
        lblTitle.setTextSize(8);
        lblTitle.setGravity(Gravity.CENTER);
        lblTitle.setEllipsize(TextUtils.TruncateAt.END);
        lblTitle.setText(obj[1].toString());
        return lblTitle;
    }
    
    private TextView CreateObjectPrice(Object[] obj){
        TextView lblPrice = new TextView(Activity);
        lblPrice.setLines(1);
        lblPrice.setTextSize(8);
        lblPrice.setGravity(Gravity.CENTER);
        lblPrice.setEllipsize(TextUtils.TruncateAt.END);
        lblPrice.setText("$ " + obj[3].toString());
        return lblPrice;
    }
}
