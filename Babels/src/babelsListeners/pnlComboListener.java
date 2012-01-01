package babelsListeners;

import babelsComponents.TransferableProduct;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

public class pnlComboListener extends DropTargetAdapter {

    private JPanel panel;
    private ArrayList prodList;

    public pnlComboListener(JPanel panel) {
        this.panel = panel;
        this.prodList = new ArrayList();
        DropTarget dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY,
                this, true, null);
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        try {
            Transferable tr = dtde.getTransferable();
            JPanel pnlProd = (JPanel) tr.getTransferData(TransferableProduct.productPanelFlavor);
            if (dtde.isDataFlavorSupported(TransferableProduct.productPanelFlavor)) {
                if (!ProductIsListed(pnlProd)){
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    this.prodList.add(pnlProd);
                    SetProductBounds(pnlProd);
                    this.panel.add(pnlProd);
                    this.panel.revalidate();
                    this.panel.repaint(); 
                    //this.panel.setSize(this.panel.getWidth(), (50 * this.prodList.size()) + 10);
                    dtde.dropComplete(true);
                }
                else{
                    dtde.rejectDrop();
                }
            }else{
                dtde.rejectDrop();
            }
        } catch (Exception e) {
            e.printStackTrace();
            dtde.rejectDrop();
        }
    }
    
    private boolean ProductIsListed(JPanel prodPanel){
        if(prodList.contains(prodPanel)){
            return true;
        }
        else{
            return false;
        }
    }
    
    private void SetProductBounds(JPanel prodPanel){
        prodPanel.setBounds(5, 
                5 + (50 * (this.prodList.size() - 1) + 5),
                this.panel.getWidth() - 10, 50);
    }
}
