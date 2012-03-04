package andro.babels.views;

import andro.babels.R;
import andro.babels.wrappers.GifImageView;
import android.view.Gravity;
import android.widget.LinearLayout;
import java.io.InputStream;

public class Welcome extends andro.babels.views.Base {
    private andro.babels.Welcome Activity;
    
    public Welcome(andro.babels.Welcome activity){
        Activity = activity;
        LoadLoadingIcon();
    }
    
    private void LoadLoadingIcon(){
        try{
            LinearLayout ll = (LinearLayout) Activity.findViewById(R.id.wl_llLoading);
                        
            LinearLayout llRow = new LinearLayout(Activity);
            llRow.setOrientation(LinearLayout.HORIZONTAL);
            llRow.setGravity(Gravity.RIGHT);
            
            GifImageView loadingGif = new GifImageView(Activity, R.drawable.loading);
            llRow.addView(loadingGif);
            
            ll.addView(llRow, 57, 25);
        }
        catch (Exception ex){
            //ShowError(Activity, "Error", ex.getMessage(), null);
        }
    }
}
