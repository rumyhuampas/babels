package andro.babels.controllers;

import babelsObjects.MySQL;
import java.sql.SQLException;

public class Welcome extends andro.babels.controllers.Base {
    
    private andro.babels.Welcome Activity;
    private andro.babels.views.Welcome view;
    public static MySQL mysql = new MySQL();
    
    public Welcome(andro.babels.Welcome activity){
        Activity = activity;
        view = new andro.babels.views.Welcome(activity);
    }
    
    private void ConnectDB(){
        try {
            mysql.Open();
            try {
                
            } finally {
                mysql.Close();
            }
        } catch (SQLException ex) {
            /*JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error " + ex.getErrorCode(), JOptionPane.ERROR_MESSAGE);*/
        }
    }
    
    public void LoadInfo(){
        
    }
}
