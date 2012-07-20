
package babelsreports;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class ReportCashRegisterClassDataSource implements JRDataSource{

  
    private List<ReportCashRegisterClass> listaReporte = new ArrayList<ReportCashRegisterClass>();
    private int indice = -1;
    
   public Object getFieldValue(JRField jrField) throws JRException
{ 
    Object valor = null;  

    if("id".equals(jrField.getName())) 
    { 
        valor = listaReporte.get(indice).getId(); 
    } 
    else if("movimiento".equals(jrField.getName())) 
    { 
        valor = listaReporte.get(indice).getMovimiento(); 
    } 
    else if("fecha".equals(jrField.getName())) 
    { 
        valor = listaReporte.get(indice).getFecha(); 
    } 
    else if("monto".equals(jrField.getName())) 
    { 
        valor = listaReporte.get(indice).getMonto(); 
    }
     
     else if("subTotal".equals(jrField.getName())) 
    { 
        valor = listaReporte.get(indice).getSubTotal(); 
    }
 
    return valor; 
}

    public boolean next() throws JRException
    {
        return ++indice < listaReporte.size();
    }
    public void addReportCashRegisterClass(ReportCashRegisterClass reportClass)
{
    this.listaReporte.add(reportClass);
}
}