package babelsManagers;

import babels.Babels;
import babelsForms.Main;
import babelsObjects.User;
import java.awt.Frame;
import javax.swing.JMenu;

public class MainManager {
    
    public void SetWindowBasicSettings(Main mainWindow){
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
    
    public void HideUsersIfNotAdmin(JMenu usersMenuItem){
        User currentUser = Babels.session.CurrentUser;
        if (!currentUser.IsAdmin){
            usersMenuItem.setVisible(false);
        }
    }
}
