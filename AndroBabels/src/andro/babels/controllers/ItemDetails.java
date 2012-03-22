package andro.babels.controllers;

import andro.babels.wrappers.dialogs.ImageDialog;
import andro.babels.wrappers.dialogs.LoadingDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.sql.SQLException;

public class ItemDetails extends andro.babels.controllers.Base {

    private andro.babels.ItemDetails Activity;
    private andro.babels.models.ItemDetails model;
    private andro.babels.views.ItemDetails view;

    public ItemDetails(andro.babels.ItemDetails activity) {
        Activity = activity;
        model = new andro.babels.models.ItemDetails();
        view = new andro.babels.views.ItemDetails(activity);
        LoadItem();
    }
    
    private void LoadItem() {
        Bundle extras = Activity.getIntent().getExtras();
        final int itemID = extras.getInt("ItemID");
        final String itemType = extras.getString("ItemType");
        final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Get Item", "Loading...");
        loadDialog.show();
        Thread thread = new Thread(new Runnable() {

            public void run() {
                try {
                    andro.babels.controllers.Welcome.mysql.Open();
                    try {
                        Object[] resp = new Object[3];
                        resp[0] = itemType;
                        resp[1] = model.GetItem(itemID, itemType);
                        resp[2] = loadDialog;
                        Message msg = LoadItemHandler.obtainMessage(1, resp);
                        LoadItemHandler.sendMessage(msg);

                    } finally {
                        andro.babels.controllers.Welcome.mysql.Close();
                    }
                } catch (Exception ex) {
                    Message msg = ExceptionHandler.obtainMessage(1, ex);
                    ExceptionHandler.sendMessage(msg);
                }
            }
        });
        thread.start();
    }
    
    private Handler LoadItemHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object[] resp = (Object[]) msg.obj;
            view.DrawItem((String)resp[0], (Object)resp[1]);
            LoadingDialog loadDialog = (LoadingDialog) resp[2];
            loadDialog.hide();
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
