package babelsManagers;

import babels.Babels;
import babelsObjects.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CashRegisterDetailManager {

    private JTextArea taDescription;
    private JTable tableDetail;
    private JTextField txtAmount;
    private JTextField txtDateTime;
    private JTextField txtMovementId;
    private JTextField txtMovementType;
    private TableModel ModelTable;
    public int moveId;

    public CashRegisterDetailManager(int moveId, JTable tblSaleDetail, JTextField txtAmount, JTextField txtDateTime, JTextField txtMovementId, JTextField txtMovementType) {
        this.moveId = moveId;
        this.tableDetail = tblSaleDetail;
        this.ModelTable = tableDetail.getModel();
        this.txtAmount = txtAmount;
        this.txtDateTime = txtDateTime;
        this.txtMovementId = txtMovementId;
        this.txtMovementType = txtMovementType;
    }

    public CashRegisterDetailManager(int moveId, JTextArea taDescription, JTextField txtAmount, JTextField txtDateTime, JTextField txtMovementId, JTextField txtMovementType) {
        this.moveId = moveId;
        this.taDescription = taDescription;
        this.txtAmount = txtAmount;
        this.txtDateTime = txtDateTime;
        this.txtMovementId = txtMovementId;
        this.txtMovementType = txtMovementType;
    }

    public void LoadMovementSale() throws SQLException {
        Babels.mysql.Open();
        try {
            Sale sale = new Sale(Babels.mysql.Conn);
            sale.Load(moveId);
            this.txtAmount.setText("" + sale.Amount);
            this.txtDateTime.setText(sale.DateTime.toString());
            this.txtMovementId.setText("" + sale.Id);
            this.txtMovementType.setText(sale.Type.Name);
            this.loadDetail(sale.Items);
        } finally {
            Babels.mysql.Close();
        }
    }

    public void LoadMovement() throws SQLException {
        Babels.mysql.Open();
        try {
            Movement move = new Movement(Babels.mysql.Conn);
            Cancelation cancel = new Cancelation(Babels.mysql.Conn);
            move.Load(moveId);
            this.txtAmount.setText("" + move.Amount);
            this.txtDateTime.setText(move.DateTime.toString());
            this.txtMovementId.setText("" + move.Id);
            this.txtMovementType.setText(move.Type.Name);
            if (cancel.LoadByCanceller(moveId)) {
             //   Sale sale = new Sale(Babels.mysql.Conn);
             //   sale = cancel.CanceledMove;
                this.taDescription.setText("Cancelacion del movimiento: " + (cancel.CanceledMove.getId()));
            } else {
                this.taDescription.setText(move.Description);
            }
        } finally {
            Babels.mysql.Close();
        }
    }

    private void ClearTable() {
        DefaultTableModel tm = (DefaultTableModel) this.ModelTable;
        tm.getDataVector().removeAllElements();
    }

    public void RefreshTable() throws SQLException {

        ClearTable();
        LoadMovementSale();
    }

    private void loadDetail(ArrayList Items) {
        DefaultTableModel tm = (DefaultTableModel) this.ModelTable;
        Object[] rowTable = null;
        for (int i = 0; i < Items.size(); i++) {
            Object[] obj = (Object[]) Items.get(i);
            String itemType = (String) obj[0];
            rowTable = new Object[3];
            rowTable[0] = itemType;
            if (itemType == SalesItemsAdmin.IT_COMBO) {
                Combo cmb = (Combo) obj[1];
                rowTable[1] = cmb.Name;
                rowTable[2] = cmb.Price;
            } else {
                Product prd = (Product) obj[1];
                rowTable[1] = prd.Name;
                rowTable[2] = prd.Price;
            }

            tm.addRow(rowTable);
        }
    }
}
