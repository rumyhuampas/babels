package babelsListeners;

import babelsManagers.UsersManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class tblUsersListener implements TableModelListener {

    private UsersManager Manager;

    public tblUsersListener(UsersManager manager) {
        Manager = manager;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if (this.Manager.RefreshingTable == false) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            try {
                if (this.Manager.RunValidations() == true) {
                    this.Manager.UpdateUser(row, column);
                }
            } catch (SQLException ex) {
                Logger.getLogger(tblUsersListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
