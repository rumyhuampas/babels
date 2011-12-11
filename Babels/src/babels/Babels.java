package babels;

import babelsObjects.FormsFactory;
import babelsObjects.MySQL;
import babelsObjects.Session;
import babelsObjects.UsersAdmin;
import java.sql.SQLException;

public class Babels {

    public static MySQL mysql = new MySQL();
    public static Session session = null;

    public static void main(String[] args) throws SQLException {
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
    }
}
