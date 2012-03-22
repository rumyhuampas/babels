package andro.babels;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Settings extends PreferenceActivity {

    private andro.babels.controllers.Settings controller;
    
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.settings);
        
        controller = new andro.babels.controllers.Settings(this);
    }
    
    @Override
    public void onBackPressed(){
        controller.LaunchWelcome();
        return;
    }
}
