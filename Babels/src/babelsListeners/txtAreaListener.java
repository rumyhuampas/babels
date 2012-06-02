package babelsListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextArea;

public class txtAreaListener implements KeyListener {
    
    private JTextArea Area;    
        
    public txtAreaListener(JTextArea area){
        Area = area;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_TAB) {
                System.out.println(e.getModifiers());
                if(e.getModifiers() > 0) Area.transferFocusBackward();
                else Area.transferFocus(); 
                e.consume();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}