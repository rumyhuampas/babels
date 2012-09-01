package babelsManagers;

import babelsInterfaces.IBabelsDialog;
import babelsListeners.KeyListenerType;
import babelsListeners.txtFieldListener;
import babelsObjects.Movement;
import babelsObjects.MovementAdmin;
import babelsObjects.MovementTypes;
import babelsObjects.Print;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CashRegisterWindowManager {

    public static boolean doCashOpen(float amount, String Description) throws SQLException {
        try {
            babels.Babels.mysql.Open();
            if (!MovementAdmin.IsCashOpen(babels.Babels.mysql.Conn)) {

                MovementTypes Mt = new MovementTypes(babels.Babels.mysql.Conn);
                Mt.Load(MovementTypes.MT_APER);
                Movement move = new Movement(babels.Babels.mysql.Conn);
                move.Type = Mt;
                move.Description = Description;
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

    public static boolean doCashClose(float amount, boolean isParcial, String Description) throws SQLException {
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
            move.Description = Description;
            move.Type = Mt;
            if (amount > 0) {
                amount = -amount;
            }
            move.Amount = amount;
            move.User = babels.Babels.session.CurrentUser;
            if (move.Save()) {
                if (isParcial == false){
                Print print = new Print(babels.Babels.mysql.Conn);
                print.Move = move;
                print.Printer = Print.PR_FIS;
                print.Status = Print.ST_PEND;
                print.Save();
            }
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
     public static void SetFieldsListeners(JTextField txtAmount, IBabelsDialog dialog) {
               txtAmount.addKeyListener(new txtFieldListener(KeyListenerType.NUMBERS_ONLY, dialog));
       
    }
    public static boolean doCashMove(float amount, boolean isIn, String Description) throws SQLException {
        try {
            babels.Babels.mysql.Open();
             if (MovementAdmin.IsCashOpen(babels.Babels.mysql.Conn)) {
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
            move.Description = Description;
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
            } else {
                JOptionPane.showMessageDialog(null, "La caja no esta abierta",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }   return false;
        } finally {
            babels.Babels.mysql.Close();
        }
    }
}
