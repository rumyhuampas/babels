package babelsListeners;

import babelsComponents.TransferableProductPanel;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JPanel;

public class ProdPnlPMenuMouseListener implements ActionListener, ItemListener{
    
    private ArrayList ProdList;
    private JPanel ComboPanel;
    private TransferableProductPanel Panel;
    
    public ProdPnlPMenuMouseListener(ProdPnlMouseListener prodPanelML){
        this.ProdList = prodPanelML.ProdList;
        this.ComboPanel = prodPanelML.ComboPanel;
        this.Panel = prodPanelML.Panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
     
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
