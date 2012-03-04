package andro.babels;

import android.app.TabActivity;
import android.os.Bundle;

public class Pos extends TabActivity {

    private andro.babels.controllers.Pos controller;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pos);
        
        controller = new andro.babels.controllers.Pos(this);
    }
}
