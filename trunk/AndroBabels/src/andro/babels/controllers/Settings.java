package andro.babels.controllers;

import android.os.Bundle;

public class Settings extends andro.babels.controllers.Base {

    private andro.babels.Settings Activity;

    public Settings(andro.babels.Settings activity) {
        Activity = activity;
    }

    public void CheckCaller() {
        Bundle extras = Activity.getIntent().getExtras();
        final String caller = extras.getString("activity");
        if (caller.equals("POS")) {
            Activity.finish();
        } else {
            RunActivity(Activity, andro.babels.Welcome.class, null);
        }
    }
}