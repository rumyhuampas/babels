package babelsManagers;

import babels.Babels;
import babelsObjects.CashMovements;
import babelsObjects.CashRegister;
import babelsObjects.User;
import java.sql.Date;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Calendar;
import javax.swing.JTextField;

public class CashRegisterManager {

    private JTable Table;
    private TableModel Model;
    public boolean RefreshingTable;
    private Date today;

    public CashRegisterManager(JTable table) {
        this.Table = table;
        this.Model = this.Table.getModel();
    }

    public CashRegisterManager() {
    }

    public void RefreshTable() throws SQLException {
        this.RefreshingTable = true;
        ClearTable();
        LoadMovements();
        this.RefreshingTable = false;
    }

    private void ClearTable() {
        DefaultTableModel tm = (DefaultTableModel) this.Model;
        tm.getDataVector().removeAllElements();
    }

    private void LoadMovements() throws SQLException {
        Babels.mysql.Open();
        try {
            Object[] rows = CashMovements.GetMovements(Babels.mysql.Conn);
            Object[] row = null;
            DefaultTableModel tm = (DefaultTableModel) this.Model;
            Object[] rowTable = null;
            for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
                row = (Object[]) rows[rowIdx];
                rowTable = new Object[9];
                rowTable[0] = row[0];
                rowTable[1] = row[1];
                rowTable[2] = row[3];
                rowTable[3] = row[2];
                rowTable[4] = row[4];
                rowTable[5] = row[5];
                rowTable[6] = row[6];
                rowTable[7] = row[7];
                rowTable[8] = row[8];

                tm.addRow(rowTable);
            }
        } finally {
            Babels.mysql.Close();
        }
    }

    public boolean OpenCashRegister(JTextField txtAmount) throws SQLException {
        Babels.mysql.Open();
        try {
            CashRegister cashReg = new CashRegister(Babels.mysql.Conn);
            CashMovements cashMov = new CashMovements(Babels.mysql.Conn);
            cashReg.IdActionType = 1;
            User currentUser = Babels.session.CurrentUser;
            cashReg.IdUser = currentUser.getId();
            //  cashReg.DatePosted = today.
            cashReg.IdPos = 0;

            if (cashReg.InsertCashRegister() == true) {
                cashMov.Total = Float.parseFloat(txtAmount.getText());
                cashMov.IdCashRegister = cashReg.getId();
                cashMov.MovementType = "CashRegister";
                if (cashMov.InsertCashRegister() == true) {
                    JOptionPane.showMessageDialog(null, "Caja abierta exitosamente",
                            "Exito", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo abrir caja",
                            "Error", JOptionPane.ERROR_MESSAGE);

                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo abrir caja",
                        "Error", JOptionPane.ERROR_MESSAGE);

                return false;
            }
        } finally {
            Babels.mysql.Close();
        }
    }
}
