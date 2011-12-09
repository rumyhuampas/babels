package babels;

import babelsObjects.FormsFactory;
import babelsObjects.MySQL;
import babelsObjects.Session;
import java.sql.SQLException;

public class Babels {

    public static MySQL mysql = new MySQL();
    public static Session session = null;

    public static void main(String[] args) throws SQLException {
        FormsFactory.GetDialogForm("babelsForms.Login", false);
    }
}
