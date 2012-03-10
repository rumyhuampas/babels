package andro.babels.controllers;

import andro.babels.wrappers.ExtraObject;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import babelsObjects.MySQL;
import babelsObjects.CombosAdmin;
import java.sql.SQLException;

public class Welcome extends andro.babels.controllers.Base {

    private andro.babels.Welcome Activity;
    private andro.babels.views.Welcome view;
    public static MySQL mysql = new MySQL();

    ;
    
    public Welcome(andro.babels.Welcome activity) {
        Activity = activity;
        view = new andro.babels.views.Welcome(activity);
        GetInfo();
    }

    private void GetInfo() {
        Thread thread = new Thread(new Runnable() {

            public void run() {
                try {
                    mysql.Open();
                    try {
                        Object[] result = CombosAdmin.GetAllCombos(mysql.Conn);
                        Message msg = getInfoHandler.obtainMessage(1, result);
                        getInfoHandler.sendMessage(msg);
                    } finally {
                        mysql.Close();
                    }
                } catch (SQLException ex) {
                    Message msg = getInfoExceptionHandler.obtainMessage(1, ex);
                    getInfoExceptionHandler.sendMessage(msg);
                }
            }
        });
        thread.start();
    }
    private Handler getInfoHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object[] result = (Object[]) msg.obj;
            Bundle extras = new Bundle();
            ExtraObject objects = new ExtraObject(result);
            extras.putParcelable("combos", objects);
            andro.babels.controllers.Base.RunActivity(Activity, andro.babels.Pos.class, extras);
        }
    };
    private Handler getInfoExceptionHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            view.ShowToast(Activity, ((SQLException)msg.obj).getMessage());
        }
    };
}
