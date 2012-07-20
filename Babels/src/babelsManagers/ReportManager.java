package babelsManagers;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import babels.Babels;
import babelsreports.ReportCashRegisterClass;
import babelsreports.ReportCashRegisterClassDataSource;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportManager {

    private JTable tblCashReg;
    private TableModel Model;
    private int index = -1;
    private HashMap columnNames = new HashMap();

    public ReportManager(JTable tblMovements) {
        this.tblCashReg = tblMovements;
        this.Model = tblCashReg.getModel();
    }
 
   
    public void print() throws SQLException, JRException, FileNotFoundException {
       


            /*
             * JasperReport report =
             * JasperCompileManager.compileReport("report5.jrxml"); JasperPrint
             * print = JasperFillManager.fillReport(report, null, new
             * JRTableModelDataSource(this.Model));
             * JasperViewer.viewReport(print);
             */
        ReportCashRegisterClass clasesReportes;
        ReportCashRegisterClassDataSource datasource = new ReportCashRegisterClassDataSource();
        for (int fila = 0; fila < Model.getRowCount(); fila++) {
          
            clasesReportes = new ReportCashRegisterClass( 
                    String.valueOf(this.tblCashReg.getValueAt(fila, 0)),
                    String.valueOf(this.tblCashReg.getValueAt(fila, 1)),
                    String.valueOf(this.tblCashReg.getValueAt(fila, 2)), 
                    Float.valueOf(""+this.tblCashReg.getValueAt(fila, 3)), 
                   // String.valueOf(this.tblCashReg.getValueAt(fila, 4)),                    
                    Float.valueOf(""+this.tblCashReg.getValueAt(fila, 5)));
            datasource.addReportCashRegisterClass(clasesReportes);
        }

        FileInputStream fis = new FileInputStream("reportCashRegister.jasper");
          BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
                      
        
            //Map pars = new HashMap();
            //pars.put("LOGO_URL", "logo.gif");
          
            //Collection List<ReportCashRegisterClass> lista = this.getTableList();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport, null, datasource);

           JasperViewer.viewReport(jasperPrint);
       
    }
}
