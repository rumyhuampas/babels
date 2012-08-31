package babelsManagers;

import babels.Babels;
import babelsObjects.Client;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ClientsManager {

    private JTable Table;
    private TableModel Model;
    public boolean RefreshingTable;
    private JTextField txtName;

    public ClientsManager(JTable table, JTextField txtName) {
        this.Table = table;
        this.Model = this.Table.getModel();
        this.txtName = txtName;
    }
     public void ClearTable() {
        DefaultTableModel tm = (DefaultTableModel) this.Model;
        tm.getDataVector().removeAllElements();
    }
      public void RefreshTable() throws SQLException {
        this.RefreshingTable = true;
        ClearTable();
        LoadClient(txtName);
        this.RefreshingTable = false;
    }
      private void LoadClient(JTextField txtName) throws SQLException {
        Babels.mysql.Open();
        try {
            Client client = new Client(Babels.mysql.Conn);
            Object[] rows = client.GetClient(txtName.getText());
            Object[] row = null;
            DefaultTableModel tm = (DefaultTableModel) this.Model;
            Object[] rowTable= null;
            for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
                row = (Object[]) rows[rowIdx];
                rowTable= new Object[7];
                rowTable[0]=row[0];
                rowTable[1]=row[1];
                rowTable[2]=row[2];
                rowTable[3]=row[3];
                rowTable[4]=row[4];
                rowTable[5]=row[5];
                rowTable[6]=row[6];
                tm.addRow(rowTable);
            }
        } finally {
            Babels.mysql.Close();
        }
    }
}
