package andro.babels.controllers;

import andro.babels.Combos;
import andro.babels.R;
import andro.babels.wrappers.ExtraObjects;
import andro.babels.wrappers.dialogs.ImageDialog;
import android.content.Intent;
import android.os.Bundle;

public class Pos extends andro.babels.controllers.Base {
    
    private andro.babels.Pos Activity;
    private andro.babels.views.Pos view;
    
    public Pos(andro.babels.Pos activity){
        Activity = activity;
        view = new andro.babels.views.Pos(activity);
        CreateTabs();
        LoadInfo();
    }
    
    private void CreateTabs(){
        Intent intent = new Intent().setClass(Activity, Combos.class);
        view.CreateTab(intent, "tabCombos", "Combos");
    }
    
    private void LoadInfo(){
        Bundle extras = Activity.getIntent().getExtras();
        ExtraObjects extraObj = (ExtraObjects) extras.getParcelable("combos");
        for (int i = 0; i< extraObj.Objects.length; i++){
            ImageDialog d = new ImageDialog();
            d.show(Activity, "TEST", ((Object[])extraObj.Objects[i])[1].toString(), R.drawable.error, null);
        }
    }
}
