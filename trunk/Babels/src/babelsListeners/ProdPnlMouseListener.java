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
    public TransferableProductPanel prdPanel;
    public ArrayList ProdList;
    public JPanel ComboPanel;
    
    public ProdPnlMouseListener(TransferableProductPanel panel, ArrayList prodList, JPanel comboPanel){
        this.prdPanel = panel;
        this.ProdList= prodList;
        this.ComboPanel=comboPanel;
        PMenu = new JPopupMenu();
        JMenuItem mi = new JMenuItem("Remover Producto", 
                new ImageIcon(getClass().getResource("/babelsImages/Delete.png")));
        ProdPnlPMenuMouseListener pmenuListener = new ProdPnlPMenuMouseListener(prdPanel, prodList, comboPanel);
        mi.addActionListener(pmenuListener);
        PMenu.add(mi);
        this.prdPanel.add(this.PMenu);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            this.PMenu.show(this.prdPanel, e.getX(), e.getY());
        }
    }
}
