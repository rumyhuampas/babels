package babelsManagers;

import babels.Babels;
import babelsListeners.tblUsersListener;
import babelsObjects.User;
import babelsObjects.UsersAdmin;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class UsersManager {

    private JTable Table;
    private TableModel Model;
    public TableModelListener Listener;
    public boolean RefreshingTable;

    public UsersManager(JTable table) {
        this.Table = table;
        this.Model = this.Table.getModel();
        this.Listener = new tblUsersListener(this);
        this.Model.addTableModelListener(this.Listener);
    }

    public void RefreshTable() throws SQLException {
        this.RefreshingTable = true;
        ClearTable();
        LoadUsers();
        this.RefreshingTable = false;
    }

    private void ClearTable() {
        DefaultTableModel tm = (DefaultTableModel) this.Model;
        tm.getDataVector().removeAllElements();
    }

    private void LoadUsers() throws SQLException {
        Babels.mysql.Open();
        try {
            Object[] rows = UsersAdmin.GetAllUsers(Babels.mysql.Conn);
            Object[] row = null;
            DefaultTableModel tm = (DefaultTableModel) this.Model;
            for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
                row = (Object[]) rows[rowIdx];
                tm.addRow(row);
            }
        } finally {
            Babels.mysql.Close();
        }
    }

    public boolean RunValidations() throws SQLException {
        if (CheckOneAdmin() == true) {
            if (CheckOneActive() == true) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Debe existir al menos un usuario activo",
                        "Error", JOptionPane.ERROR_MESSAGE);
                RefreshTable();
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe existir al menos un usuario administrador activo",
                    "Error", JOptionPane.ERROR_MESSAGE);
            RefreshTable();
            return false;
        }
    }

    private boolean CheckOneAdmin() {
        for (int i = 0; i < this.Model.getRowCount(); i++) {
            if ((Boolean) this.Model.getValueAt(i, 2) == true
                    && (Boolean) this.Model.getValueAt(i, 3) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean CheckOneActive() {
        return (CheckIfColumnHasOneTrue(3));
    }

    private boolean CheckIfColumnHasOneTrue(int colIdx) {
        for (int i = 0; i < this.Model.getRowCount(); i++) {
            if ((Boolean) this.Model.getValueAt(i, colIdx) == true) {
                return true;
            }
        }
        return false;
    }

    public void UpdateUser(int row, int column) throws SQLException {
        Babels.mysql.Open();
        try {
            User user = new User(Babels.mysql.Conn);
            user.Load((Integer) this.Model.getValueAt(row, 0));
            user.IsAdmin = (Boolean) this.Model.getValueAt(row, 2);
            user.Active = (Boolean) this.Model.getValueAt(row, 3);
            if (user.Save() == false) {
                JOptionPane.showMessageDialog(null, "Error al guardar usuario",
                        "Error", JOptionPane.ERROR_MESSAGE);
                RefreshTable();
            }
        } finally {
            Babels.mysql.Close();
        }
    }
}
