package babelsManagers;

import babels.Babels;
import babelsListeners.tblProductsListener;
import babelsObjects.ProductsAdmin;
import babelsRenderers.JLabelCell;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class NewComboManager {

    private JTable Table;
    private TableModel Model;
    private boolean RefreshingTable;
    private tblProductsListener Listener;

    public NewComboManager(JTable table) {
        this.Table = table;
        this.Model = this.Table.getModel();
        JLabelCell labelCell = new JLabelCell();
        this.Table.getColumnModel().getColumn(3).setCellRenderer(labelCell);
        this.Table.setRowHeight(100);
        this.Listener = new tblProductsListener();
        
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
