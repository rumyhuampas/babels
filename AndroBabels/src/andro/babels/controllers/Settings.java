package andro.babels.controllers;

import andro.babels.wrappers.dialogs.YesNoDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

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