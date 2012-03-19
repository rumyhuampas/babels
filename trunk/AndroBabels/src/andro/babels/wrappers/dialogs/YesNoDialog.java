package andro.babels.wrappers.dialogs;

import andro.babels.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class YesNoDialog extends andro.babels.wrappers.dialogs.Base{
    
    public static final String BUTTON_YES = "Yes"; 
    public static final String BUTTON_NO = "No";
    
    public YesNoDialog(Context context, String title, String msg, int imageId){
        super(context, title);
        dialog.setContentView(R.layout.yesnodialog);
        dialog.setCancelable(false);
        ((TextView)dialog.findViewById(R.id.ynd_txtMsg)).setText(msg);
        ((ImageView)dialog.findViewById(R.id.ynd_imgLogo)).setImageResource(imageId);
        ((Button)dialog.findViewById(R.id.ynd_btnYES)).setText(BUTTON_YES);
        ((Button)dialog.findViewById(R.id.ynd_btnNO)).setText(BUTTON_NO);
        ((Button)dialog.findViewById(R.id.ynd_btnYES)).setOnClickListener(defaultCallback);
        ((Button)dialog.findViewById(R.id.ynd_btnNO)).setOnClickListener(defaultCallback);
    }
    
    public void SetCallback(View.OnClickListener callback){
        if (callback == null){
            callback = defaultCallback;
        }
        ((Button)dialog.findViewById(R.id.ynd_btnYES)).setOnClickListener(callback);
        ((Button)dialog.findViewById(R.id.ynd_btnNO)).setOnClickListener(callback);
    }
}