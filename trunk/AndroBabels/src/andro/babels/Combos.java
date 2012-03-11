package andro.babels;

import android.app.Activity;
import android.os.Bundle;

public class Combos extends Activity {
    
    private andro.babels.controllers.Combos controller;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.combos);
        
        controller = new andro.babels.controllers.Combos(this);
    }
}
