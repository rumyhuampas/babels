package andro.babels.views;

import andro.babels.R;
import andro.babels.wrappers.GifImageView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import android.widget.LinearLayout.LayoutParams;

public class Combos extends andro.babels.views.Base {

    private andro.babels.Combos Activity;
    private TableLayout tl;
    private TableRow row;

    public Combos(andro.babels.Combos activity) {
        Activity = activity;
        tl = (TableLayout) Activity.findViewById(R.id.cmb_tlMain);
    }

    public void DrawCombo(Object[] combo, int index) {
        try {
            if (index % 5 == 0) {
                row = new TableRow(Activity);
                row.setBackgroundResource(R.drawable.border);
                LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.setMargins(2, 2, 2, 2);
                row.setLayoutParams(params);
            }
            /*
             * row.setLayoutParams(new LayoutParams( LayoutParams.FILL_PARENT,
                              LayoutParams.WRAP_CONTENT));
             */
            row.addView(GetComboView(combo)); 
            if (index / 5 == 0) {
                tl.addView(row, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            }
        } catch (Exception ex) {
            //ShowError(Activity, "Error", ex.getMessage(), null);
        }
    }

    private Button GetComboView(Object[] combo) {
        Button btn = new Button(Activity);
        btn.setWidth(80);
        btn.setHeight(10);
        btn.setTextSize(9);
        btn.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        btn.setText(combo[1].toString());
        return btn;
    }
}
