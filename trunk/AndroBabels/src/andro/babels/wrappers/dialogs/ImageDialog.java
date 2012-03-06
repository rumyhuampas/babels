package andro.babels.wrappers.dialogs;

import andro.babels.R;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageDialog extends Base{

    public void show(Context appContext, String title, String message, int resId, View.OnClickListener listener) {
        dialog = new Dialog(appContext);
        dialog.setContentView(R.layout.imagedialog);
        dialog.setTitle(title);
        ((TextView)dialog.findViewById(R.id.id_txtMsg)).setText(message);
        ((ImageView)dialog.findViewById(R.id.id_imgLogo)).setImageResource(resId);
        if (listener == null){
            listener = defaultCallback;
        }
        ((Button)dialog.findViewById(R.id.id_btnOK)).setOnClickListener(listener);
        dialog.setCancelable(listener == null);
        dialog.show();
    }
}
