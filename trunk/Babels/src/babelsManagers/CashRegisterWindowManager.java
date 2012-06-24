package babelsManagers;

import babelsObjects.Movement;
import babelsObjects.MovementTypes;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CashRegisterWindowManager {

    public static boolean doCashOpen(float amount) throws SQLException {
        try {
            babels.Babels.mysql.Open();
            MovementTypes Mt = new MovementTypes(babels.Babels.mysql.Conn);
            Mt.Load(MovementTypes.MT_APER);
            Movement move = new Movement(babels.Babels.mysql.Conn);
            move.Type = Mt;
            move.Amount = amount;
            move.User = babels.Babels.session.CurrentUser;
            if (move.Save()) {
                JOptionPane.showMessageDialog(null, "Caja abierta",
                        "Exito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo abrir la caja",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } finally {
            babels.Babels.mysql.Close();
        }
    }
}
