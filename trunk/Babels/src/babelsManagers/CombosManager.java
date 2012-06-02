/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package babelsManagers;

import babelsObjects.CombosAdmin;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import babels.Babels;
import babelsObjects.Combo;
import babelsObjects.FormsFactory;
import babelsObjects.ProductsAdmin;
import babelsRenderers.JLabelCell;
import javax.swing.JOptionPane;

/**
 *
 * @author BGH
 */
public class CombosManager {

    private JTable TableCombos, TableProducts;
    private TableModel ModelTableCombos, ModelTableProducts;
    private Boolean RefreshingTableCombos, RefreshingTableProducts;
    public Integer IdCombo;

    public CombosManager(JTable tableCombos, JTable tableProducts) {
        this.TableCombos = tableCombos;
        this.ModelTableCombos = TableCombos.getModel();
        this.TableProducts = tableProducts;
        this.ModelTableProducts = TableProducts.getModel();
        JLabelCell labelCell = new JLabelCell();
        this.TableProducts.getColumnModel().getColumn(3).setCellRenderer(labelCell);
        this.TableProducts.setRowHeight(50);
    }

    public void RefreshTableCombos() throws SQLException {
        this.RefreshingTableCombos = true;
        ClearTableCombos();
        LoadCombos();
        this.RefreshingTableCombos = false;
    }

    private void ClearTableCombos() {
        DefaultTableModel tm = (DefaultTableModel) this.ModelTableCombos;
        tm.getDataVector().removeAllElements();
    }

    private void ClearTableProducts() {
        DefaultTableModel tm = (DefaultTableModel) this.ModelTableProducts;
        tm.getDataVector().removeAllElements();
    }

    private void LoadCombos() throws SQLException {
        Babels.mysql.Open();
        try {
            Object[] rows = CombosAdmin.GetAllCombos(Babels.mysql.Conn);
            Object[] row = null;
            DefaultTableModel tm = (DefaultTableModel) this.ModelTableCombos;
            for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
                row = (Object[]) rows[rowIdx];
                tm.addRow(row);
            }
        } finally {
            Babels.mysql.Close();
        }
    }

    public void DeleteCombo() throws SQLException {
        Babels.mysql.Open();
        try {
            int row = this.TableCombos.getSelectedRow();
            Combo Comb = new Combo(Babels.mysql.Conn);
            Comb.Load((Integer) this.ModelTableCombos.getValueAt(row, 0));
            if (Comb.Delete() == false) {
                JOptionPane.showMessageDialog(null, "Error al borrar el producto",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            Babels.mysql.Close();
        }
    }

    public void EditCombo() {
        int row = this.TableCombos.getSelectedRow();
        IdCombo = (Integer) this.TableCombos.getModel().getValueAt(row, 0);
        Class[] classParam = new Class[3];
        classParam[0] = java.awt.Frame.class;
        classParam[1] = boolean.class;
        classParam[2] = int.class;
        Object[] objectParam = new Object[3];
        objectParam[0] = new javax.swing.JFrame();
        objectParam[1] = true;
        objectParam[2] = IdCombo;
        FormsFactory.GetDialogForm("babelsForms.NewCombo", true, classParam, objectParam);
    }

    public void GetTableProducts() throws SQLException {
        int rowCombo = this.TableCombos.getSelectedRow();
        int ComboId = (Integer) this.ModelTableCombos.getValueAt(rowCombo, 0);
        try {
            Babels.mysql.Open();
        } catch (SQLException ex) {
            Logger.getLogger(CombosManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.ClearTableProducts();
            Object[] rows = ProductsAdmin.LoadProductDetailWithImage(ComboId, Babels.mysql.Conn);
            Object[] row = null;
            DefaultTableModel tm = (DefaultTableModel) this.ModelTableProducts;
            for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
                row = (Object[]) rows[rowIdx];
                tm.addRow(row);
            }
        } finally {
            Babels.mysql.Close();
        }
    }
}
