package andro.babels.controllers;

import andro.babels.R;
import andro.babels.wrappers.SaleList;
import andro.babels.wrappers.dialogs.ComboDialog;
import andro.babels.wrappers.dialogs.ImageDialog;
import andro.babels.wrappers.dialogs.LoadingDialog;
import andro.babels.wrappers.dialogs.YesNoDialog;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import java.sql.SQLException;

public class CancelSale extends andro.babels.controllers.Base {

    private andro.babels.CancelSale Activity;
    private andro.babels.views.CancelSale view;
    private andro.babels.models.CancelSale model;
    private SaleList saleList;

    public CancelSale(andro.babels.CancelSale activity) {
        Activity = activity;
        view = new andro.babels.views.CancelSale(activity);
        model = new andro.babels.models.CancelSale();
        SetListeners();
        saleList = new SaleList();
        view.RefreshSaleList(saleList);
    }

    public void SetListeners() {
        SetSearchButtonListener();
        SetCancelButtonListener();
    }

    private void SetSearchButtonListener() {
        view.GetSearchButton().setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                final String search = view.GetSearchText();
                if (!search.equals("")) {
                    final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Buscar venta", "Buscando...");
                    loadDialog.show();
                    Thread thread = new Thread(new Runnable() {

                        public void run() {
                            try {
                                andro.babels.controllers.Welcome.mysql.Open();
                                try {
                                    model.Search(saleList, search);
                                    Message msg = SearchSaleHandler.obtainMessage(1, loadDialog);
                                    SearchSaleHandler.sendMessage(msg);

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
                } else {
                    view.ShowToast(Activity, "El campo de búsqueda está vacio");
                }
            }
        });
    }
    private Handler SearchSaleHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LoadingDialog loadDialog = (LoadingDialog) msg.obj;
            loadDialog.hide();
            if(saleList.GetGeneralList().size() == 0){
                view.ShowToast(Activity, "La búsqueda no encontró ningun resultado");
            }
            view.RefreshSaleList(saleList);
            view.SetSaleTotal(String.valueOf(saleList.GetSaleTotal()));
        }
    };

    private void SetCancelButtonListener() {
        view.GetCancelButton().setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                final YesNoDialog cancelDialog = view.CreateYesNoMessage(Activity, "Cancelar venta", "¿Esta seguro?");
                cancelDialog.SetCallback(new View.OnClickListener() {

                    public void onClick(View v) {
                        if (((Button) v).getText() == YesNoDialog.BUTTON_YES) {
                            final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Cancelar venta", "Cancelando...");
                            loadDialog.show();
                            Thread thread = new Thread(new Runnable() {

                                public void run() {
                                    try {
                                        andro.babels.controllers.Welcome.mysql.Open();
                                        try {
                                            //model.SaveSale(saleList, type);
                                            Message msg = CancelSaleHandler.obtainMessage(1, loadDialog);
                                            CancelSaleHandler.sendMessage(msg);

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
                        cancelDialog.hide();
                    }
                });
                cancelDialog.show();
            }
        });
    }
    private Handler CancelSaleHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LoadingDialog loadDialog = (LoadingDialog) msg.obj;
            loadDialog.hide();
            view.ShowToast(Activity, "Venta cancelada exitosamente");
            saleList.ClearSalelist();
            view.RefreshSaleList(saleList);
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
}
