package andro.babels;

import andro.babels.wrappers.AndroThread;
import andro.babels.wrappers.SaleList;
import andro.babels.wrappers.dialogs.LoadingDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Pos extends TabActivity {

    private andro.babels.controllers.Pos controller;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pos);
        
        controller = new andro.babels.controllers.Pos(this);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return controller.HandleMenuSelection(item);
    }
    
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
         if (requestCode == 1) {
             if (resultCode == RESULT_OK) {
                 controller.SaveSaleInternal("VENTA_A");
             }
         }
     }
}
