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
import java.io.File;
import java.io.IOException;
import org.ini4j.Ini;

public class Babels {

    public static MySQL mysql = null;
    public static Session session = null;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                    "de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel");
            InitDBConn();
            
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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void InitDBConn() throws IOException{
        String appPath = Babels.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        appPath = appPath + "Babels.ini";
        appPath = appPath.replaceAll("%20", " ");
        Ini ini = new Ini(new File(appPath));
        String server = ini.get("CONFIG", "SERVER");
        String bd = ini.get("CONFIG", "DB");
        String user = ini.get("CONFIG", "USER");
        String pass = ini.get("CONFIG", "PASS");
        mysql = new MySQL(server, bd, user, pass);
    }
}
