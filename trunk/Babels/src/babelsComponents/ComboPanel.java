package babelsComponents;

import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class ComboPanel implements DropTargetListener {

    private JPanel panel;
    public DropTarget dropTarget;
    private boolean acceptableType; // Indicates whether data is acceptable
    private DataFlavor targetFlavor; // Flavor to use for transfer

    public ComboPanel(JPanel panel) {
        this.panel = panel;

        dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY_OR_MOVE,
                this, true, null);
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        Logger.getLogger(ComboPanel.class.getName()).log(Level.INFO, "dragEnter");
        /*DnDUtils.debugPrintln("dragEnter, drop action = "
        + DnDUtils.showActions(dtde.getDropAction()));*/
        checkTransferType(dtde);
        acceptOrRejectDrag(dtde);
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        Logger.getLogger(ComboPanel.class.getName()).log(Level.INFO, "dragExit");
        //DnDUtils.debugPrintln("DropTarget dragExit");
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        Logger.getLogger(ComboPanel.class.getName()).log(Level.INFO, "dragOver");
        /*DnDUtils.debugPrintln("DropTarget dragOver, drop action = "
        + DnDUtils.showActions(dtde.getDropAction()));*/
        acceptOrRejectDrag(dtde);
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        Logger.getLogger(ComboPanel.class.getName()).log(Level.INFO, "dropActionChanged");
        /*DnDUtils.debugPrintln("DropTarget dropActionChanged, drop action = "
        + DnDUtils.showActions(dtde.getDropAction()));*/
        acceptOrRejectDrag(dtde);
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        Logger.getLogger(ComboPanel.class.getName()).log(Level.INFO, "drop");
        /*DnDUtils.debugPrintln("DropTarget drop, drop action = "
        + DnDUtils.showActions(dtde.getDropAction()));*/
        if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
            dtde.acceptDrop(dtde.getDropAction());
            Transferable transferable = dtde.getTransferable();

            try {
                boolean result = dropComponent(transferable);
                dtde.dropComplete(result);
                //DnDUtils.debugPrintln("Drop completed, success: " + result);
            } catch (Exception e) {
                //DnDUtils.debugPrintln("Exception while handling drop " + e);
                dtde.dropComplete(false);
            }
        } else {
            //DnDUtils.debugPrintln("Drop target rejected drop");
            dtde.rejectDrop();
        }
    }

    protected boolean acceptOrRejectDrag(DropTargetDragEvent dtde) {
        int dropAction = dtde.getDropAction();
        int sourceActions = dtde.getSourceActions();
        boolean acceptedDrag = false;

        Logger.getLogger(ComboPanel.class.getName()).log(Level.INFO, "acceptOrRejectDrag");
        /*DnDUtils.debugPrintln("\tSource actions are "
        + DnDUtils.showActions(sourceActions) + ", drop action is "
        + DnDUtils.showActions(dropAction));*/

        if (!acceptableType
                || (sourceActions & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
            //DnDUtils.debugPrintln("Drop target rejecting drag");
            dtde.rejectDrag();
        } else if ((dropAction & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
            //DnDUtils.debugPrintln("Drop target offering COPY");
            dtde.acceptDrag(DnDConstants.ACTION_COPY);
            acceptedDrag = true;
        } else {
            //DnDUtils.debugPrintln("Drop target accepting drag");
            dtde.acceptDrag(dropAction);
            acceptedDrag = true;
        }

        return acceptedDrag;
    }

    protected void checkTransferType(DropTargetDragEvent dtde) {
        acceptableType = false;
        DataFlavor[] fl = dtde.getCurrentDataFlavors();
        for (int i = 0; i < fl.length; i++) {
            Class dataClass = fl[i].getRepresentationClass();

            if (Component.class.isAssignableFrom(dataClass)) {
                // This flavor returns a Component - accept it.
                targetFlavor = fl[i];
                acceptableType = true;
                break;
            }
        }

        //DnDUtils.debugPrintln("File type acceptable - " + acceptableType);
    }

    protected boolean dropComponent(Transferable transferable)
            throws IOException, UnsupportedFlavorException {
        Object o = transferable.getTransferData(targetFlavor);
        if (o instanceof Component) {
            /*DnDUtils.debugPrintln("Dragged component class is "
            + o.getClass().getName());*/
            panel.add((Component) o);
            panel.validate();
            return true;
        }
        return false;
    }
}
