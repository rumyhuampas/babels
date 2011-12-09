package babelsObjects;

import babels.Babels;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class UsersAdmin implements TableModelListener {

    private static final String TABLENAME = "Users";
    private static final String FIELD_ID = "Id";
    private static final String FIELD_NAME = "Name";
    private static final String FIELD_ISADMIN = "IsAdmin";
    private static final String FIELD_ACTIVE = "Active";
    private JTable Table;
    
    public UsersAdmin(JTable table){
        Table = table;
    }

    public static Object[] GetAllUsers(Connection conn) throws SQLException {
        String sql = "SELECT * FROM " + TABLENAME + " ORDER BY NAME ";
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ArrayList rows = new ArrayList();
            ArrayList row = new ArrayList();
            ResultSet results = qry.executeQuery(sql);
            try {
                while (results.next()) {
                    row.add(results.getInt(FIELD_ID));
                    row.add(results.getString(FIELD_NAME));
                    row.add(results.getBoolean(FIELD_ISADMIN));
                    row.add(results.getBoolean(FIELD_ACTIVE));
                    rows.add(row.toArray());
                }
                return rows.toArray();
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel) e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        if (CheckOneAdmin(model) == true){
            if (CheckOneActive())
        }
        else{
            JOptionPane.showMessageDialog(null, "Debe existir al menos un usuario administrador",
                        "Error", JOptionPane.ERROR_MESSAGE);
            this.Table
        }
    }
    
    private boolean CheckOneAdmin(TableModel model){
        for (int i=0;i<model.getRowCount();i++){
            if ((Boolean)model.getValueAt(i, 2) == true){
                return true;
            }
        }
        return false;
    }
    
    private void UpdateUser(){
        Babels.mysql.Open();
        try {
            try {
                User user = new User(Babels.mysql.Conn);
                user.Load((Integer) model.getValueAt(row, 0));

            } catch (SQLException ex) {
                Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            Babels.mysql.Close();
        }
    }
}
