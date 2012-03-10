package andro.babels.views;

import andro.babels.R;
import andro.babels.wrappers.dialogs.ImageDialog;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class Base {
    public static void ShowToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
    
    public void ShowError(Context context, String title, String msg, View.OnClickListener callback){
        ImageDialog dialog = new ImageDialog();
        dialog.show(context, title, msg, R.drawable.error, callback);
    }
}
