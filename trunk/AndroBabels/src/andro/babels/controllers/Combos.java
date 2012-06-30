package andro.babels.controllers;

import andro.babels.wrappers.AndroThread;
import andro.babels.wrappers.ExtraObject;
import andro.babels.wrappers.SaleList;
import andro.babels.wrappers.dialogs.ImageDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import babelsObjects.Combo;
import babelsObjects.SalesItemsAdmin;
import java.io.InputStream;
import java.sql.SQLException;

public class Combos extends andro.babels.controllers.Tab {

    private andro.babels.Combos Activity;
    private andro.babels.views.Combos view;
    private andro.babels.models.Combos model;
    private Bundle extras;
    private andro.babels.controllers.Pos posController;

    public Combos(andro.babels.Combos activity) {
        super(activity);
        Activity = activity;
        view = new andro.babels.views.Combos(activity);
        model = new andro.babels.models.Combos();
        extras = Activity.getIntent().getExtras();
        posController = (andro.babels.controllers.Pos) ((ExtraObject) extras.getParcelable("posController")).Obj[0];
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
        Object[] info = new Object[2];
        info[0] = llObj;
        info[1] = comboObj;
        
        AndroThread thread = new AndroThread(andro.babels.controllers.Welcome.mysql,
                model, "LoadCombo", new Class[]{Integer.class}, new Object[]{comboObj[0]},
                InputStream.class, info, LoadComboHandler, ExceptionHandler);
        thread.Start();
    }

    private Handler LoadComboHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            Object[] message = (Object[]) msg.obj;
            Object[] info = (Object[])message[1];
            
            Object[] comboObj = (Object[])info[1];
            comboObj[4] = (InputStream)message[0];
            
            LinearLayout llObj = (LinearLayout) info[0];
            if (comboObj[4] != null) {
                view.AddImageToObject(llObj, comboObj);
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
