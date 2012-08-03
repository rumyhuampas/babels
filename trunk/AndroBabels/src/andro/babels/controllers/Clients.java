package andro.babels.controllers;

import android.view.View;

public class Clients extends andro.babels.controllers.Base {
    
    private andro.babels.Clients Activity;
    private andro.babels.views.Clients view;
    
    public Clients(andro.babels.Clients activity) {
        Activity = activity;
        view = new andro.babels.views.Clients(activity);
        SetListeners();
    }
    
    public void SetListeners() {
        SetCancelButtonListener();
    }
    
    private void SetCancelButtonListener() {
        view.GetCancelButton().setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Activity.finish();
            }
        });
    }
}
