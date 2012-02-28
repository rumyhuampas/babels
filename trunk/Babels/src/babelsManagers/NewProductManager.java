package babelsManagers;

import babels.Babels;
import babelsComponents.ImageManagement;
import babelsFilters.ImagesFilter;
import babelsForms.NewProduct;
import babelsInterfaces.IBabelsDialog;
import babelsListeners.KeyListenerType;
import babelsListeners.txtFieldListener;
import babelsObjects.Product;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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

    public boolean SaveProduct(int ProdId, String name, String desc, String price, String imagePath) throws SQLException {
        Babels.mysql.Open();
        try {
            Product prod = new Product(Babels.mysql.Conn);
            if (ProdId != -1) {
                prod.Load(ProdId);
            }
            prod.Name = name;
            prod.Desc = desc;
            prod.Price = Float.parseFloat(price);
            prod.SetImage(imagePath);
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
            ImageIcon img = prod.GetImageIcon();
            if (img != null) {
                lblImage.setIcon(img);
                lblImage.setText("");
            }
        } finally {
            Babels.mysql.Close();
        }
    }
}
