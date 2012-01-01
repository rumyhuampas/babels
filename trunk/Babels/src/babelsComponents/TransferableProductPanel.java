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

public class TransferableProductPanel extends JPanel implements Transferable {

    public Product prod;
    public static DataFlavor productPanelFlavor =
            new DataFlavor(TransferableProductPanel.class, "A Product Panel");
    public static DataFlavor[] supportedFlavors = {productPanelFlavor};

    public TransferableProductPanel(Product prod) {
        this.prod = prod;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel label = new JLabel(prod.Name, prod.GetImageIcon(), JLabel.CENTER);
        this.add(label);
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
            return this;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
