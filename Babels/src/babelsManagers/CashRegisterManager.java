package babelsManagers;

import babels.Babels;
import babelsObjects.*;
import babelsRenderers.TableRendererFTextField;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
    private String currentQuery;
    public int moveId;
    private float subTotal;
    
    public CashRegisterManager(JTable table, JLabel lblTotal, JCheckBox Open, JCheckBox Sales, JCheckBox CashInOut) {
        this.Table = table;
        this.LblTotal = lblTotal;
        this.Model = this.Table.getModel();
        this.chbxOpen = Open;
        this.chbxSales = Sales;
        this.chbxCashInOut = CashInOut;
        this.currentQuery = "";
        this.chbxCashInOut.setSelected(true);
        this.chbxOpen.setSelected(true);
        this.chbxSales.setSelected(true);
        this.setColumnModel();
    }

    private void setColumnModel() {
        TableRendererFTextField formato = new TableRendererFTextField();
        for (int i = 0; i < this.Table.getColumnCount(); i++) {
            this.Table.getColumnModel().getColumn(i).setCellRenderer(formato);
        }
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
                mt = new MovementTypes(babels.Babels.mysql.Conn);
                mt.Load(MovementTypes.MT_CANCELATION);
                optList.add(mt);

            }
            if (chbxCashInOut.isSelected()) {
                MovementTypes mt = new MovementTypes(babels.Babels.mysql.Conn);
                mt.Load(MovementTypes.MT_DEPOSITO);
                optList.add(mt);
                mt = new MovementTypes(babels.Babels.mysql.Conn);
                mt.Load(MovementTypes.MT_EXTRACCION);
                optList.add(mt);
            }

            Calendar cal = new GregorianCalendar();
            Calendar calfin = new GregorianCalendar();
            cal.setTime(DateBegining);
            calfin.setTime(DateFinal);
            cal.add(Calendar.HOUR, 2);
            calfin.add(Calendar.HOUR, 2);
            Date Begining = cal.getTime();
            Date Final = calfin.getTime();
            LoadMovements(Begining, Final, optList);

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
              if (row[0].toString().equalsIgnoreCase(MovementTypes.MT_APER)){
                subTotal = 0 + (Float) row [2];
            } else{
                subTotal= subTotal + ((Float) row[2]);
            }
            rowTable = new Object[6];
            rowTable[1] = row[0];
            rowTable[2] = row[1];
            rowTable[3] = row[2];
            rowTable[4] = row[3];
            rowTable[0] = row[4];
            rowTable[5] = subTotal;          
            
            for (int i=0; i< optList.size(); i++){
                MovementTypes move;
                move= (MovementTypes)optList.get(i);
                if(row[0].toString().equalsIgnoreCase(move.Name)){
                  tm.addRow(rowTable);
                  result = result + ((Float) row[2]);
                }
            }
         //   ArrayList Type= new ArrayList();
         //   Type = optList;
           
        }
        this.LblTotal.setText("$ " + result);
    }

    public void doOperation(int opType) {
        Class[] classParam = new Class[3];
        classParam[0] = java.awt.Frame.class;
        classParam[1] = boolean.class;
        classParam[2] = int.class;
        Object[] objectParam = new Object[3];
        objectParam[0] = new javax.swing.JFrame();
        objectParam[1] = true;
        objectParam[2] = opType;
        FormsFactory.GetDialogForm("babelsForms.CashRegisterWindow", true, classParam, objectParam);
    }

    public void getDetail() throws SQLException {
        Babels.mysql.Open();
        try {
            int row = this.Table.getSelectedRow();
            moveId = (Integer) this.Model.getValueAt(row, 0);
            Movement move = new Movement(Babels.mysql.Conn);
            move.Load(moveId);
            if (move.Type.Name.equals(MovementTypes.MT_VENTA_A) || move.Type.Name.equals(MovementTypes.MT_VENTA_B)
                    || move.Type.Name.equals(MovementTypes.MT_VENTA_X)) {
                Class[] classParam = new Class[3];
                classParam[0] = java.awt.Frame.class;
                classParam[1] = boolean.class;
                classParam[2] = int.class;
                Object[] objectParam = new Object[3];
                objectParam[0] = new javax.swing.JFrame();
                objectParam[1] = true;
                objectParam[2] = moveId;
                FormsFactory.GetDialogForm("babelsForms.CashRegisterSaleDetail", true, classParam, objectParam);

            } else {
                JOptionPane.showMessageDialog(null, "Este Movimiento no es una venta",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            Babels.mysql.Close();
        }
    }

    public boolean doPrint() throws SQLException {
        /*
         * try { babels.Babels.mysql.Open(); Print print = new
         * Print(babels.Babels.mysql.Conn); print.Printer = "COMUN"; print.Data
         * = currentQuery; if (print.Save() == true) {
         * JOptionPane.showMessageDialog(null, "Impresion enviada", "Exito",
         * JOptionPane.INFORMATION_MESSAGE); return true; } else {
         * JOptionPane.showMessageDialog(null, "No se pudo enviar la impresion",
         * "Error", JOptionPane.ERROR_MESSAGE); return false; } } finally {
         * babels.Babels.mysql.Close(); }
         */
        return true;
    }
}
