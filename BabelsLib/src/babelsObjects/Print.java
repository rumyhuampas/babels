package babelsObjects;

import java.sql.*;

public class Print {

    private static final String TABLENAME = "SalesPrints";
    private static final String FIELD_ID = "Id";
    private static final String FIELD_IDSALE = "IdSale";
    private static final String FIELD_DATEPOSTED = "DatePosted";
    private static final String FIELD_STATUS = "Status";
    private static final String FIELD_DATEPRINTED = "DatePrinted";
    private Connection Conn;
    private int Id;
    public Sale Sale;
    private Date DatePosted;
    private Date DatePrinted;
    private String Status;

    public int getId() {
        return this.Id;
    }

    public String getStatus() {
        return this.Status;
    }

    public Date getDatePosted() {
        return this.DatePosted;
    }

    public Date getDatePrinted() {
        return this.DatePrinted;
    }

    public Print(Connection conn) throws SQLException {
        this.Conn = conn;
        this.Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.Sale = null;
        this.DatePosted = null;
        this.DatePrinted = null;
        this.Status = PrintAdmin.ST_PEND;
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
                this.Sale = new Sale(this.Conn);
                this.Sale.Load(results.getInt(this.FIELD_IDSALE));
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
            return UpdatePrint();
        }
    }

    private boolean InsertPrint() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_IDSALE + "," + this.FIELD_DATEPOSTED + ","
                + this.FIELD_STATUS
                + ") VALUES (?,?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setInt(1, this.Sale.getId());
            qry.setDate(2, (Date) DateUtils.now());
            qry.setString(3, this.Status);
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

    private boolean UpdatePrint() throws SQLException {
        String sql = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_IDSALE + " = ?, "
                + this.FIELD_STATUS + " = ?, "
                + this.FIELD_DATEPRINTED + " = ? "
                + "WHERE " + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, this.Sale.getId());
            qry.setString(2, this.Status);
            qry.setDate(3, (Date) this.DatePrinted);
            qry.setInt(4, this.Id);
            return qry.executeUpdate() > 0;
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
