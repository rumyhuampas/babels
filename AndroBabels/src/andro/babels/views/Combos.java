package andro.babels.views;

import andro.babels.R;
import andro.babels.wrappers.GifImageView;
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
            if (index % 5 == 0) {
                row = new LinearLayout(Activity);
                row.setBackgroundResource(R.drawable.border);
                row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
                ll.addView(row);
            }
            row.addView(GetComboView(combo, onClickHandler));
        } catch (Exception ex) {
            //ShowError(Activity, "Error", ex.getMessage(), null);
        }
    }

    private Button GetComboView(Object[] combo, OnClickListener onClickHandler) {
        Button btn = new Button(Activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        btn.setLayoutParams(params);
        btn.setLines(1);
        btn.setTextSize(8);
        btn.setEllipsize(TextUtils.TruncateAt.END);
        btn.setText(combo[1].toString());
        btn.setOnClickListener(onClickHandler);
        return btn;
    }
}
