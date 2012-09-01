package babelsObjects;

import java.sql.*;

public class Print {

    private static final String TABLENAME = "Prints";
    private static final String FIELD_ID = "Id";
    private static final String FIELD_IDMOVE = "IdMove";
    private static final String FIELD_DATEPOSTED = "DatePosted";
    private static final String FIELD_STATUS = "Status";
    private static final String FIELD_DATEPRINTED = "DatePrinted";
    private static final String FIELD_PRINTER = "Printer";
    
    public static final String ST_PEND = "Pending";
    public static final String ST_PRIN = "Printing";
    public static final String ST_COMP = "Completed";
    public static final String ST_FAIL = "Failed";

    public static final String PR_FIS = "FISCAL";
    public static final String PR_NOFIS = "NOFISCAL";
    
    private Connection Conn;
    private int Id;
    public Movement Move;
    public Date DatePosted;
    public Date DatePrinted;
    public String Status;
    public String Printer;

    public int getId() {
        return this.Id;
    }

    public Print(Connection conn) throws SQLException {
        this.Conn = conn;
        this.Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.Move = null;
        this.DatePosted = null;
        this.DatePrinted = null;
        this.Status = this.ST_PEND;
        this.Printer = "";
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectPrint(qry);
        } finally {
            qry.close();
        }
    }

    private boolean SelectPrint(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.DatePosted = results.getDate(this.FIELD_DATEPOSTED);
                this.DatePrinted = results.getDate(this.FIELD_DATEPRINTED);
                this.Status = results.getString(this.FIELD_STATUS);
                this.Printer = results.getString(this.FIELD_PRINTER);
                this.Move = new Movement(this.Conn);
                this.Move.Load(results.getInt(this.FIELD_IDMOVE));
                return true;
            } else {
                return false;
            }
        } finally {
            results.close();
        }
    }

    public boolean Save() throws SQLException {
        if (this.Id == -1) {
            return InsertPrint();
        } else {
            return false;
        }
    }

    private boolean InsertPrint() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_IDMOVE + "," + this.FIELD_DATEPOSTED + ","
                + this.FIELD_STATUS + "," + this.FIELD_PRINTER
                + ") VALUES (?,NOW(),?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setInt(1, this.Move.getId());
            qry.setString(2, this.Status);
            qry.setString(3, this.Printer);
            if (qry.executeUpdate() > 0) {
                ResultSet result = qry.getGeneratedKeys();
                result.next();
                this.Id = result.getInt("GENERATED_KEY");
                return true;
            } else {
                return false;
            }
        } finally {
            qry.close();
        }
    }

    public boolean Delete() throws SQLException {
        String sql = "DELETE FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, this.Id);
            if (qry.executeUpdate() > 0) {
                this.Id = -1;
                return true;
            } else {
                return false;
            }
        } finally {
            qry.close();
        }
    }
}
