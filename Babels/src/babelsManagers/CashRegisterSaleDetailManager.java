package babelsManagers;

import babels.Babels;
import babelsObjects.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CashRegisterSaleDetailManager {

    private JTable tableDetail;
    private JTextField txtAmount;
    private JTextField txtDateTime;
    private JTextField txtMovementId;
    private JTextField txtMovementType;
    private TableModel ModelTable;
    public int moveId;

    public CashRegisterSaleDetailManager(int moveId, JTable tblSaleDetail, JTextField txtAmount, JTextField txtDateTime, JTextField txtMovementId, JTextField txtMovementType) {
        this.moveId = moveId;
        this.tableDetail = tblSaleDetail;
        this.ModelTable = tableDetail.getModel();
        this.txtAmount = txtAmount;
        this.txtDateTime = txtDateTime;
        this.txtMovementId = txtMovementId;
        this.txtMovementType = txtMovementType;
    }

    public void LoadMovement() throws SQLException {
        Babels.mysql.Open();
        try {
            Sale sale = new Sale(Babels.mysql.Conn);
            sale.Load(moveId);
            this.txtAmount.setText("" + sale.Amount);
            this.txtDateTime.setText(sale.DatePosted.toString());
            this.txtMovementId.setText("" + sale.Id);
            this.txtMovementType.setText(sale.Type.Name);
            this.loadDetail(sale.Items);
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
        LoadMovement();
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
