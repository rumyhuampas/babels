package andro.babels.controllers;

import andro.babels.wrappers.AndroThread;
import andro.babels.wrappers.dialogs.ImageDialog;
import andro.babels.wrappers.dialogs.LoadingDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import babelsObjects.Client;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Clients extends andro.babels.controllers.Base {
    
    private andro.babels.Clients Activity;
    private andro.babels.views.Clients view;
    private andro.babels.models.Clients model;
    private Client currentClient;
    
    public Clients(andro.babels.Clients activity) {
        Activity = activity;
        view = new andro.babels.views.Clients(activity);
        model = new andro.babels.models.Clients();
        try {
            currentClient = new Client(andro.babels.controllers.Welcome.mysql.Conn);
        } catch (SQLException ex) {
            Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
        }
        SetListeners();
        view.GetOKButton().setEnabled(false);
    }
    
    public void SetListeners() {
        SetSearchButtonListener();
        SetOKButtonListener();
        SetCancelButtonListener();
    }
    
    private void SetSearchButtonListener(){
        view.GetSearchButton().setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String searchText = view.GetSearchText();
                if (!searchText.equals("")) {
                    final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Buscar cliente", "Buscando...");
                    loadDialog.show();
                    try{
                        andro.babels.controllers.Welcome.mysql.Open();
                        AndroThread thread = new AndroThread(andro.babels.controllers.Welcome.mysql,
                                model, "Search", new Class[]{String.class}, new Object[]{searchText},
                                Boolean.class, loadDialog, SearchClientHandler, ExceptionHandler);
                        thread.Start();
                    }
                    catch (SQLException ex) {
                        Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
                    }                    
                    finally{
                        andro.babels.controllers.Welcome.mysql.Close();
                    }
                } else {
                    view.ShowToast(Activity, "El campo de búsqueda está vacio");
                }
            }
        });
    }
    
    private Handler SearchClientHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Object[] message = (Object[]) msg.obj;
            LoadingDialog loadDialog = (LoadingDialog) message[1];
            loadDialog.hide();
            boolean result = (Boolean)message[0];
            if (result == true) {
                view.SetName(model.currentClient.Name);
                view.SetDocNum(model.currentClient.DocNum);
                view.SetAddress(model.currentClient.Address);
                view.GetOKButton().setEnabled(true);
            } else {
                view.ShowToast(Activity, "El cliente no existe");
                view.SetName("");
                view.SetDocNum("");
                view.SetAddress("");
                view.GetOKButton().setEnabled(false);
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
    
    private void SetOKButtonListener() {
        view.GetOKButton().setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent= Activity.getIntent();
                intent.putExtra("idClient", model.currentClient.getId());
                Activity.setResult(Activity.RESULT_OK, intent);
                Activity.finish();
            }
        });
    }
    
    private void SetCancelButtonListener() {
        view.GetCancelButton().setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Activity.setResult(Activity.RESULT_CANCELED);
                Activity.finish();
            }
        });
    }
}
