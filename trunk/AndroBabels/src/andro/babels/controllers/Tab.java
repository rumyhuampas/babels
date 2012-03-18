package andro.babels.controllers;

import andro.babels.wrappers.dialogs.YesNoDialog;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Tab extends andro.babels.controllers.Base {
    private Activity Activity;
    public Tab(Activity activity){
        Activity = activity;
    }
    
    public void Exit() {
        final YesNoDialog dialog = andro.babels.views.Base.CreateYesNoMessage(Activity, "Exit?", "Are you sure?");
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
