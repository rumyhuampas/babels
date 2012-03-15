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
                row.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, 20));
            }
            /*
             * row.setLayoutParams(new LayoutParams( LayoutParams.FILL_PARENT,
                              LayoutParams.WRAP_CONTENT));
             */
            row.addView(GetComboView(combo));
            if (index % 5 == 0) {
                tl.addView(row);//, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
            }
        } catch (Exception ex) {
            //ShowError(Activity, "Error", ex.getMessage(), null);
        }
    }

    private Button GetComboView(Object[] combo) {
        Button btn = new Button(Activity);
        LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, 20);
        params.weight = 1;
        btn.setLayoutParams(params);
        btn.setLines(1);
        btn.setTextSize(8);
        btn.setEllipsize(TextUtils.TruncateAt.END);
        btn.setText(combo[1].toString());
        return btn;
    }
}
