package babelsManagers;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import babels.Babels;
import babelsreports.ReportCashRegisterClass;
import babelsreports.ReportCashRegisterClassDataSource;
import java.awt.Desktop;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;

public class ReportManager {

    private JTable tblCashReg;
    private TableModel Model;
    private int index = -1;
    private HashMap columnNames = new HashMap();

    public ReportManager(JTable tblMovements) {
        this.tblCashReg = tblMovements;
        this.Model = tblCashReg.getModel();
    }
 
   
    public void print() throws SQLException, JRException, FileNotFoundException, IOException {
       


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

        String appPath = Babels.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        appPath = appPath.replaceAll("%20", " ");
        if (appPath.substring(appPath.length() - 3).equalsIgnoreCase("jar")) {
            appPath = appPath.substring(0, appPath.length() - 11);
        }
        FileInputStream fis = new FileInputStream(appPath + "\\reportCashRegister.jasper");
          BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
                      
        
            //Map pars = new HashMap();
            //pars.put("LOGO_URL", "logo.gif");
          
            //Collection List<ReportCashRegisterClass> lista = this.getTableList();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport, null, datasource);

           //JasperViewer.viewReport(jasperPrint);
            //JDialog viewer = new JDialog(new JFrame(),"Vista previa del reporte", true); 
            //viewer.setSize(800,600); 
           // viewer.setLocationRelativeTo(null); 
            JRViewer jrv = new JRViewer(jasperPrint); 
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setVisible(true);
                        
            //viewer.getContentPane().add(jrv); 
            //viewer.show(); 
           
           // String filename = "Reporte Movimientos de Caja" + ".pdf";
           //  OutputStream output = new FileOutputStream(new File("C:/"+filename+"")); 
           //  File path = new File ("C:/"+filename+"");
           // JasperExportManager.exportReportToPdfStream(jasperPrint, output); 
           
         //   output.flush();
          //  output.close();
          //  File path = new File ("C:/"+filename+"");
       // Desktop.getDesktop().open(path);
          
    }
}
