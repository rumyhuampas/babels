package babelsManagers;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import babels.Babels;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

public class ReportManager {

    private JTable tblCashReg;
    private TableModel Model;

    public ReportManager(JTable tblMovements) {
        this.tblCashReg = tblMovements;
        this.Model = tblCashReg.getModel();
    }

    public void print() throws SQLException {
        try {
            Babels.mysql.Open();
            try {
                JasperReport report = JasperCompileManager.compileReport("report5.jrxml");
                JasperPrint print = JasperFillManager.fillReport(report, null, new JRTableModelDataSource(this.Model));
                JasperViewer.viewReport(print);
            } catch (JRException ex) {
                Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            Babels.mysql.Close();
        }
    }
}
