package babelsManagers;

import babels.Babels;
import babelsObjects.MovementAdmin;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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

    public void RefreshTable(Date DateBegining, Date DateFinal) throws SQLException {
        this.RefreshingTable = true;
        ClearTable();
        LoadMovements(DateBegining, DateFinal);
        this.RefreshingTable = false;
    }

    private void ClearTable() {
        DefaultTableModel tm = (DefaultTableModel) this.Model;
        tm.getDataVector().removeAllElements();
    }

    private void LoadMovements(Date DateBegining, Date DateFinal) throws SQLException {
        Babels.mysql.Open();
        try {
            Object[] rows = MovementAdmin.GetMovements(Babels.mysql.Conn, DateBegining, DateFinal);
            Object[] row = null;
            DefaultTableModel tm = (DefaultTableModel) this.Model;
            Object[] rowTable = null;
            for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
                row = (Object[]) rows[rowIdx];
                rowTable = new Object[5];
                rowTable[0] = row[0];
                rowTable[1] = row[1];
                rowTable[2] = row[2];
                rowTable[3] = row[3];
                rowTable[4] = row[4];
                

                tm.addRow(rowTable);
            }
        } finally {
            Babels.mysql.Close();
        }
    }

 /*   public boolean OpenCashRegister(JTextField txtAmount) throws SQLException {
        Babels.mysql.Open();
        try {
            CashRegister cashReg = new CashRegister(Babels.mysql.Conn);
            Movement cashMov = new Movement(Babels.mysql.Conn);
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
    } */
}
