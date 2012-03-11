package andro.babels.views;

import andro.babels.R;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Pos extends andro.babels.views.Base {

    private andro.babels.Pos Activity;

    public Pos(andro.babels.Pos activity) {
        Activity = activity;
    }

    public void CreateTab(Intent content, String tabName, String tabTitle) {
        TabHost tabHost = (TabHost) Activity.findViewById(android.R.id.tabhost);
        TabSpec spec = tabHost.newTabSpec(tabName).setIndicator(tabTitle);
        View view = prepareTabView(tabTitle);
        spec.setIndicator(view);
        spec.setContent(content);
        tabHost.addTab(spec);
    }

    private View prepareTabView(String title) {
        View view = LayoutInflater.from(Activity).inflate(R.layout.tabs_bg, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);
        tv.setText(title);
        return view;
    }
}
