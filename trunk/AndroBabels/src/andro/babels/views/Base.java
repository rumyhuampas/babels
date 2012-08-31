package andro.babels.views;

import andro.babels.R;
import andro.babels.wrappers.dialogs.*;
import android.content.Context;
import android.widget.Toast;

public class Base {
    public static void ShowToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
    
    public andro.babels.wrappers.dialogs.ImageDialog CreateErrorMessage(Context context, String msg){
        return new ImageDialog(context, "Error", msg, R.drawable.error);
    }
    
    public andro.babels.wrappers.dialogs.ImageDialog CreateInfoMessage(Context context, String msg){
        return new ImageDialog(context, "Info", msg, R.drawable.info);
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
    
    public static andro.babels.wrappers.dialogs.TextBoxDialog CreateTextBoxMessage(Context context, String title){
        return new TextBoxDialog(context, title);
    }
}
