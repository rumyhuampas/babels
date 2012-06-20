package babelsManagers;

import babels.Babels;
import babelsFilters.ImagesFilter;
import babelsForms.NewProduct;
import babelsInterfaces.IBabelsDialog;
import babelsListeners.KeyListenerType;
import babelsListeners.txtAreaListener;
import babelsListeners.txtFieldListener;
import babelsObjects.Product;
import java.io.File;
import java.sql.SQLException;
import javax.swing.*;

public class NewProductManager {

    public File ChooseProductImage(NewProduct newProductWindow) {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Elija la imagen del producto");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.addChoosableFileFilter(new ImagesFilter());
        if (fc.showOpenDialog(newProductWindow) == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        } else {
            return null;
        }
    }

    public void SetFieldsListeners(JTextField txtName, JTextField txtPrice, JTextArea txtaDesc, IBabelsDialog dialog) {
        txtName.addKeyListener(new txtFieldListener(KeyListenerType.ANY, dialog));
        txtPrice.addKeyListener(new txtFieldListener(KeyListenerType.NUMBERS_ONLY, dialog));
        txtaDesc.addKeyListener(new txtAreaListener(txtaDesc));
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

    public boolean SaveProduct(int ProdId, String name, String desc, String price, String costPrice,
             String pricePackaging, String iva,int idKitchen, int idCategories, String imagePath, boolean ImageChanged) throws SQLException {
        Babels.mysql.Open();
        try {
            Product prod = new Product(Babels.mysql.Conn);
            if (ProdId != -1) {
                prod.Load(ProdId);
            }
            prod.Name = name;
            prod.Desc = desc;
            prod.Price = Float.parseFloat(price);
            prod.CostPrice = Float.parseFloat(costPrice);
            prod.PricePackaging = Float.parseFloat(pricePackaging);
            prod.Iva = Float.parseFloat(iva);
            prod.Idkitchen = idKitchen + 1;
            prod.IdCategories = idCategories;
            prod.SetImage(imagePath);
            prod.ImageChanged=ImageChanged;
            if (prod.Exists() == false) {
                if (prod.Save() == true) {
                    JOptionPane.showMessageDialog(null, "Producto guardado",
                            "Exito", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar el producto",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "El producto ya existe",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } finally {
            Babels.mysql.Close();
        }
    }

    public void LoadProduct(int prodId, JTextField txtName,
            JTextArea txtDesc, JTextField txtPrice, JLabel lblImage) throws SQLException {
        Babels.mysql.Open();
        try {
            Product prod = new Product(Babels.mysql.Conn);
            prod.Load(prodId);
            txtName.setText(prod.Name);
            txtDesc.setText(prod.Desc);
            txtPrice.setText(String.valueOf(prod.Price));
            ImageIcon img = prod.GetImageIconResized(lblImage.getWidth(),lblImage.getHeight());
            if (img != null) {
                lblImage.setIcon(img);
                lblImage.setText("");
            }
        } finally {
            Babels.mysql.Close();
        }
    }

    public void LoadProduct(int prodId, JTextField txtName, JTextArea txtaDesc, JTextField txtPrice, JLabel lblImg, JTextField txtCostPrice, JTextField txtIva, JTextField txtPricePackaging, JComboBox comboCategories, JComboBox comboKitchen) throws SQLException {
         Babels.mysql.Open();
        try {
            Product prod = new Product(Babels.mysql.Conn);
            prod.Load(prodId);
            txtName.setText(prod.Name);
            txtaDesc.setText(prod.Desc);
            txtPrice.setText(String.valueOf(prod.Price));
            txtCostPrice.setText(String.valueOf(prod.CostPrice));
            txtIva.setText(String.valueOf(prod.Iva));
            txtPricePackaging.setText(String.valueOf(prod.PricePackaging));
            comboCategories.setSelectedIndex(prod.IdCategories);
            comboKitchen.setSelectedItem(prod.Idkitchen);
            
            ImageIcon img = prod.GetImageIconResized(lblImg.getWidth(),lblImg.getHeight());
            if (img != null) {
                lblImg.setIcon(img);
                lblImg.setText("");
            }
        } finally {
            Babels.mysql.Close();
        }
    }
}
