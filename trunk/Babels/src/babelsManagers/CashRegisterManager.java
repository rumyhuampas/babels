package babelsManagers;

import babels.Babels;
import babelsObjects.MovementAdmin;
import babelsObjects.MovementTypes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CashRegisterManager {

    private JTable Table;
    private JLabel LblTotal;
    private TableModel Model;
    private JCheckBox chbxOpen;
    private JCheckBox chbxSales;
    private JCheckBox chbxCashInOut;
    public boolean RefreshingTable;
    private Date today;

    public CashRegisterManager(JTable table, JLabel lblTotal, JCheckBox Open, JCheckBox Sales, JCheckBox CashInOut) {
        this.Table = table;
        this.LblTotal = lblTotal;
        this.Model = this.Table.getModel();
        this.chbxOpen = Open;
        this.chbxSales = Sales;
        this.chbxCashInOut = CashInOut;
    }

    public void RefreshTable(Date DateBegining, Date DateFinal) throws SQLException {
        this.RefreshingTable = true;
        ClearTable();
        ArrayList optList = new ArrayList();

        Babels.mysql.Open();
        try {
            if (chbxOpen.isSelected()) {
                MovementTypes mt = new MovementTypes(babels.Babels.mysql.Conn);
                mt.Load(MovementTypes.MT_APER);
                optList.add(mt);
                mt = new MovementTypes(babels.Babels.mysql.Conn);
                mt.Load(MovementTypes.MT_CIERRE);
                optList.add(mt);
                mt = new MovementTypes(babels.Babels.mysql.Conn);
                mt.Load(MovementTypes.MT_CIERREPARC);
                optList.add(mt);
            }
            if (chbxSales.isSelected()) {
                MovementTypes mt = new MovementTypes(babels.Babels.mysql.Conn);
                mt.Load(MovementTypes.MT_VENTA_A);
                optList.add(mt);
                mt = new MovementTypes(babels.Babels.mysql.Conn);
                mt.Load(MovementTypes.MT_VENTA_B);
                optList.add(mt);
                mt = new MovementTypes(babels.Babels.mysql.Conn);
                mt.Load(MovementTypes.MT_VENTA_X);
                optList.add(mt);
            }
            if (chbxCashInOut.isSelected()) {
                MovementTypes mt = new MovementTypes(babels.Babels.mysql.Conn);
                mt.Load(MovementTypes.MT_DEPOSITO);
                mt = new MovementTypes(babels.Babels.mysql.Conn);
                mt.Load(MovementTypes.MT_EXTRACCION);
                optList.add(mt);
            }


            LoadMovements(DateBegining, DateFinal, optList);
        } finally {
            Babels.mysql.Close();
        }
        this.RefreshingTable = false;

    }

    private void ClearTable() {
        DefaultTableModel tm = (DefaultTableModel) this.Model;
        tm.getDataVector().removeAllElements();
    }

    private void LoadMovements(Date DateBegining, Date DateFinal, ArrayList optList) throws SQLException {

        float result = 0;
        Object[] rows = MovementAdmin.GetMovements(Babels.mysql.Conn, DateBegining, DateFinal, optList);
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

            result = result + ((Float) row[2]);
        }
        this.LblTotal.setText("$ " + result);

    }

    /*
     * public boolean OpenCashRegister(JTextField txtAmount) throws SQLException
     * { Babels.mysql.Open(); try { CashRegister cashReg = new
     * CashRegister(Babels.mysql.Conn); Movement cashMov = new
     * Movement(Babels.mysql.Conn); cashReg.IdActionType = 1; User currentUser =
     * Babels.session.CurrentUser; cashReg.IdUser = currentUser.getId(); //
     * cashReg.DatePosted = today. cashReg.IdPos = 0;
     *
     * if (cashReg.InsertCashRegister() == true) { cashMov.Total =
     * Float.parseFloat(txtAmount.getText()); cashMov.IdCashRegister =
     * cashReg.getId(); cashMov.MovementType = "CashRegister"; if
     * (cashMov.InsertCashRegister() == true) {
     * JOptionPane.showMessageDialog(null, "Caja abierta exitosamente", "Exito",
     * JOptionPane.INFORMATION_MESSAGE); return true; } else {
     * JOptionPane.showMessageDialog(null, "No se pudo abrir caja", "Error",
     * JOptionPane.ERROR_MESSAGE);
     *
     * return false; } } else { JOptionPane.showMessageDialog(null, "No se pudo
     * abrir caja", "Error", JOptionPane.ERROR_MESSAGE);
     *
     * return false; } } finally { Babels.mysql.Close(); } }
     */
}
