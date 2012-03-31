package andro.babels.controllers;

import andro.babels.wrappers.ExtraObject;
import andro.babels.wrappers.dialogs.ImageDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import babelsObjects.Product;
import babelsObjects.SalesItemsAdmin;
import java.sql.SQLException;

public class Products extends andro.babels.controllers.Tab {

    private andro.babels.Products Activity;
    private andro.babels.views.Products view;
    private Bundle extras;
    private andro.babels.controllers.Pos posController;

    public Products(andro.babels.Products activity) {
        super(activity);
        Activity = activity;
        view = new andro.babels.views.Products(activity);
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
        Thread thread = new Thread(new Runnable() {

            public void run() {
                try {
                    Welcome.mysql.Open();
                    try {
                        Product prod = new Product(Welcome.mysql.Conn);
                        prod.Load((Integer) prodObj[0]);
                        prodObj[4] = prod.ImageStream;
                        Object[] info = new Object[2];
                        info[0] = llObj;
                        info[1] = prodObj;
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
            Object[] product = (Object[]) info[1];
            if (product[4] != null){
                view.AddImageToObject(llObj, product);
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
