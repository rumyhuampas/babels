package babelsComponents;

import babelsObjects.Product;
import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TransferableProduct implements Transferable {

    private JPanel panel;
    public static DataFlavor productPanelFlavor =
            new DataFlavor(TransferableProduct.class, "A Product Panel");
    public static DataFlavor[] supportedFlavors = {productPanelFlavor};

    public TransferableProduct(Product prod) {
        this.panel = new JPanel();
        this.panel.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel label = new JLabel(prod.Name, prod.GetImageIcon(), JLabel.CENTER);
        //label.setSize(50, 40);
        label.setVisible(true);
        this.panel.add(label);
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        if (flavor.equals(productPanelFlavor) || flavor.equals(DataFlavor.stringFlavor)) {
            return true;
        }
        return false;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(productPanelFlavor)) {
            return panel;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
