package babelsObjects;

import java.sql.Connection;
import java.sql.SQLException;

public class Session {

    private Connection Conn;
    public User CurrentUser;

    public Session(Connection conn) throws SQLException {
        this.Conn = conn;
        this.CurrentUser = new User(this.Conn);
    }

    public boolean Login(String name, String pass) throws SQLException {
        this.CurrentUser.Load(name);
        if (this.CurrentUser.Active == true
                && this.CurrentUser.Pass.equals(pass)) {
            return true;
        } else {
            return false;
        }
    }
}