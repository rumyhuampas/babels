package babelsManagers;

import babelsInterfaces.IBabelsDialog;
import babelsListeners.KeyListenerType;
import babelsListeners.txtFieldListener;
import babelsObjects.Movement;
import babelsObjects.MovementAdmin;
import babelsObjects.MovementTypes;
import babelsObjects.Print;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CashRegisterTotalsManager  {

    public static Object[] getInfo() throws SQLException {
        try {
            babels.Babels.mysql.Open();

            ArrayList info = new ArrayList();
            ArrayList dayInfo = CashRegisterTotalsManager.getDayInfo();
            ArrayList monthInfo = CashRegisterTotalsManager.getMonthInfo();
            ArrayList yearInfo = CashRegisterTotalsManager.getYearInfo();
            info.add(dayInfo);
            info.add(monthInfo);
            info.add(yearInfo);
                
            return info.toArray();

        } finally {
            babels.Babels.mysql.Close();
        }
    }
    
    private static ArrayList getDayInfo() throws SQLException {            
        Date begin = new Date();
        Date end = new Date();
        Calendar cal = new GregorianCalendar();
        Calendar calfin = new GregorianCalendar();
        cal.setTime(begin);
        calfin.setTime(end);
        Date Begining = cal.getTime();
        Date Final = calfin.getTime();

        ArrayList res = new ArrayList();
        Object[] absales = CashRegisterTotalsManager.getABSales(Begining, Final);
        Object[] xsales = CashRegisterTotalsManager.getXSales(Begining, Final);
        res.add(absales);
        res.add(xsales);
        
        return res;
    }
    
    private static ArrayList getMonthInfo() throws SQLException {            
        //Date begin = new Date();
        Date end = new Date();
        Calendar cal = new GregorianCalendar();
        Calendar calfin = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
        //cal.setTime(begin);
        calfin.setTime(end);
        Date Begining = cal.getTime();
        Date Final = calfin.getTime();

        ArrayList res = new ArrayList();
        Object[] absales = CashRegisterTotalsManager.getABSales(Begining, Final);
        Object[] xsales = CashRegisterTotalsManager.getXSales(Begining, Final);
        res.add(absales);
        res.add(xsales);
        
        return res;
    }
    
    private static ArrayList getYearInfo() throws SQLException {            
        //Date begin = new Date();
        Date end = new Date();
        Calendar cal = new GregorianCalendar();
        Calendar calfin = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_YEAR,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_YEAR));
        //cal.setTime(begin);
        calfin.setTime(end);
        Date Begining = cal.getTime();
        Date Final = calfin.getTime();

        ArrayList res = new ArrayList();
        Object[] absales = CashRegisterTotalsManager.getABSales(Begining, Final);
        Object[] xsales = CashRegisterTotalsManager.getXSales(Begining, Final);
        res.add(absales);
        res.add(xsales);
        
        return res;
    }
    
    private static Object[] getABSales(Date BeginingDate, Date FinalDate) throws SQLException {
        ArrayList optList = new ArrayList();
        
        MovementTypes mt = new MovementTypes(babels.Babels.mysql.Conn);
        mt.Load(MovementTypes.MT_VENTA_A);
        optList.add(mt);
        mt = new MovementTypes(babels.Babels.mysql.Conn);
        mt.Load(MovementTypes.MT_VENTA_B);
        optList.add(mt);

        return MovementAdmin.GetMovements(babels.Babels.mysql.Conn, BeginingDate, FinalDate, optList);
    }
    
    private static Object[] getXSales(Date BeginingDate, Date FinalDate) throws SQLException {
        ArrayList optList = new ArrayList();
        
        MovementTypes mt = new MovementTypes(babels.Babels.mysql.Conn);
        mt.Load(MovementTypes.MT_VENTA_X);
        optList.add(mt);

        return MovementAdmin.GetMovements(babels.Babels.mysql.Conn, BeginingDate, FinalDate, optList);
    }
}