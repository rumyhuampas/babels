package babelsObjects;

import java.sql.*;

public class Cancelation {

    private final String TABLENAME = "Cancelations";
    private final String FIELD_ID = "Id";
    private final String FIELD_CANCELERSALEID = "CancelerSaleId";
    private final String FIELD_CANCELEDSALEID = "CanceledSaleId";
    private final String FIELD_DATEPOSTED = "DatePosted";
    private Connection Conn;
    private int Id;
    public Sale CancelerSale;
    public Sale CanceledSale;
    private Date CancelationDate;

    public int getId() {
        return this.Id;
    }
    
    public Date getDate(){
        return this.CancelationDate;
    }

    public Cancelation(Connection conn) throws SQLException {
        this.Conn = conn;
        this.Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.CancelerSale = null;
        this.CanceledSale = null;
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectCancelation(qry);
        } finally {
            qry.close();
        }
    }

    public boolean LoadByCanceler(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_CANCELERSALEID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectCancelation(qry);
        } finally {
            qry.close();
        }
    }

    public boolean LoadByCanceled(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_CANCELEDSALEID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectCancelation(qry);
        } finally {
            qry.close();
        }
    }

    private boolean SelectCancelation(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.CancelerSale = new Sale(Conn);
                this.CancelerSale.Load(results.getInt(this.FIELD_CANCELERSALEID));
                this.CanceledSale = new Sale(Conn);
                this.CanceledSale.Load(results.getInt(this.FIELD_CANCELEDSALEID));
                this.CancelationDate = results.getDate(FIELD_DATEPOSTED);
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
            return InsertCancelation();
        } else {
            return UpdateCancelation();
        }
    }

    private boolean InsertCancelation() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_CANCELERSALEID + ", " + this.FIELD_CANCELEDSALEID + ","
                + this.FIELD_DATEPOSTED
                + ") VALUES (?,?,NOW())";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setInt(1, this.CancelerSale.getId());
            qry.setInt(2, this.CanceledSale.getId());
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

    private boolean UpdateCancelation() throws SQLException {
        String sql = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_CANCELERSALEID + " = ?,"
                + this.FIELD_CANCELEDSALEID + " = ? "
                + "WHERE " + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, this.CancelerSale.getId());
            qry.setInt(2, this.CanceledSale.getId());
            qry.setInt(3, this.Id);
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
