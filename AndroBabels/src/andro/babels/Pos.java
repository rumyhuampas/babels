package andro.babels;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class Pos extends Activity 
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pos);
        
        
        Resources res = getResources(); // Resource object to get Drawables
        
        
        TabHost tabs = (TabHost)findViewById(R.id.ps_tabhost);
        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.ps_tabCombos);
        spec.setIndicator("",
                res.getDrawable(R.drawable.tabitem));
        tabs.addTab(spec);
        
        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.ps_tabProducts);
        spec.setIndicator("",
                res.getDrawable(R.drawable.tabitem));
        tabs.addTab(spec);
        
        
       /* TabHost tabHost = (TabHost) findViewById(R.id.tabhost);  // The activity TabHost
        
        tabHost.setup();
        
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, Welcome.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("artists").setIndicator("Artists",
                        res.getDrawable(R.drawable.tabitem))
                    .setContent(intent);
        tabHost.addTab(spec);*/

        // Do the same for the other tabs
        /*intent = new Intent().setClass(this, AlbumsActivity.class);
        spec = tabHost.newTabSpec("albums").setIndicator("Albums",
                        res.getDrawable(R.drawable.ic_tab_albums))
                    .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, SongsActivity.class);
        spec = tabHost.newTabSpec("songs").setIndicator("Songs",
                        res.getDrawable(R.drawable.ic_tab_songs))
                    .setContent(intent);
        tabHost.addTab(spec);*/

        tabs.setCurrentTab(0);
    }
}
