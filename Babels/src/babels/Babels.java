package babels;

import babelsObjects.FormsFactory;
import babelsObjects.MySQL;
import babelsObjects.Session;
import babelsObjects.UsersAdmin;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Babels {

    public static MySQL mysql = new MySQL();
    public static Session session = null;

    public static void main(String[] args) {
        try {
            mysql.Open();
            try {
                FormsFactory.GetDialogForm("babelsForms.Login", false, null, null);            
                if (UsersAdmin.GetAllUsers(mysql.Conn).length == 0) {
                    Class[] classPrm = {java.awt.Frame.class, boolean.class, boolean.class};
                    Object[] objectPrm = {new javax.swing.JFrame(), true, true};
                    FormsFactory.GetDialogForm("babelsForms.NewUser", false,
                            classPrm, objectPrm);
                }
            } finally {
                mysql.Close();
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Error " + ex.getErrorCode(), JOptionPane.ERROR_MESSAGE);   
        }
    }
}