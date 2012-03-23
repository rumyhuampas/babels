package babelsprinter;

import babelsObjects.MySQL;
import babelsObjects.PrintAdmin;
import babelsObjects.Sale;
import babelsprinter.helpers.Printer;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BabelsPrinter {

    public static MySQL mysql = new MySQL("localhost", "babels", "root", "");

    public static void main(String[] args) {
        try {
            mysql.Open();
            try {
                ArrayList pendingPrints = PrintAdmin.GetPendingPrints(mysql.Conn);
                Printer printer = new Printer();
                for (int i = 0; i < pendingPrints.size(); i++) {
                    try{
                        Sale sale = (Sale) pendingPrints.get(i);
                        printer.Print(sale);
                    }
                    catch(Exception ex){
                        
                    }
                }
            } finally {
                mysql.Close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error " + ex.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
