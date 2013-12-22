package babelsManagers;

import babels.Babels;
import babelsObjects.Print;
import babelsObjects.PrintAdmin;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PrintsManager {
    private JTable Table;
    
    private TableModel Model;
    public boolean RefreshingTable;

    public PrintsManager(JTable table) {
        this.Table = table;
        this.Model = this.Table.getModel();
    }
    
    public void ClearTable() {
        DefaultTableModel tm = (DefaultTableModel) this.Model;
        tm.getDataVector().removeAllElements();
    }
    public void RefreshTable() throws SQLException {
        this.RefreshingTable = true;
        this.ClearTable();
        LoadPrints();
        this.RefreshingTable = false;
    }
    
    private void LoadPrints() throws SQLException {
        Babels.mysql.Open();
        try {
            //Print print = new Print(Babels.mysql.Conn);
            String printers = "'" + Print.PR_FIS + "','" + Print.PR_CLIENTE + "'";
            Object[] rows = PrintAdmin.GetAllPrints(Babels.mysql.Conn, printers, 100);
            if(rows.length!=0){
                Object[] row = null;
                DefaultTableModel tm = (DefaultTableModel) this.Model;
                Object[] rowTable= null;
                for (int rowIdx = 0; rowIdx < rows.length; rowIdx++) {
                    row = (Object[]) rows[rowIdx];
                    rowTable= new Object[6];
                    rowTable[0]=row[0];
                    rowTable[1]=row[1];
                    rowTable[2]=row[2];
                    rowTable[3]=row[3];
                    rowTable[4]=row[4];
                    rowTable[5]=row[5];
                    tm.addRow(rowTable);
                }
            }
            else {
                DefaultTableModel tm = (DefaultTableModel) this.Model;
                tm.getDataVector().removeAllElements();
            }
        } finally {
            Babels.mysql.Close();
        }
    }
    
    public void RestartJob(int jobId) throws SQLException{
        Babels.mysql.Open();
        try {
            Print print = new Print(Babels.mysql.Conn);
            print.Load(jobId);
            print.Status = Print.ST_PEND;
            print.Retries = 0;
            print.DatePrinted = null;
            print.Save();
        } finally {
            Babels.mysql.Close();
        }
    }
}
