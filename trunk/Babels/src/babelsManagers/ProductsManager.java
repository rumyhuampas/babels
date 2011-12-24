package babelsManagers;

import babels.Babels;
import babelsObjects.Product;
import babelsObjects.ProductsAdmin;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ProductsManager {
    private JTable Table;
    private TableModel Model;
    public boolean RefreshingTable;

    public ProductsManager(JTable table) {
        this.Table = table;
        this.Model = this.Table.getModel();
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
        try{
            Object[] rows = ProductsAdmin.GetAllProducts(Babels.mysql.Conn);
            Object[] row = null;
            DefaultTableModel tm = (DefaultTableModel) this.Model;
            for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
                row = (Object[]) rows[rowIdx];
                tm.addRow(row);
            }
        }
        finally{
            Babels.mysql.Close();
        }
    }
    
    public ImageIcon GetProductImage() throws SQLException{
        Babels.mysql.Open();
        try {
            int row = this.Table.getSelectedRow();
            Product prod = new Product(Babels.mysql.Conn);
            prod.Load((Integer)this.Model.getValueAt(row, 0));
            return prod.GetImageIcon();
        }
        finally{
            Babels.mysql.Close();
        }
    }
}
