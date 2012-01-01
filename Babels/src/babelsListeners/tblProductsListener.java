package babelsListeners;

import babels.Babels;
import babelsComponents.TransferableProductPanel;
import babelsObjects.Product;
import java.awt.Cursor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

public class tblProductsListener implements DragGestureListener {

    public tblProductsListener(JTable table) {
        DragSource ds = new DragSource();
        ds.createDefaultDragGestureRecognizer(table,
                DnDConstants.ACTION_COPY, this);
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        Cursor cursor = null;
        if (dge.getDragAction() == DnDConstants.ACTION_COPY) {
            cursor = DragSource.DefaultCopyDrop;
        }

        JTable table = (JTable) dge.getComponent();
        int rowIdx = table.getSelectedRow();
        Product prod = null;
        try {
            Babels.mysql.Open();
            try {
                prod = new Product(Babels.mysql.Conn);
                prod.Load((Integer) table.getModel().getValueAt(rowIdx, 0));
            } finally {
                Babels.mysql.Close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(tblProductsListener.class.getName()).log(Level.SEVERE, null, ex);
        }

        dge.startDrag(cursor, new TransferableProductPanel(prod));
    }
}
