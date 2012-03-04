package andro.babels.views;

import android.content.Intent;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Pos extends andro.babels.views.Base {
     
    private andro.babels.Pos Activity;
    
    public Pos(andro.babels.Pos activity){
        Activity = activity;
    }
    
    public void CreateTab(Intent content, String tabName, String tabTitle) {
        TabHost tabHost = (TabHost)Activity.findViewById(android.R.id.tabhost);
        TabSpec spec = tabHost.newTabSpec(tabName).setIndicator(tabTitle);
        spec.setContent(content);
        tabHost.addTab(spec);
    }
}
