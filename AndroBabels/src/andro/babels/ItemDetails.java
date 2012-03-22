package andro.babels;

import android.app.Activity;
import android.os.Bundle;

public class ItemDetails extends Activity {

    private andro.babels.controllers.ItemDetails controller;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.itemdetails);

        controller = new andro.babels.controllers.ItemDetails(this);
    }
}
