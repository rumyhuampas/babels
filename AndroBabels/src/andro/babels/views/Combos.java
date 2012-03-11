package andro.babels.views;

import andro.babels.R;
import andro.babels.wrappers.GifImageView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Combos extends andro.babels.views.Base {

    private andro.babels.Combos Activity;

    public Combos(andro.babels.Combos activity) {
        Activity = activity;
    }

    public void DrawCombo(Object[] combo){
        try{
            LinearLayout ll = (LinearLayout) Activity.findViewById(R.id.cmb_llMain);
                        
            LinearLayout llRow = new LinearLayout(Activity);
            llRow.setOrientation(LinearLayout.HORIZONTAL);
            llRow.setBackgroundResource(R.drawable.border);
            llRow.addView(GetComboView(combo));
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(2, 2, 2, 2);
            llRow.setLayoutParams(params);
            
            ll.addView(llRow);
        }
        catch (Exception ex){
            //ShowError(Activity, "Error", ex.getMessage(), null);
        }
    }
    
    private TextView GetComboView(Object[] combo){
        TextView txt = new TextView(Activity);
        txt.setText(combo[1].toString());
        return txt;
    }
}
