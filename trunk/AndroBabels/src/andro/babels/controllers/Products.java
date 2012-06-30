package andro.babels.controllers;

import andro.babels.wrappers.AndroThread;
import andro.babels.wrappers.ExtraObject;
import andro.babels.wrappers.dialogs.ImageDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import babelsObjects.SalesItemsAdmin;
import java.io.InputStream;
import java.sql.SQLException;

public class Products extends andro.babels.controllers.Tab {

    private andro.babels.Products Activity;
    private andro.babels.views.Products view;
    private andro.babels.models.Products model;
    private Bundle extras;
    private andro.babels.controllers.Pos posController;

    public Products(andro.babels.Products activity) {
        super(activity);
        Activity = activity;
        view = new andro.babels.views.Products(activity);
        model = new andro.babels.models.Products();
        extras = Activity.getIntent().getExtras();
        posController = (andro.babels.controllers.Pos) ((ExtraObject) extras.getParcelable("posController")).Obj[0];
        LoadInfo();
    }

    private void LoadInfo() {
        ExtraObject extraObj = (ExtraObject) extras.getParcelable("products");
        for (int i = 0; i < extraObj.Obj.length; i++) {
            Object[] obj = (Object[]) extraObj.Obj[i];
            Object[] prodObj = new Object[5];

            prodObj[0] = obj[0]; //ID
            prodObj[1] = obj[1]; //NAME
            prodObj[2] = obj[2]; //DESC
            prodObj[3] = obj[3]; //PRICE
            LinearLayout llObj = view.DrawObject(prodObj, i, SalesItemsAdmin.IT_PROD, posController.ObjectOnClickHandler, posController.ObjectOnLongClickHandler);
            GetProductImage(prodObj, llObj);
        }
    }

    private void GetProductImage(final Object[] prodObj, final LinearLayout llObj) {
        Object[] info = new Object[2];
        info[0] = llObj;
        info[1] = prodObj;
        
        AndroThread thread = new AndroThread(andro.babels.controllers.Welcome.mysql,
                model, "GetProductImage", new Class[]{Integer.class}, new Object[]{prodObj[0]},
                InputStream.class, info, LoadProductHandler, ExceptionHandler);
        thread.Start();
    }
    private Handler LoadProductHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object[] message = (Object[]) msg.obj;
            Object[] info = (Object[])message[1];
            
            Object[] prodObj = (Object[])info[1];
            prodObj[4] = (InputStream)message[0];
            
            LinearLayout llObj = (LinearLayout) info[0];
            if (prodObj[4] != null) {
                view.AddImageToObject(llObj, prodObj);
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
