package andro.babels;

import android.app.Activity;
import android.os.Bundle;

public class Clients extends Activity {
    
    private andro.babels.controllers.Clients controller;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.clients);
        
        controller = new andro.babels.controllers.Clients(this);
    }
}
