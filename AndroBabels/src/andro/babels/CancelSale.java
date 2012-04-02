package andro.babels;

import android.app.Activity;
import android.os.Bundle;

public class CancelSale extends Activity {
    
    private andro.babels.controllers.CancelSale controller;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.cancelsale);
        
        controller = new andro.babels.controllers.CancelSale(this);
    }
}
