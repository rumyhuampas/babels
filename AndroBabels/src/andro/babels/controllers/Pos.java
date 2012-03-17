package andro.babels.controllers;

import andro.babels.Combos;
import andro.babels.Products;
import andro.babels.R;
import andro.babels.wrappers.ExtraObject;
import andro.babels.wrappers.dialogs.ImageDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Pos extends andro.babels.controllers.Base {
    
    private andro.babels.Pos Activity;
    private andro.babels.views.Pos view;
    private andro.babels.models.Pos model;
    
    public Pos(andro.babels.Pos activity){
        Activity = activity;
        view = new andro.babels.views.Pos(activity);
        CreateTabs();
    }
    
    private void CreateTabs(){
        view.InitTabHost();
        
        Bundle extras = Activity.getIntent().getExtras();
        Object[] posController = new Object[1];
        posController[0] = this;
        ExtraObject objects = new ExtraObject(posController);
        extras.putParcelable("posController", objects);
        Intent combosIntent = new Intent().setClass(Activity, Combos.class);
        combosIntent.putExtras(extras);
        view.CreateTab(combosIntent, "tabCombos", "Combos");
        
        Intent productsIntent = new Intent().setClass(Activity, Products.class);
        view.CreateTab(productsIntent, "tabProducts", "Products");
    }
    
    public OnClickListener ComboOnClickHandler = new OnClickListener(){

        public void onClick(View comboView) {
            model.AddSaleItem(view.GetComboId(comboView), view.GetComboName(comboView), view.GetComboPrice(comboView));
            view.RefreshSaleList(model.GetSaleList());
        }        
    };
    
    /*private void LoadInfo(){
        Bundle extras = Activity.getIntent().getExtras();
        ExtraObject extraObj = (ExtraObject) extras.getParcelable("combos");
        //for (int i = 0; i< extraObj.Obj.length; i++){
        //    ImageDialog d = new ImageDialog(Activity, "TEST", ((Object[])extraObj.Obj[i])[1].toString(), R.drawable.error, null);
        //    d.show();
        //}
    }*/
}
