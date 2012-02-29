package andro.babels;

import android.app.Activity;
import android.os.Bundle;

public class Welcome extends Activity {

    private andro.babels.controllers.Welcome controller;
            
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.welcome);
        
        controller = new andro.babels.controllers.Welcome(this);
    }
}
