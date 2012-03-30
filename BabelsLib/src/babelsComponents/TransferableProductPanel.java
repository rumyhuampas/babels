package babelsComponents;

import babelsObjects.Product;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TransferableProductPanel extends JPanel implements Transferable {

    public int prodId;
    public static DataFlavor productPanelFlavor =
            new DataFlavor(TransferableProductPanel.class, "A Product Panel");
    public static DataFlavor[] supportedFlavors = {productPanelFlavor};

    public TransferableProductPanel(Product prod) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        GridBagConstraints c2 = new GridBagConstraints();
        this.prodId = prod.getId();
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        JLabel label = new JLabel();
        label.setBounds(0,0,120,90);
        ImageIcon img = prod.GetImageIconResized(label.getWidth(),label.getHeight()); 
        label.setIcon(img);
        label.setText(null);
        c2.weightx = 0.5;
        c2.fill = GridBagConstraints.HORIZONTAL;
        c2.gridx = 0;
        c2.gridy = 1;
        c2.gridwidth = 2; 
       
        this.add(label, c2);
       
         JLabel labelText=new JLabel(prod.Name);
         labelText.setBounds(0, 0, 60, 90);
         labelText.setFont(new java.awt.Font("Tahoma", 1, 11));
         c1.weightx = 0.5;
         c1.fill = GridBagConstraints.HORIZONTAL;
         c1.gridx = 2;
         c1.gridy = 1;
        
         this.add(labelText, c1);
    
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
