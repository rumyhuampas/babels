package andro.babels.controllers;

import andro.babels.R;
import andro.babels.wrappers.AndroThread;
import andro.babels.wrappers.BabelsSettings;
import andro.babels.wrappers.ExtraObject;
import andro.babels.wrappers.dialogs.YesNoDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import babelsObjects.MySQL;
import java.sql.SQLException;

public class Welcome extends andro.babels.controllers.Base {

    private andro.babels.Welcome Activity;
    private andro.babels.views.Welcome view;
    private andro.babels.models.Welcome model;
    public static MySQL mysql;
    public static andro.babels.wrappers.BabelsSettings settings;
    private boolean SettingsPressed;

    public Welcome(andro.babels.Welcome activity) {
        Activity = activity;
        view = new andro.babels.views.Welcome(activity);
        model = new andro.babels.models.Welcome();
        settings = new andro.babels.wrappers.BabelsSettings(activity);
        SettingsPressed = false;
        String url = settings.GetAppSetting(andro.babels.wrappers.BabelsSettings.URLKEY, andro.babels.wrappers.BabelsSettings.URLDEFAULT);
        String db = settings.GetAppSetting(andro.babels.wrappers.BabelsSettings.DBKEY, andro.babels.wrappers.BabelsSettings.DBDEFAULT);
        String user = settings.GetAppSetting(andro.babels.wrappers.BabelsSettings.USERKEY, andro.babels.wrappers.BabelsSettings.USERDEFAULT);
        String pass = settings.GetAppSetting(andro.babels.wrappers.BabelsSettings.PASSKEY, andro.babels.wrappers.BabelsSettings.PASSDEFAULT);
        mysql = new MySQL(url, db, user, pass);
        LoadAppSettings();
        LoadInfo();
    }

    public void LoadAppSettings() {
        if (settings.GetAppSetting(BabelsSettings.URLKEY, "").equals("")) {
            settings.SaveAppSetting(BabelsSettings.URLKEY, BabelsSettings.URLDEFAULT);
        }
        if (settings.GetAppSetting(BabelsSettings.DBKEY, "").equals("")) {
            settings.SaveAppSetting(BabelsSettings.DBKEY, BabelsSettings.DBDEFAULT);
        }
        if (settings.GetAppSetting(BabelsSettings.USERKEY, "").equals("")) {
            settings.SaveAppSetting(BabelsSettings.USERKEY, BabelsSettings.USERDEFAULT);
        }
        if (settings.GetAppSetting(BabelsSettings.PASSKEY, "").equals("")) {
            settings.SaveAppSetting(BabelsSettings.PASSKEY, BabelsSettings.PASSDEFAULT);
        }
        if (settings.GetAppSetting(BabelsSettings.ITEMTEXTSIZEKEY, "").equals("")) {
            settings.SaveAppSetting(BabelsSettings.ITEMTEXTSIZEKEY, BabelsSettings.ITEMTEXTSIZEDEFAULT);
        }
        if (settings.GetAppSetting(BabelsSettings.TITLETEXTSIZEKEY, "").equals("")) {
            settings.SaveAppSetting(BabelsSettings.TITLETEXTSIZEKEY, BabelsSettings.TITLETEXTSIZEDEFAULT);
        }
        if (settings.GetAppSetting(BabelsSettings.SALEITEMTEXTSIZEKEY, "").equals("")) {
            settings.SaveAppSetting(BabelsSettings.SALEITEMTEXTSIZEKEY, BabelsSettings.SALEITEMTEXTSIZEDEFAULT);
        }
    }

    private void LoadInfo() {
        AndroThread thread = new AndroThread(mysql, model, "LoadInfo", null, null, Object[].class, null, LoadInfoHandler, ExceptionHandler);
        thread.Start();
    }
    private Handler LoadInfoHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (SettingsPressed == false) {
                super.handleMessage(msg);
                
                Object[] message = (Object[]) msg.obj;
                Object[] info = (Object[])message[0];
                Object[] combos = (Object[]) info[0];
                Object[] products = (Object[]) info[1];
                ExtraObject extraCombos = new ExtraObject(combos);
                ExtraObject extraProducts = new ExtraObject(products);
                Bundle extras = new Bundle();
                extras.putParcelable("combos", extraCombos);
                extras.putParcelable("products", extraProducts);
                andro.babels.controllers.Base.RunActivity(Activity, andro.babels.Pos.class, extras);
            }
        }
    };
    private Handler ExceptionHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (SettingsPressed == false) {
                super.handleMessage(msg);
                YesNoDialog dialog = view.CreateYesNoMessage(Activity, "Error", "Un problema ocurrió al iniciar el programa.\n\ns "
                        + ((SQLException) msg.obj).getMessage()
                        + "\n\n¿Desea ir a la ventana de configuración?");
                dialog.SetCallback(new View.OnClickListener() {

                    public void onClick(View v) {
                        if (((Button) v).getText() == YesNoDialog.BUTTON_YES) {
                            Bundle extras = new Bundle();
                            extras.putString("activity", "WELCOME");
                            andro.babels.controllers.Base.RunActivity(Activity, andro.babels.Settings.class, extras);
                        } else {
                            Activity.finish();
                        }
                    }
                });
                dialog.show();
            }
        }
    };

    public boolean HandleMenuSelection(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.lm_miSett:
                SettingsPressed = true;
                Bundle extras = new Bundle();
                extras.putString("activity", "WELCOME");
                andro.babels.controllers.Base.RunActivity(Activity, andro.babels.Settings.class, extras);
                return true;
            default:
                return false;
        }
    }
}
