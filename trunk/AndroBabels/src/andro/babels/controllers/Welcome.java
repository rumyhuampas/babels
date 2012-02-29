package andro.babels.controllers;

public class Welcome extends andro.babels.controllers.Base {
    
    private andro.babels.Welcome Activity;
    
    public Welcome(andro.babels.Welcome activity){
        Activity = activity;
        
        RunActivity(Activity, andro.babels.Pos.class, null);
    }
}
