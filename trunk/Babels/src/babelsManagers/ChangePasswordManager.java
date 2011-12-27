package babelsManagers;

import babels.Babels;
import babelsInterfaces.IBabelsDialog;
import babelsListeners.KeyListenerType;
import babelsListeners.txtFieldListener;
import babelsObjects.User;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ChangePasswordManager {

    public void SetFieldsListeners(JTextField txtPass, JTextField txtNewPass, JTextField txtReNewPass, IBabelsDialog dialog) {
        txtPass.addKeyListener(new txtFieldListener(KeyListenerType.NO_SPACES, dialog));
        txtNewPass.addKeyListener(new txtFieldListener(KeyListenerType.NO_SPACES, dialog));
        txtReNewPass.addKeyListener(new txtFieldListener(KeyListenerType.NO_SPACES, dialog));
    }

    public boolean CheckFields(JTextField txtPass, JTextField txtNewPass, JTextField txtReNewPass) {
        if (!txtPass.getText().equals("") && !txtNewPass.getText().equals("")
                && !txtReNewPass.getText().equals("")) {
            if (txtNewPass.getText().equals(txtReNewPass.getText())) {
                if (txtPass.getText().equals(Babels.session.CurrentUser.Pass)) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Password actual incorrecto",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Los passwords no son iguales",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe completar todos los campos",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean ChangeUserPassword(String newPass) throws SQLException {
        Babels.mysql.Open();
        try {
            User user = new User(Babels.mysql.Conn);
            user.Load(Babels.session.CurrentUser);
            user.Pass = newPass;
            if (user.Save()) {
                Babels.session.CurrentUser = user;
                JOptionPane.showMessageDialog(null, "Password cambiado",
                        "Cambiar password", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Error al cambiar el password",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } finally {
            Babels.mysql.Close();
        }
    }
}
