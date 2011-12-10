package babelsManagers;

import babels.Babels;
import babelsObjects.Session;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LoginManager {

    public boolean CheckFields(JTextField txtName, JTextField txtPass) {
        if (!txtName.getText().equals("") && !txtPass.getText().equals("")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Debe completar ambos campos",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean DoLogin(String name, String pass) throws SQLException {
        Babels.mysql.Open();
        try {
            Babels.session = new Session(Babels.mysql.Conn);
            if (Babels.session.Login(name, pass) == true) {
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Usuario/password incorrecto",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } finally {
            Babels.mysql.Close();
        }
    }
}
