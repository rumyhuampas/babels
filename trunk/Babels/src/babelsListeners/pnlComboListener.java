package babelsListeners;

import babelsComponents.TransferableProductPanel;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
            TransferableProductPanel pnlProd = (TransferableProductPanel) tr.getTransferData(TransferableProductPanel.productPanelFlavor);
            if (dtde.isDataFlavorSupported(TransferableProductPanel.productPanelFlavor)) {
                if (!ProductIsListed(pnlProd)) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    this.prodList.add(pnlProd);
                    this.panel.add(pnlProd);
                    this.panel.revalidate();
                    this.panel.repaint();
                    dtde.dropComplete(true);
                } else {
                    dtde.rejectDrop();
                    JOptionPane.showMessageDialog(null, "El producto ya esta en la lista",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                dtde.rejectDrop();
            }
        } catch (Exception e) {
            e.printStackTrace();
            dtde.rejectDrop();
        }
    }

    private boolean ProductIsListed(TransferableProductPanel prodPanel) {
        for (int i = 0; i < prodList.size(); i++) {
            TransferableProductPanel pnl = (TransferableProductPanel) prodList.get(i);
            if (pnl.prodId == prodPanel.prodId) {
                return true;
            }
        }
        return false;
    }
}
