package babelsListeners;

import babelsInterfaces.IBabelsDialog;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class txtFieldListener implements KeyListener {

    private int Type;
    private IBabelsDialog Dialog;

    public txtFieldListener(int keyListenerType, IBabelsDialog dialog) {
        this.Type = keyListenerType;
        this.Dialog = dialog;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (c != KeyEvent.VK_ENTER) {
            if (this.Type == KeyListenerType.NUMBERS_ONLY) {
                if (!((c >= '0') && (c <= '9')
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE)
                        || (c == KeyEvent.VK_PERIOD))) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
            if (this.Type == KeyListenerType.NO_SPACES) {
                if (c == KeyEvent.VK_SPACE) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
            if (this.Type == KeyListenerType.LETTERS_ONLY) {
                if (!((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z')
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        } else {
            if (this.Dialog != null) {
                try {
                    this.Dialog.ClickOKButton();
                } catch (Exception ex) {
                    Logger.getLogger(txtFieldListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
