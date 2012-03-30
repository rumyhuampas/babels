package andro.babels.views;

import andro.babels.R;
import andro.babels.wrappers.dialogs.ComboDialog;
import andro.babels.wrappers.dialogs.ImageDialog;
import andro.babels.wrappers.dialogs.LoadingDialog;
import andro.babels.wrappers.dialogs.YesNoDialog;
import android.content.Context;
import android.widget.Toast;

public class Base {
    public static void ShowToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
    
    public andro.babels.wrappers.dialogs.ImageDialog CreateErrorMessage(Context context, String msg){
        return new ImageDialog(context, "Error", msg, R.drawable.error);
    }
    
    public static andro.babels.wrappers.dialogs.YesNoDialog CreateYesNoMessage(Context context, String title, String msg){
        return new YesNoDialog(context, title, msg, R.drawable.question);
    }
    
    public static andro.babels.wrappers.dialogs.LoadingDialog CreateLoadingMessage(Context context, String title, String msg){
        return new LoadingDialog(context, title, msg);
    }
    
    public andro.babels.wrappers.dialogs.ComboDialog CreateComboMessage(Context context, String title, String prompt, int ArrayID){
        return new ComboDialog(context, title, prompt, ArrayID);
    }
}
