package andro.babels.controllers;

import andro.babels.R;
import andro.babels.wrappers.BabelsSettings;
import andro.babels.wrappers.ExtraObject;
import andro.babels.wrappers.dialogs.YesNoDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import babelsObjects.CombosAdmin;
import babelsObjects.MySQL;
import babelsObjects.ProductsAdmin;
import java.sql.SQLException;

public class Welcome extends andro.babels.controllers.Base {

    private andro.babels.Welcome Activity;
    private andro.babels.views.Welcome view;
    public static MySQL mysql;
    public static andro.babels.wrappers.BabelsSettings settings;
    private boolean SettingsPressed;

    public Welcome(andro.babels.Welcome activity) {
        Activity = activity;
        view = new andro.babels.views.Welcome(activity);
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
    }

    private void LoadInfo() {
        Thread thread = new Thread(new Runnable() {

            public void run() {
                try {
                    mysql.Open();
                    try {
                        Object[] info = new Object[2];
                        Object[] combos = CombosAdmin.GetAllCombos(mysql.Conn);
                        Object[] products = ProductsAdmin.GetAllProducts(mysql.Conn);
                        if (combos != null && products != null) {
                            info[0] = combos;
                            info[1] = products;
                            Message msg = LoadInfoHandler.obtainMessage(1, info);
                            LoadInfoHandler.sendMessage(msg);
                        } else {
                            throw new Exception("No se pudo obtener la información de la base de datos");
                        }
                    } finally {
                        mysql.Close();
                    }
                } catch (Exception ex) {
                    Message msg = ExceptionHandler.obtainMessage(1, ex);
                    ExceptionHandler.sendMessage(msg);
                }
            }
        });
        thread.start();
    }
    private Handler LoadInfoHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (SettingsPressed == false) {
                super.handleMessage(msg);
                Object[] info = (Object[]) msg.obj;
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
