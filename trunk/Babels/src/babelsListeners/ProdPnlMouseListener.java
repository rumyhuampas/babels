package babelsListeners;

import babelsComponents.TransferableProductPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class ProdPnlMouseListener extends MouseAdapter{
    
    private JPopupMenu PMenu;
    public TransferableProductPanel Panel;
    public ArrayList ProdList;
    public JPanel ComboPanel;
    
    public ProdPnlMouseListener(TransferableProductPanel panel){
        this.Panel = panel;
        PMenu = new JPopupMenu();
        JMenuItem mi = new JMenuItem("Remover Producto", 
                new ImageIcon(getClass().getResource("/babelsImages/delete.png")));
        ProdPnlPMenuMouseListener pmenuListener = new ProdPnlPMenuMouseListener(this);
        mi.addActionListener(pmenuListener);
        PMenu.add(mi);
        this.Panel.add(this.PMenu);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            this.PMenu.show(this.Panel, e.getX(), e.getY());
        }
    }
}
