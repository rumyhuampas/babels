package babelsManagers;

import babels.Babels;
import babelsObjects.FormsFactory;
import babelsObjects.Product;
import babelsObjects.ProductsAdmin;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
        try {
            Object[] rows = ProductsAdmin.GetAllProducts(Babels.mysql.Conn);
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

    public ImageIcon GetProductImage(int width, int height) throws SQLException {
        Babels.mysql.Open();
        try {
            int row = this.Table.getSelectedRow();
            Product prod = new Product(Babels.mysql.Conn);
            prod.Load((Integer) this.Model.getValueAt(row, 0));
            return prod.GetImageIconResized(width,height);
        } finally {
            Babels.mysql.Close();
        }
    }

    public void EditProduct() {
        int row = this.Table.getSelectedRow();
        int prodId = (Integer) this.Model.getValueAt(row, 0);
        Class[] classParam = new Class[3];
        classParam[0] = java.awt.Frame.class;
        classParam[1] = boolean.class;
        classParam[2] = int.class;
        Object[] objectParam = new Object[3];
        objectParam[0] = new javax.swing.JFrame();
        objectParam[1] = true;
        objectParam[2] = prodId;
        FormsFactory.GetDialogForm("babelsForms.NewProduct", true, classParam, objectParam);
    }

    public void DeleteProduct() throws SQLException {
        Babels.mysql.Open();
        try {
            int row = this.Table.getSelectedRow();
            Product prod = new Product(Babels.mysql.Conn);
            prod.Load((Integer) this.Model.getValueAt(row, 0));
            if (prod.Delete() == false) {
                JOptionPane.showMessageDialog(null, "Error al borrar el producto",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            Babels.mysql.Close();
        }
    }
}
