package andro.babels.wrappers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class BabelsSettings {
    
    private andro.babels.Welcome Activity;
    
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;
    public static final String URLKEY = "URLPref";
    public static final String URLDEFAULT = "10.0.2.2:3306";
    public static final String DBKEY = "DBPref";
    public static final String DBDEFAULT = "babels";
    public static final String USERKEY = "USERPref";
    public static final String USERDEFAULT = "root";
    public static final String PASSKEY = "PASSPref";
    public static final String PASSDEFAULT = "";
    public static final String ITEMTEXTSIZEKEY = "ITEMTEXTSIZEPref";
    public static final String ITEMTEXTSIZEDEFAULT = "10";
    public static final String TITLETEXTSIZEKEY = "TITLETEXTSIZEPref";
    public static final String TITLETEXTSIZEDEFAULT = "10";
    public static final String SALEITEMTEXTSIZEKEY = "SALEITEMTEXTSIZEPref";
    public static final String SALEITEMTEXTSIZEDEFAULT = "10";
    public static final String ITEMSAMOUNTKEY = "ITEMSAMOUNTPref";
    public static final String ITEMSAMOUNTDEFAULT = "4";
    
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