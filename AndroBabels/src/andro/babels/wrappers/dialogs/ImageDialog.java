package andro.babels.wrappers.dialogs;

import andro.babels.R;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageDialog extends Base{

    public ImageDialog(Context context, String title, String msg, int imageId, View.OnClickListener callback){
        super(context, title, callback);
        dialog.setContentView(R.layout.imagedialog);
        ((TextView)dialog.findViewById(R.id.id_txtMsg)).setText(msg);
        ((ImageView)dialog.findViewById(R.id.id_imgLogo)).setImageResource(imageId);
        if (callback == null){
            callback = defaultCallback;
        }
        ((Button)dialog.findViewById(R.id.id_btnOK)).setOnClickListener(callback);
    }
}
