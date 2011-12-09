package babelsObjects;

import babelsInterfaces.IConn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQL implements IConn {

    private final String BD = "babels";
    private final String LOGIN = "root";
    private final String PASS = "";
    private final String URL = "jdbc:mysql://localhost/" + BD;
    public Connection Conn;

    @Override
    public boolean Open() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            this.Conn = DriverManager.getConnection(this.URL, this.LOGIN, this.PASS);
            if (this.Conn != null) {
                return true;
            } else {
                return false;
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean Close() {
        try {
            this.Conn.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
