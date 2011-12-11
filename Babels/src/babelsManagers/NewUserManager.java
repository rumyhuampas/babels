package babelsManagers;

import babels.Babels;
import babelsInterfaces.IBabelsDialog;
import babelsListeners.KeyListenerType;
import babelsListeners.txtFieldListener;
import babelsObjects.User;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NewUserManager {
    
    public void SetFieldsListeners(JTextField txtName, JTextField txtPass, JTextField txtRePass, IBabelsDialog dialog){
        txtName.addKeyListener(new txtFieldListener(KeyListenerType.NO_SPACES, dialog));
        txtPass.addKeyListener(new txtFieldListener(KeyListenerType.NO_SPACES, dialog));
        txtRePass.addKeyListener(new txtFieldListener(KeyListenerType.NO_SPACES, dialog));
    }

    public boolean CheckFields(JTextField txtName, JTextField txtPass, JTextField txtRePass) {
        if (!txtName.getText().equals("") && !txtPass.getText().equals("")
                && !txtPass.getText().equals("")) {
            if (txtPass.getText().equals(txtRePass.getText())) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Los password no son iguales",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe completar todos los campos",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean SaveNewUser(String name, String pass, boolean isAdmin) throws SQLException {
        Babels.mysql.Open();
        try {
            User user = new User(Babels.mysql.Conn);
            user.Name = name;
            user.Pass = pass;
            user.IsAdmin = isAdmin;
            user.Active = true;
            if (!user.Exists()) {
                if (user.Save()) {
                    JOptionPane.showMessageDialog(null, "Usuario guardado",
                            "Nuevo Usuario", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar el usuario",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "El usuario ya existe",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } finally {
            Babels.mysql.Close();
        }
    }
}
