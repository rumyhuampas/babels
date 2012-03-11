package andro.babels.controllers;

import andro.babels.wrappers.ExtraObject;
import android.os.Bundle;

public class Combos extends andro.babels.controllers.Base {
    private andro.babels.Combos Activity;
    private andro.babels.views.Combos view;
    
    public Combos(andro.babels.Combos activity){
        Activity = activity;
        view = new andro.babels.views.Combos(activity);
        LoadInfo();
    }
    
    private void LoadInfo(){
        Bundle extras = Activity.getIntent().getExtras();
        ExtraObject extraObj = (ExtraObject) extras.getParcelable("combos");
        for (int i = 0; i< extraObj.Obj.length; i++){
            view.DrawCombo((Object[])extraObj.Obj[i]);
            /*ImageDialog d = new ImageDialog(Activity, "TEST", ((Object[])extraObj.Obj[i])[1].toString(), R.drawable.error, null);
            d.show();*/
        }
    }
}
