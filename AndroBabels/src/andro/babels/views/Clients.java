package andro.babels.views;

import andro.babels.R;
import andro.babels.wrappers.BabelsSettings;
import android.widget.Button;
import android.widget.TextView;

public class Clients extends andro.babels.views.Base {
    
    private andro.babels.Clients Activity;
    private int TextSize;
    
    public Clients(andro.babels.Clients activity) {
        Activity = activity;
        TextSize = Integer.parseInt(andro.babels.controllers.Welcome.settings.GetAppSetting(BabelsSettings.TITLETEXTSIZEKEY, BabelsSettings.TITLETEXTSIZEDEFAULT));
        GetOKButton().setTextSize(TextSize);
        GetCancelButton().setTextSize(TextSize);
        GetSearchLabel().setTextSize(TextSize);
        GetSearchButton().setTextSize(TextSize);
        GetNameLabel().setTextSize(TextSize);
        GetDocNumLabel().setTextSize(TextSize);
        GetAddressLabel().setTextSize(TextSize);
        GetSearchTextBox().setTextSize(TextSize);
    }
    
    public TextView GetSearchLabel(){
        return (TextView)Activity.findViewById(R.id.cli_lblSearch);
    }
     
    public Button GetSearchButton(){
        return (Button)Activity.findViewById(R.id.cli_btnSearch);
    }
    
    public TextView GetNameLabel(){
        return (TextView)Activity.findViewById(R.id.cli_lblName);
    }
    
    public TextView GetDocNumLabel(){
        return (TextView)Activity.findViewById(R.id.cli_lblDocNum);
    }
    
    public TextView GetAddressLabel(){
        return (TextView)Activity.findViewById(R.id.cli_lblAddress);
    }
    
    public Button GetOKButton(){
        return (Button)Activity.findViewById(R.id.cli_btnOK);
    }
    
    public Button GetCancelButton(){
        return (Button)Activity.findViewById(R.id.cli_btnCancel);
    }
    
    public TextView GetSearchTextBox(){
        return (TextView)Activity.findViewById(R.id.cli_txtSearch);
    }
    
    public String GetSearchText(){
        return ((TextView)Activity.findViewById(R.id.cli_txtSearch)).getText().toString();
    }
}
