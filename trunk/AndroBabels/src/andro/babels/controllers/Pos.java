package andro.babels.controllers;

import andro.babels.Combos;
import android.content.Intent;

public class Pos extends andro.babels.controllers.Base {
    
    private andro.babels.Pos Activity;
    private andro.babels.views.Pos view;
    
    public Pos(andro.babels.Pos activity){
        Activity = activity;
        view = new andro.babels.views.Pos(activity);
        CreateTabs();
    }
    
    private void CreateTabs(){
        Intent intent = new Intent().setClass(Activity, Combos.class);
        view.CreateTab(intent, "tabCombos", "Combos");
    }
}
