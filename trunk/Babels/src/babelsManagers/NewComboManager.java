package babelsManagers;

import babels.Babels;
import babelsListeners.pnlComboListener;
import babelsListeners.tblProductsListener;
import babelsObjects.ProductsAdmin;
import babelsRenderers.JLabelCell;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class NewComboManager {

    private JTable Table;
    private TableModel Model;
    public boolean RefreshingTable;

    public NewComboManager(JTable table, JPanel comboPanel) {
        this.Table = table;
        this.Model = this.Table.getModel();
        JLabelCell labelCell = new JLabelCell();
        this.Table.getColumnModel().getColumn(3).setCellRenderer(labelCell);
        this.Table.setRowHeight(50);

        tblProductsListener TableListener = new tblProductsListener(table);
        comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.PAGE_AXIS));
        pnlComboListener pnlCombo = new pnlComboListener(comboPanel);
    }

    public void RefreshTable() throws SQLException {
        this.RefreshingTable = true;
        ClearTable();
        LoadProducts();
        this.RefreshingTable = false;
    }

    private void ClearTable() {
        DefaultTableModel tm = (DefaultTableModel) this.Model;
        tm.getDataVector().removeAllElements();
    }

    private void LoadProducts() throws SQLException {
        Babels.mysql.Open();
        try {
            Object[] rows = ProductsAdmin.GetAllProductsWithImage(Babels.mysql.Conn);
            Object[] row = null;
            DefaultTableModel tm = (DefaultTableModel) this.Model;
            for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
                row = (Object[]) rows[rowIdx];
                tm.addRow(row);
            }
        } finally {
            Babels.mysql.Close();
        }
    }
}
