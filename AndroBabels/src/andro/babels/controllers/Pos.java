package andro.babels.controllers;

import andro.babels.Combos;
import andro.babels.Products;
import andro.babels.R;
import andro.babels.wrappers.ExtraObject;
import andro.babels.wrappers.dialogs.ImageDialog;
import andro.babels.wrappers.dialogs.YesNoDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        view.CreateTab(productsIntent, "tabProducts", "Products");
    }

    public void SetListeners() {
        SetDoneButtonListener();
    }

    private void SetDoneButtonListener() {
        view.GetDoneButton().setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                final YesNoDialog dialog = view.CreateYesNoMessage(Activity, "Save sale?", "Are you sure?");
                dialog.SetCallback(new View.OnClickListener() {

                    public void onClick(View view) {
                        if (((Button) view).getText() == YesNoDialog.BUTTON_YES) {
                            model.SaveSale();
                        } else {
                            dialog.hide();
                        }
                    }
                });
                dialog.show();
                /*
                 * String test = ""; for (int i=0;
                 * i<model.GetSaleList().size();i++){ test =
                 * test.concat(model.GetSaleList().get(i).name.toString()); }
                 * view.ShowToast(Activity, test);
                 */
            }
        });
    }
    public OnClickListener ObjectOnClickHandler = new OnClickListener() {

        public void onClick(View objView) {
            model.AddSaleItem(view.GetObjectId(objView), view.GetObjectName(objView),
                    Float.parseFloat(view.GetObjectPrice(objView).replace("$", "")),
                    view.GetObjectType(objView));
            view.RefreshSaleList(model.GetGeneralList(), SaleItemOnClickHandler);
            view.SetSaleTotal(String.valueOf(model.GetSaleTotal()));
        }
    };
    public OnClickListener SaleItemOnClickHandler = new OnClickListener() {

        public void onClick(View saleItemView) {
            model.RemoveSaleItem(view.GetSaleItemType(saleItemView), view.GetSaleItemId(saleItemView));
            view.RefreshSaleList(model.GetGeneralList(), SaleItemOnClickHandler);
            view.SetSaleTotal(String.valueOf(model.GetSaleTotal()));
        }
    };
    /*
     * private void LoadInfo(){ Bundle extras =
     * Activity.getIntent().getExtras(); ExtraObject extraObj = (ExtraObject)
     * extras.getParcelable("combos"); //for (int i = 0; i< extraObj.Obj.length;
     * i++){ // ImageDialog d = new ImageDialog(Activity, "TEST",
     * ((Object[])extraObj.Obj[i])[1].toString(), R.drawable.error, null); //
     * d.show(); //}
    }
     */
}
