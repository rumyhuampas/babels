package andro.babels.wrappers.dialogs;

import andro.babels.R;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.*;

public class TextBoxDialog extends andro.babels.wrappers.dialogs.Base {        

    private TextView tv;
    
    public TextBoxDialog(Context context, String title){
        super(context, title);
        dialog.setContentView(R.layout.textboxdialog);
        tv = (TextView)dialog.findViewById(R.id.td_txtTicketNum);
        ((Button)dialog.findViewById(R.id.td_btnOK)).setOnClickListener(defaultCallback);
    }
    
    public void SetCallback(View.OnClickListener callback){
        if (callback == null){
            callback = defaultCallback;
        }
        ((Button)dialog.findViewById(R.id.td_btnOK)).setOnClickListener(callback);
    }
    
    public String GetInsertedText(){
        return tv.getText().toString();
    }
}
