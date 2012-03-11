package andro.babels.controllers;

import andro.babels.wrappers.ExtraObject;
import andro.babels.wrappers.dialogs.YesNoDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Combos extends andro.babels.controllers.Base {

    private andro.babels.Combos Activity;
    private andro.babels.views.Combos view;

    public Combos(andro.babels.Combos activity) {
        Activity = activity;
        view = new andro.babels.views.Combos(activity);
        LoadInfo();
    }

    private void LoadInfo() {
        Bundle extras = Activity.getIntent().getExtras();
        ExtraObject extraObj = (ExtraObject) extras.getParcelable("combos");
        for (int i = 0; i < extraObj.Obj.length; i++) {
            view.DrawCombo((Object[]) extraObj.Obj[i]);
            /*
             * ImageDialog d = new ImageDialog(Activity, "TEST",
             * ((Object[])extraObj.Obj[i])[1].toString(), R.drawable.error,
             * null);
            d.show();
             */
        }
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
