package babelsManagers;

import babels.Babels;
import babelsInterfaces.IBabelsDialog;
import babelsListeners.KeyListenerType;
import babelsListeners.txtFieldListener;
import babelsObjects.Session;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LoginManager {
    
    public void SetFieldsListeners(JTextField txtName, JTextField txtPass, IBabelsDialog dialog){
        txtName.addKeyListener(new txtFieldListener(KeyListenerType.NO_SPACES, dialog));
        txtPass.addKeyListener(new txtFieldListener(KeyListenerType.NO_SPACES, dialog));
    }

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
