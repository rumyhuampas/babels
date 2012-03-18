package andro.babels.controllers;

import andro.babels.wrappers.ExtraObject;
import android.os.Bundle;

public class Products extends andro.babels.controllers.Tab {
    private andro.babels.Products Activity;
    private andro.babels.views.Products view;
    private Bundle extras;
    private andro.babels.controllers.Pos posController;

    public Products(andro.babels.Products activity) {
        super(activity);
        Activity = activity;
        view = new andro.babels.views.Products(activity);
        extras = Activity.getIntent().getExtras();
        posController = (andro.babels.controllers.Pos)((ExtraObject)extras.getParcelable("posController")).Obj[0];
        LoadInfo();
    }
    
    private void LoadInfo() {
        ExtraObject extraObj = (ExtraObject) extras.getParcelable("products");
        for (int i = 0; i < extraObj.Obj.length; i++) {
            view.DrawObject((Object[]) extraObj.Obj[i], i, posController.ComboOnClickHandler);
        }
    }
}
