package babelsManagers;

import babelsObjects.Movement;
import babelsObjects.MovementAdmin;
import babelsObjects.MovementTypes;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CashRegisterWindowManager {

    public static boolean doCashOpen(float amount) throws SQLException {
        try {
            babels.Babels.mysql.Open();
            if (!MovementAdmin.IsCashOpen(babels.Babels.mysql.Conn)) {

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
            } else {
                JOptionPane.showMessageDialog(null, "La caja ya esta abierta",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;

        } finally {
            babels.Babels.mysql.Close();
        }
    }

    public static boolean doCashClose(float amount, boolean isParcial) throws SQLException {
        try {
            babels.Babels.mysql.Open();
             if (MovementAdmin.IsCashOpen(babels.Babels.mysql.Conn)) {
            MovementTypes Mt = new MovementTypes(babels.Babels.mysql.Conn);
            if (isParcial == true) {
                Mt.Load(MovementTypes.MT_CIERREPARC);
            } else {
                Mt.Load(MovementTypes.MT_CIERRE);
            }
            Movement move = new Movement(babels.Babels.mysql.Conn);
            move.Type = Mt;
            if (amount > 0) {
                amount = -amount;
            }
            move.Amount = amount;
            move.User = babels.Babels.session.CurrentUser;
            if (move.Save()) {
                JOptionPane.showMessageDialog(null, "Caja cerrada",
                        "Exito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo cerrar la caja",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            } else {
                JOptionPane.showMessageDialog(null, "La caja no esta abierta",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }   return false;
        } finally {
            babels.Babels.mysql.Close();
        }
    }

    public static boolean doCashMove(float amount, boolean isIn) throws SQLException {
        try {
            babels.Babels.mysql.Open();
            MovementTypes Mt = new MovementTypes(babels.Babels.mysql.Conn);
            if (isIn == true) {
                Mt.Load(MovementTypes.MT_DEPOSITO);
            } else {
                Mt.Load(MovementTypes.MT_EXTRACCION);
            }
            Movement move = new Movement(babels.Babels.mysql.Conn);
            move.Type = Mt;
            if (isIn == false) {
                if (amount > 0) {
                    amount = -amount;
                }
            }
            move.Amount = amount;
            move.User = babels.Babels.session.CurrentUser;
            if (move.Save()) {
                JOptionPane.showMessageDialog(null, "Movimiento realizado",
                        "Exito", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo realizar el movimiento",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } finally {
            babels.Babels.mysql.Close();
        }
    }
}
