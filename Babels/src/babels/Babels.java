package babels;

import babelsObjects.FormsFactory;
import babelsObjects.MySQL;
import babelsObjects.Session;
import babelsObjects.UsersAdmin;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;

public class Babels {

    public static MySQL mysql = new MySQL("localhost", "babels", "root", "");
    public static Session session = null;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    "de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel");
        } catch (Exception ex) {
            Logger.getLogger(Babels.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            mysql.Open();
            try {
                FormsFactory.GetDialogForm("babelsForms.Login", false, null, null);
                if (UsersAdmin.NoAdminUser(mysql.Conn) == true) {
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