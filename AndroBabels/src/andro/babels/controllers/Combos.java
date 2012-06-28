package andro.babels.controllers;

import andro.babels.wrappers.ExtraObject;
import andro.babels.wrappers.dialogs.ImageDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import babelsObjects.Combo;
import babelsObjects.SalesItemsAdmin;
import java.sql.SQLException;

public class Combos extends andro.babels.controllers.Tab {

    private andro.babels.Combos Activity;
    private andro.babels.views.Combos view;
    private Bundle extras;
    private andro.babels.controllers.Pos posController;

    public Combos(andro.babels.Combos activity) {
        super(activity);
        Activity = activity;
        view = new andro.babels.views.Combos(activity);
        extras = Activity.getIntent().getExtras();
        posController = (andro.babels.controllers.Pos)((ExtraObject)extras.getParcelable("posController")).Obj[0];
        LoadInfo();
    }

    private void LoadInfo() {
        ExtraObject extraObj = (ExtraObject) extras.getParcelable("combos");
        for (int i = 0; i < extraObj.Obj.length; i++) {
            //view.DrawObject((Object[]) extraObj.Obj[i], i, SalesItemsAdmin.IT_COMBO, posController.ObjectOnClickHandler, posController.ObjectOnLongClickHandler);
            
            Object[] obj = (Object[]) extraObj.Obj[i];
            Object[] comboObj = new Object[5];

            comboObj[0] = obj[0]; //ID
            comboObj[1] = obj[1]; //NAME
            comboObj[2] = obj[2]; //DESC
            comboObj[3] = obj[3]; //PRICE
            LinearLayout llObj = view.DrawObject(comboObj, i, SalesItemsAdmin.IT_COMBO, posController.ObjectOnClickHandler, posController.ObjectOnLongClickHandler);
            GetComboImage(comboObj, llObj);
        }
    }
    
    private void GetComboImage(final Object[] comboObj, final LinearLayout llObj) {
        Thread thread = new Thread(new Runnable() {

            public void run() {
                try {
                    Welcome.mysql.Open();
                    try {
                        Combo combo = new Combo(Welcome.mysql.Conn);
                        combo.Load((Integer) comboObj[0]);
                        comboObj[4] = combo.ImageStream;
                        Object[] info = new Object[2];
                        info[0] = llObj;
                        info[1] = comboObj;
                        Message msg = LoadInfoHandler.obtainMessage(1, info);
                        LoadInfoHandler.sendMessage(msg);

                    } catch (SQLException ex) {
                        Message msg = ExceptionHandler.obtainMessage(1, ex);
                        ExceptionHandler.sendMessage(msg);
                    } finally {
                        Welcome.mysql.Close();
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
            super.handleMessage(msg);
            Object[] info = (Object[]) msg.obj;
            LinearLayout llObj = (LinearLayout) info[0];
            Object[] combo = (Object[]) info[1];
            if (combo[4] != null){
                view.AddImageToObject(llObj, combo);
            }
        }
    };
    private Handler ExceptionHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ImageDialog dialog = view.CreateErrorMessage(Activity, ((SQLException) msg.obj).getMessage());
            dialog.SetCallback(andro.babels.wrappers.dialogs.Base.closeAppViewCallback);
            dialog.show();
        }
    };
}
