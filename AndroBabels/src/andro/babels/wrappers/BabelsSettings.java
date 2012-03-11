package andro.babels.wrappers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class BabelsSettings {
    
    private andro.babels.Welcome Activity;
    
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    public static final String URLKEY = "URLPref";
    public static final String URLDEFAULT = "localhost:3306";
    public static final String DBKEY = "DBPref";
    public static final String DBDEFAULT = "babels";
    public static final String USERKEY = "USERPref";
    public static final String USERDEFAULT = "root";
    public static final String PASSKEY = "PASSPref";
    public static final String PASSDEFAULT = "";
    
    public BabelsSettings(andro.babels.Welcome activity){
        Activity = activity;
        settings = PreferenceManager.getDefaultSharedPreferences(Activity);
    }
    
    public void SaveAppSetting(String key, String value){
        editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }
    
    public String GetAppSetting(String key, String defaultValue){
        return settings.getString(key, defaultValue);
    }
}