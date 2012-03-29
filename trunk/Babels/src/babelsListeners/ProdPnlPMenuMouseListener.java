package babelsListeners;

import babelsComponents.TransferableProductPanel;
import babelsManagers.NewComboManager;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JPanel;

public class ProdPnlPMenuMouseListener implements ActionListener, ItemListener{
    
    private ArrayList ProdList;
    private JPanel ComboPanel;
    private TransferableProductPanel Panel;
    private NewComboManager Manager;
    
    public ProdPnlPMenuMouseListener(TransferableProductPanel panel, ArrayList prodList, JPanel comboPanel){
        this.ProdList = prodList;
        this.ComboPanel = comboPanel;
        this.Panel = panel;
        Manager=new NewComboManager(comboPanel);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Manager.setList(ProdList);
        Manager.DeletePnlProd(Panel);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
