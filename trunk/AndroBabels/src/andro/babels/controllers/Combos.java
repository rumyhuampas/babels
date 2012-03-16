package andro.babels.controllers;

import andro.babels.wrappers.ExtraObject;
import andro.babels.wrappers.dialogs.YesNoDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Combos extends andro.babels.controllers.Base {

    private andro.babels.Combos Activity;
    private andro.babels.views.Combos view;
    private Bundle extras;
    private andro.babels.controllers.Pos posController;

    public Combos(andro.babels.Combos activity) {
        Activity = activity;
        view = new andro.babels.views.Combos(activity);
        extras = Activity.getIntent().getExtras();
        posController = (andro.babels.controllers.Pos)((ExtraObject)extras.getParcelable("posController")).Obj[0];
        LoadInfo();
    }

    private void LoadInfo() {
        ExtraObject extraObj = (ExtraObject) extras.getParcelable("combos");
        for (int i = 0; i < extraObj.Obj.length; i++) {
            view.DrawCombo((Object[]) extraObj.Obj[i], i, posController.ComboOnClickHandler);
            /*
             * ImageDialog d = new ImageDialog(Activity, "TEST",
             * ((Object[])extraObj.Obj[i])[1].toString(), R.drawable.error,
             * null);
            d.show();
             */
        }
    }
    
    private void ComboOnClick(){
        
    }

    public void Exit() {
        final YesNoDialog dialog = view.CreateYesNoMessage(Activity, "Exit?", "Are you sure?");
        dialog.SetCallback(new View.OnClickListener() {

            public void onClick(View view) {
                if (((Button) view).getText() == YesNoDialog.BUTTON_YES) {
                    Activity.finish();
                } else {
                    dialog.hide();
                }
            }
        });
        dialog.show();
    }
}
