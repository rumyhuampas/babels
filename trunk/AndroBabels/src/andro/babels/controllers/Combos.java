package andro.babels.controllers;

import andro.babels.wrappers.ExtraObject;
import android.os.Bundle;
import babelsObjects.SalesItemsAdmin;

public class Combos extends andro.babels.controllers.Tab {

    private andro.babels.Combos Activity;
    private andro.babels.views.Combos view;
    private Bundle extras;
    private andro.babels.controllers.Pos posController;

    public Combos(andro.babels.Combos activity) {
        super(activity);
        Activity = activity;
        view = new andro.babels.views.Combos(activity);
        extras = Activity.getIntent().getExtras();
        posController = (andro.babels.controllers.Pos)((ExtraObject)extras.getParcelable("posController")).Obj[0];
        LoadInfo();
    }

    private void LoadInfo() {
        ExtraObject extraObj = (ExtraObject) extras.getParcelable("combos");
        for (int i = 0; i < extraObj.Obj.length; i++) {
            view.DrawObject((Object[]) extraObj.Obj[i], i, SalesItemsAdmin.IT_COMBO, posController.ObjectOnClickHandler, posController.ObjectOnLongClickHandler);
        }
    }
}
