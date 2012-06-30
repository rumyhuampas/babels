package andro.babels.controllers;

import andro.babels.wrappers.AndroThread;
import andro.babels.wrappers.dialogs.ImageDialog;
import andro.babels.wrappers.dialogs.LoadingDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.io.InputStream;
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
        final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Cargar item", "Cargando...");
        loadDialog.show();
        
        Object[] info = new Object[2];
        info[0] = itemType;
        info[1] = loadDialog;
        
        AndroThread thread = new AndroThread(andro.babels.controllers.Welcome.mysql,
                model, "GetItem", new Class[]{int.class, String.class}, new Object[]{itemID, itemType},
                Object.class, info, LoadItemHandler, ExceptionHandler);
        thread.Start();
    }
    
    private Handler LoadItemHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object[] message = (Object[]) msg.obj;
            Object item = (Object)message[0];
            Object[] info = (Object[])message[1];
            
            view.DrawItem((String)info[0], item);
            LoadingDialog loadDialog = (LoadingDialog) info[1];
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
