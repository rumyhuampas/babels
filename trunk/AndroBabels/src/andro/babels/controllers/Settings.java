package andro.babels.controllers;

import andro.babels.wrappers.dialogs.ImageDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Settings extends andro.babels.controllers.Base {

    private andro.babels.Settings Activity;
    private andro.babels.views.Settings view;

    public Settings(andro.babels.Settings activity) {
        Activity = activity;
        view = new andro.babels.views.Settings(activity);
    }

    public void CheckCaller() {
        Bundle extras = Activity.getIntent().getExtras();
        final String caller = extras.getString("activity");
        if (caller.equals("POS")) {
            Activity.finish();
        } else {
            //RunActivity(Activity, andro.babels.Welcome.class, null);
            ImageDialog dialog = view.CreateInfoMessage(Activity, "La aplicacion se cerrara para guardar los cambios.");
            dialog.SetCallback(new OnClickListener(){

                public void onClick(View v) {
                    Activity.finish();
                }
            });
            dialog.show();
        }
    }
}