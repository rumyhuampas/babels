package babelsManagers;

import babels.Babels;
import babelsComponents.TransferableProductPanel;
import babelsInterfaces.IBabelsDialog;
import babelsListeners.KeyListenerType;
import babelsListeners.pnlComboListener;
import babelsListeners.tblProductsListener;
import babelsListeners.txtFieldListener;
import babelsObjects.Combo;
import babelsObjects.Product;
import babelsObjects.ProductsAdmin;
import babelsRenderers.JLabelCell;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class NewComboManager {

    private JTable Table;
    private TableModel Model;
    public boolean RefreshingTable;
    private pnlComboListener pnlCombo;
    public ArrayList ListProd;
    public TransferableProductPanel PnlProd;
    public JPanel ComboPanel;

    public NewComboManager(JTable table, JPanel comboPanel) {
        this.Table = table;
        this.Model = this.Table.getModel();
        JLabelCell labelCell = new JLabelCell();
        this.Table.getColumnModel().getColumn(3).setCellRenderer(labelCell);
        this.Table.setRowHeight(50);

        tblProductsListener TableListener = new tblProductsListener(table);
        comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.PAGE_AXIS));
        pnlCombo = new pnlComboListener(comboPanel);
    }
      public NewComboManager(JPanel PnlCombo) {
        ListProd= new ArrayList();
        this.ComboPanel=PnlCombo;
    }
    
    public void SetFieldsListeners(JTextField txtName, JTextField txtPrice, IBabelsDialog dialog) {
        txtName.addKeyListener(new txtFieldListener(KeyListenerType.ANY, dialog));
        txtPrice.addKeyListener(new txtFieldListener(KeyListenerType.NUMBERS_ONLY, dialog));
    }
    
    public boolean CheckFields(JTextField txtName, JTextField txtPrice) {
        if (!txtName.getText().equals("") && !txtPrice.getText().equals("")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Debe completar Nombre y Precio",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public ArrayList GetComboProducts() throws SQLException{
        Babels.mysql.Open();
        try{
            ArrayList prodList = new ArrayList();
            TransferableProductPanel tprod;
            for (int i=0;i<pnlCombo.prodList.size(); i++){
                tprod = (TransferableProductPanel)pnlCombo.prodList.get(i);
                Product prod = new Product(Babels.mysql.Conn);
                prod.Load(tprod.prodId);
                prodList.add(prod);
            }
            return prodList;
        }
        finally{
            Babels.mysql.Close();
        }
    } 
    
    public boolean SaveCombo(int ComboId, String name, String desc, String price, ArrayList products) throws SQLException {
        Babels.mysql.Open();
        try {
            if (products.size() > 0){
                Combo combo = new Combo(Babels.mysql.Conn);
                if (ComboId != -1) {
                    combo.Load(ComboId);
                }
                combo.Name = name;
                combo.Desc = desc;
                combo.Price = Float.parseFloat(price);
                combo.Products = products;
                if (combo.Exists() == false) {
                    if (combo.Save() == true) {
                        JOptionPane.showMessageDialog(null, "Combo guardado",
                                "Exito", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo guardar el combo",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "El combo ya existe",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Debe elegir al menos un producto",
                    "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } finally {
            Babels.mysql.Close();
        }
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
    public void setPnlProd(TransferableProductPanel pnlProd){
        this.PnlProd=pnlProd;
    }
    public TransferableProductPanel getPnlProd(){
        return (PnlProd);
}
    public void setListPnlProd(TransferableProductPanel pnlProd){
        this.ListProd.add(pnlProd);
    }
    public void setList(ArrayList listProd){
         this.ListProd=listProd;
     }
    public void Paint(){
        ComboPanel.removeAll();
       for (int i = 0; i < ListProd.size(); i++) {
        TransferableProductPanel pnl = (TransferableProductPanel) ListProd.get(i);    
        this.ComboPanel.add(pnl);
        this.ComboPanel.revalidate();
        this.ComboPanel.repaint();
       }
    }
    public void DeletePnlProd(TransferableProductPanel pnlProd){
         for (int i = 0; i < ListProd.size(); i++) {
            TransferableProductPanel pnl = (TransferableProductPanel) ListProd.get(i);
            if (pnl.prodId == pnlProd.prodId) {
                this.ListProd.remove(i);
                Paint();
            } 
        }
        
    
    
    }
    
}
