package andro.babels.controllers;

import andro.babels.Combos;
import andro.babels.ItemDetails;
import andro.babels.Products;
import andro.babels.R;
import andro.babels.wrappers.AndroThread;
import andro.babels.wrappers.ExtraObject;
import andro.babels.wrappers.SaleList;
import andro.babels.wrappers.dialogs.ComboDialog;
import andro.babels.wrappers.dialogs.ImageDialog;
import andro.babels.wrappers.dialogs.LoadingDialog;
import andro.babels.wrappers.dialogs.YesNoDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import java.sql.SQLException;

public class Pos extends andro.babels.controllers.Base {

    private andro.babels.Pos Activity;
    private andro.babels.views.Pos view;
    private andro.babels.models.Pos model;
    private SaleList saleList;

    public Pos(andro.babels.Pos activity) {
        Activity = activity;
        view = new andro.babels.views.Pos(activity);
        model = new andro.babels.models.Pos();
        CreateTabs();
        SetListeners();
        saleList = new SaleList();
        view.RefreshSaleList(saleList, SaleItemOnClickHandler, SaleItemOnLongClickHandler);
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
                try {
                    final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Guardar venta", "Chequeando caja...");
                    loadDialog.show();

                    AndroThread thread = new AndroThread(andro.babels.controllers.Welcome.mysql,
                            model, "IsCashOpen", null, null, Boolean.class, loadDialog, IsCashOpenHandler, ExceptionHandler);
                    thread.Start();

                } catch (Exception ex) {
                    view.ShowToast(Activity, ex.getMessage());
                }
            }
        });
    }
    private Handler IsCashOpenHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object[] message = (Object[]) msg.obj;
            boolean response = ((Boolean) message[0]).booleanValue();
            LoadingDialog loadingDialog = (LoadingDialog) message[1];
            loadingDialog.hide();

            if (response == true) {
                SaveSale();
            } else {//Cash is close
                view.ShowToast(Activity, "La venta no puede realizarse. La caja esta cerrada.");
            }
        }
    };

    private void SaveSale() {
        final YesNoDialog saveDialog = view.CreateYesNoMessage(Activity, "Guardar venta", "¿Esta seguro?");
        saveDialog.SetCallback(new View.OnClickListener() {

            public void onClick(View v) {
                if (((Button) v).getText() == YesNoDialog.BUTTON_YES) {
                    final ComboDialog typeDialog = view.CreateComboMessage(Activity, "Tipo de venta", "Elija un tipo", R.array.types);
                    typeDialog.SetCallback(new View.OnClickListener() {

                        public void onClick(View v) {
                            final String type = typeDialog.GetSelectedValue();
                            if (!type.equals("")) {
                                if(type.equals("VENTA_A")){
                                    andro.babels.controllers.Base.RunActivityForResult(Activity, andro.babels.Clients.class, null);
                                }
                                else{
                                    SaveSaleInternal(type, -1);
                                }
                            }
                            typeDialog.hide();
                        }
                    });
                    typeDialog.show();
                }
                saveDialog.hide();
            }
        });
        saveDialog.show();
    }
    
    public void SaveSaleInternal(String type, int idClient){
        final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Guardar venta", "Guardando...");
        loadDialog.show();

        AndroThread thread = new AndroThread(andro.babels.controllers.Welcome.mysql,
                model, "SaveSale", new Class[]{SaleList.class, String.class, int.class},
                new Object[]{saleList, type, idClient}, null, loadDialog, SaveSaleHandler, ExceptionHandler);
        thread.Start();
    }
    
    private Handler SaveSaleHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object[] message = (Object[]) msg.obj;
            LoadingDialog loadingDialog = (LoadingDialog) message[1];
            loadingDialog.hide();
            view.ShowToast(Activity, "Venta guardada exitosamente");
            saleList.ClearSalelist();
            view.RefreshSaleList(saleList, SaleItemOnClickHandler, SaleItemOnLongClickHandler);
            view.SetSaleTotal(String.valueOf(saleList.GetSaleTotal()));
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
            saleList.AddSaleItem(view.GetObjectId(objView), view.GetObjectName(objView),
                    Float.parseFloat(view.GetObjectPrice(objView).replace("$", "")),
                    view.GetObjectType(objView));
            view.RefreshSaleList(saleList, SaleItemOnClickHandler, SaleItemOnLongClickHandler);
            view.SetSaleTotal(String.valueOf(saleList.GetSaleTotal()));
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
            saleList.RemoveSaleItem(view.GetSaleItemType(saleItemView), view.GetSaleItemId(saleItemView));
            view.RefreshSaleList(saleList, SaleItemOnClickHandler, SaleItemOnLongClickHandler);
            view.SetSaleTotal(String.valueOf(saleList.GetSaleTotal()));
        }
    };
    public OnLongClickListener SaleItemOnLongClickHandler = new OnLongClickListener() {

        public boolean onLongClick(View objView) {
            final YesNoDialog dialog = view.CreateYesNoMessage(Activity, "¿Limpiar lista?", "¿Borrar todos los items de la lista?");
            dialog.SetCallback(new View.OnClickListener() {

                public void onClick(View v) {
                    if (((Button) v).getText() == YesNoDialog.BUTTON_YES) {
                        saleList.ClearSalelist();
                        view.RefreshSaleList(saleList, SaleItemOnClickHandler, SaleItemOnLongClickHandler);
                        view.SetSaleTotal(String.valueOf(saleList.GetSaleTotal()));
                    }
                    dialog.hide();
                }
            });
            dialog.show();
            return true;
        }
    };

    public boolean HandleMenuSelection(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mm_miSett:
                Bundle extras = new Bundle();
                extras.putString("activity", "POS");
                andro.babels.controllers.Base.RunActivity(Activity, andro.babels.Settings.class, extras);
                return true;
            case R.id.mm_miCancelSale:
                andro.babels.controllers.Base.RunActivity(Activity, andro.babels.CancelSale.class, null);
                return true;
            default:
                return false;
        }
    }
}
