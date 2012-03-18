package andro.babels;

import android.app.Activity;
import android.os.Bundle;

public class Products extends Activity {
    
    private andro.babels.controllers.Products controller;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.products);
        
        controller = new andro.babels.controllers.Products(this);
    }
    
    @Override
    public void onBackPressed(){
        controller.Exit();
        return;
    }
}
