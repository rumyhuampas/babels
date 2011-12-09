package babels;

import babelsForms.Login;
import babelsObjects.MySQL;
import babelsObjects.Session;
import java.sql.SQLException;

public class Babels {

    public static MySQL mysql = new MySQL();
    public static Session session = null;

    public static void main(String[] args) throws SQLException {
        LaunchLoginFrame();
    }
    
    static void LaunchLoginFrame(){
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}
