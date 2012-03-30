package babelsManagers;

import babels.Babels;
import babelsComponents.TransferableProductPanel;
import babelsInterfaces.IBabelsDialog;
import babelsListeners.KeyListenerType;
import babelsListeners.ProdPnlMouseListener;
import babelsListeners.pnlComboListener;
import babelsListeners.tblProductsListener;
import babelsListeners.txtFieldListener;
import babelsObjects.Combo;
import babelsObjects.CombosProductsAdmin;
import babelsObjects.FormsFactory;
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
    public ArrayList ListProd, ListTransferable;
    public TransferableProductPanel PnlProd;
    public JPanel ComboPanel;
    public Boolean AddOrDel;

    public NewComboManager(JTable table, JPanel comboPanel) {
        this.Table = table;
        this.Model = this.Table.getModel();
        JLabelCell labelCell = new JLabelCell();
        this.Table.getColumnModel().getColumn(3).setCellRenderer(labelCell);
        this.Table.setRowHeight(50);

        tblProductsListener TableListener = new tblProductsListener(table);
        comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.PAGE_AXIS));
        pnlCombo = new pnlComboListener(comboPanel);
        ComboPanel = comboPanel;
    }

    public NewComboManager(JPanel PnlCombo) {

        ListProd = new ArrayList();
        this.ComboPanel = PnlCombo;
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

    public ArrayList GetComboProducts() throws SQLException {
        Babels.mysql.Open();
        try {
            ArrayList prodList = new ArrayList();
            TransferableProductPanel tprod;
            for (int i = 0; i < pnlCombo.prodList.size(); i++) {
                tprod = (TransferableProductPanel) pnlCombo.prodList.get(i);
                Product prod = new Product(Babels.mysql.Conn);
                prod.Load(tprod.prodId);
                prodList.add(prod);
            }
            return prodList;
        } finally {
            Babels.mysql.Close();
        }
    }

    public boolean SaveCombo(int ComboId, String name, String desc, String price, ArrayList products) throws SQLException {
        Babels.mysql.Open();
        try {
            if (products.size() > 0) {
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
            } else {
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
            Object[] rowTable = null;
            DefaultTableModel tm = (DefaultTableModel) this.Model;
            for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
                row = (Object[]) rows[rowIdx];
                rowTable= new Object[4];
                rowTable[0] = row[0];
                rowTable[1] = row[1];
                rowTable[2] = row[3];
                if (row.length>4){
                     rowTable[3] = row[4];
                     
                }
               tm.addRow(rowTable);
            }
        } finally {
            Babels.mysql.Close();
        }
    }

    public void setPnlProd(TransferableProductPanel pnlProd) {
        this.PnlProd = pnlProd;
    }

    public TransferableProductPanel getPnlProd() {
        return (PnlProd);
    }

    public void setListPnlProd(TransferableProductPanel pnlProd) {
        this.ListProd.add(pnlProd);
    }

    public void setList(ArrayList listProd) {
        this.ListProd = listProd;
    }

    public ArrayList getList() {
        return (this.ListProd);
    }

    public void Paint(Boolean AddOrDel) {
        if (AddOrDel == true) {
            ComboPanel.removeAll();
            for (int i = 0; i < ListProd.size(); i++) {
                TransferableProductPanel pnl = (TransferableProductPanel) ListProd.get(i);
                this.ComboPanel.add(pnl);
                this.ComboPanel.revalidate();
                this.ComboPanel.repaint();
            }
        } else {
            if (ListProd.size() > 0) {
                ComboPanel.removeAll();
                for (int i = 0; i < ListProd.size(); i++) {
                    TransferableProductPanel pnl = (TransferableProductPanel) ListProd.get(i);
                    this.ComboPanel.add(pnl);
                    this.ComboPanel.revalidate();
                    this.ComboPanel.repaint();
                }
            } else {
                ComboPanel.removeAll();
                this.ComboPanel.revalidate();
                this.ComboPanel.repaint();
            }
        }
    }

    public void DeletePnlProd(TransferableProductPanel pnlProd) {

        for (int i = 0; i < this.getList().size(); i++) {

            TransferableProductPanel pnl = (TransferableProductPanel) ListProd.get(i);
            if (pnl.prodId == pnlProd.prodId) {
                this.ListProd.remove(i);

                AddOrDel = false;
                Paint(AddOrDel);
            }
        }


    }

    public void LoadCombo(int ComboId, JTextField txtName,
            JTextArea txtDesc, JTextField txtPrice) throws SQLException {
        Babels.mysql.Open();
        try {
            Combo Comb = new Combo(Babels.mysql.Conn);
            Comb.Load(ComboId);
            txtName.setText(Comb.Name);
            txtDesc.setText(Comb.Desc);
            txtPrice.setText(String.valueOf(Comb.Price));
            ArrayList ListProducts = CombosProductsAdmin.GetComboProducts(Babels.mysql.Conn, Comb);


            ListProd = this.ProdToTransferable(ListProducts);
            this.setList(ListProd);
            //   ComboPanel.setLayout(new BoxLayout(ComboPanel, BoxLayout.PAGE_AXIS));
            pnlCombo = new pnlComboListener(ComboPanel, this.getList());
            AddOrDel = true;
            this.Paint(AddOrDel);
            this.ActiveListenerPnlProd(ListProducts, ListProd);

        } finally {
            Babels.mysql.Close();
        }
    }

    public ArrayList ProdToTransferable(ArrayList listProd) {
        ListTransferable = new ArrayList();
        for (int i = 0; i < listProd.size(); i++) {
            ListTransferable.add(new TransferableProductPanel((Product) listProd.get(i)));

        }
        return (ListTransferable);
    }

    public void ActiveListenerPnlProd(ArrayList listProd, ArrayList listTransferable) {
        for (int i = 0; i < listProd.size(); i++) {
            ProdPnlMouseListener prodPnlML = new ProdPnlMouseListener((TransferableProductPanel) listTransferable.get(i), listTransferable, this.ComboPanel);
            TransferableProductPanel tpp = (TransferableProductPanel) listTransferable.get(i);
            tpp.addMouseListener(prodPnlML);
        }
    }
}
