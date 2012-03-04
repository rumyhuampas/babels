package andro.babels.controllers;

import babelsObjects.MySQL;
import babelsObjects.User;
import java.sql.SQLException;

public class Welcome extends andro.babels.controllers.Base {
    
    private andro.babels.Welcome Activity;
    private andro.babels.views.Welcome view;
    public static MySQL mysql = new MySQL();
    
    public Welcome(andro.babels.Welcome activity){
        Activity = activity;
        view = new andro.babels.views.Welcome(activity);
        //ConnectDB();
    }
    
    private void ConnectDB() {
        /*try {
            mysql.Open();
            try {
                User user = new User(null);
            } finally {
                mysql.Close();
            }
        } catch (SQLException ex) {*/
            /*JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error " + ex.getErrorCode(), JOptionPane.ERROR_MESSAGE);*/
        //}
    }
}
