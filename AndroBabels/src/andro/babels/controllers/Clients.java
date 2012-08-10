package andro.babels.controllers;

import android.content.Intent;
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
        SetOKButtonListener();
        SetCancelButtonListener();
    }
    
    private void SetOKButtonListener() {
        view.GetOKButton().setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent= Activity.getIntent();
                intent.putExtra("client", view.GetSearchText());
                Activity.setResult(Activity.RESULT_OK, intent);
                Activity.finish();
            }
        });
    }
    
    private void SetCancelButtonListener() {
        view.GetCancelButton().setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Activity.setResult(Activity.RESULT_CANCELED);
                Activity.finish();
            }
        });
    }
}
