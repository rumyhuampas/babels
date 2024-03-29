package andro.babels.controllers;

import andro.babels.R;
import andro.babels.wrappers.AndroThread;
import andro.babels.wrappers.SaleList;
import andro.babels.wrappers.dialogs.*;
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
                    
                    AndroThread thread = new AndroThread(andro.babels.controllers.Welcome.mysql,
                            model, "Search",new Class[]{SaleList.class, String.class}, new Object[]{saleList, search},
                            null, loadDialog, SearchSaleHandler, ExceptionHandler);
                    thread.Start();
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
            Object[] message = (Object[]) msg.obj;
            LoadingDialog loadDialog = (LoadingDialog) message[1];
            loadDialog.hide();
            if (saleList.GetCancelationList().size() == 0) {
                if (saleList.GetGeneralList().size() == 0) {
                    view.ShowToast(Activity, "La búsqueda no encontró ningun resultado");
                }
                view.SetSaleTotal(String.valueOf(saleList.GetSaleTotal()));
            } else {
                view.SetSaleTotal(String.valueOf(saleList.GetSaleTotal()) + " - La venta ya fue cancelada");
            }
            view.RefreshSaleList(saleList);
        }
    };

    private void SetCancelButtonListener() {
        view.GetCancelButton().setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                final YesNoDialog cancelDialog = view.CreateYesNoMessage(Activity, "Cancelar venta", "¿Esta seguro?");
                cancelDialog.SetCallback(new View.OnClickListener() {

                    public void onClick(View v) {
                        if (((Button) v).getText() == YesNoDialog.BUTTON_YES) {
                            final TextBoxDialog textboxDialog = view.CreateTextBoxMessage(Activity, "Ingresar Numero de Ticket");
                            textboxDialog.SetCallback(new View.OnClickListener(){

                                public void onClick(View v2) {
                                    String ticketNumber = textboxDialog.GetInsertedText();
                                    if(!ticketNumber.isEmpty()){
                                        final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Cancelar venta", "Cancelando...");
                                        loadDialog.show(); 

                                        AndroThread thread = new AndroThread(andro.babels.controllers.Welcome.mysql,
                                        model, "CancelSale", new Class[]{String.class}, new Object[]{ticketNumber}, null, loadDialog, CancelSaleHandler, ExceptionHandler);
                                        thread.Start();
                                    }
                                    else{
                                        ImageDialog imageDialog = view.CreateErrorMessage(Activity, "Debe ingresar el numero de ticket para cancelar la venta");
                                        imageDialog.show();
                                    }
                                    textboxDialog.hide();
                                }
                            });
                            textboxDialog.show();
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
            Object[] message = (Object[]) msg.obj;
            LoadingDialog loadDialog = (LoadingDialog) message[1];
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
