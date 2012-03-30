package andro.babels.controllers;

import android.os.Bundle;

public class Settings extends andro.babels.controllers.Base {
    private andro.babels.Settings Activity;

    public Settings(andro.babels.Settings activity) {
        Activity = activity;
    }
    
    public void LaunchWelcome(){
        RunActivity(Activity, andro.babels.Welcome.class, Bundle.EMPTY);
    }
}