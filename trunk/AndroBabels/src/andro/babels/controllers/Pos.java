package andro.babels.controllers;

import andro.babels.Combos;
import andro.babels.ItemDetails;
import andro.babels.Products;
import andro.babels.wrappers.ExtraObject;
import andro.babels.wrappers.dialogs.ImageDialog;
import andro.babels.wrappers.dialogs.LoadingDialog;
import andro.babels.wrappers.dialogs.YesNoDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import java.sql.SQLException;

public class Pos extends andro.babels.controllers.Base {

    private andro.babels.Pos Activity;
    private andro.babels.views.Pos view;
    private andro.babels.models.Pos model;

    public Pos(andro.babels.Pos activity) {
        Activity = activity;
        view = new andro.babels.views.Pos(activity);
        model = new andro.babels.models.Pos();
        CreateTabs();
        SetListeners();
        view.RefreshSaleList(model.GetGeneralList(), SaleItemOnClickHandler, SaleItemOnLongClickHandler);
    }

    private void CreateTabs() {
        view.InitTabHost();

        Bundle extras = Activity.getIntent().getExtras();
        Object[] posController = new Object[1];
        posController[0] = this;
        ExtraObject objects = new ExtraObject(posController);
        extras.putParcelable("posController", objects);

        Intent combosIntent = new Intent().setClass(Activity, Combos.class);
        combosIntent.putExtras(extras);
        view.CreateTab(combosIntent, "tabCombos", "Combos");

        Intent productsIntent = new Intent().setClass(Activity, Products.class);
        productsIntent.putExtras(extras);
        view.CreateTab(productsIntent, "tabProducts", "Productos");
    }

    public void SetListeners() {
        SetDoneButtonListener();
    }

    private void SetDoneButtonListener() {
        view.GetDoneButton().setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                final YesNoDialog dialog = view.CreateYesNoMessage(Activity, "Guardar venta", "¿Esta seguro?");
                dialog.SetCallback(new View.OnClickListener() {

                    public void onClick(View v) {
                        if (((Button) v).getText() == YesNoDialog.BUTTON_YES) {
                            final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Guardar venta", "Guardando...");
                            loadDialog.show();
                            Thread thread = new Thread(new Runnable() {

                                public void run() {
                                    try {
                                        andro.babels.controllers.Welcome.mysql.Open();
                                        try {
                                            model.SaveSale();
                                            Message msg = SaveSaleHandler.obtainMessage(1, loadDialog);
                                            SaveSaleHandler.sendMessage(msg);

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
                        dialog.hide();
                    }
                });
                dialog.show();
            }
        });
    }
    private Handler SaveSaleHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LoadingDialog loadDialog = (LoadingDialog) msg.obj;
            loadDialog.hide();
            model.ClearSalelist();
            view.RefreshSaleList(model.GetGeneralList(), SaleItemOnClickHandler, SaleItemOnLongClickHandler);
            view.SetSaleTotal(String.valueOf(model.GetSaleTotal()));
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
    public OnClickListener ObjectOnClickHandler = new OnClickListener() {

        public void onClick(View objView) {
            model.AddSaleItem(view.GetObjectId(objView), view.GetObjectName(objView),
                    Float.parseFloat(view.GetObjectPrice(objView).replace("$", "")),
                    view.GetObjectType(objView));
            view.RefreshSaleList(model.GetGeneralList(), SaleItemOnClickHandler, SaleItemOnLongClickHandler);
            view.SetSaleTotal(String.valueOf(model.GetSaleTotal()));
        }
    };
    public OnLongClickListener ObjectOnLongClickHandler = new OnLongClickListener() {

        public boolean onLongClick(View objView) {
            Bundle extras = new Bundle();
            extras.putInt("ItemID", view.GetObjectId(objView));
            extras.putString("ItemType", view.GetObjectType(objView));
            RunActivity(Activity, ItemDetails.class, extras);
            return true;
        }
    };
    public OnClickListener SaleItemOnClickHandler = new OnClickListener() {

        public void onClick(View saleItemView) {
            model.RemoveSaleItem(view.GetSaleItemType(saleItemView), view.GetSaleItemId(saleItemView));
            view.RefreshSaleList(model.GetGeneralList(), SaleItemOnClickHandler, SaleItemOnLongClickHandler);
            view.SetSaleTotal(String.valueOf(model.GetSaleTotal()));
        }
    };
    public OnLongClickListener SaleItemOnLongClickHandler = new OnLongClickListener() {

        public boolean onLongClick(View objView) {
            final YesNoDialog dialog = view.CreateYesNoMessage(Activity, "¿Limpiar lista?", "¿Borrar todos los items de la lista?");
            dialog.SetCallback(new View.OnClickListener() {

                public void onClick(View v) {
                    if (((Button) v).getText() == YesNoDialog.BUTTON_YES) {
                        model.ClearSalelist();
                        view.RefreshSaleList(model.GetGeneralList(), SaleItemOnClickHandler, SaleItemOnLongClickHandler);
                    }
                    dialog.hide();
                }
            });
            dialog.show();
            return true;
        }
    };
}
