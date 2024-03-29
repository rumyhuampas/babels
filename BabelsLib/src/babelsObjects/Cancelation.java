package babelsObjects;

import java.sql.*;

public class Cancelation {

    public static final String TABLENAME = "Cancelations";
    public static final String FIELD_ID = "Id";
    public static final String FIELD_CANCELLERMOVEID = "CancellerMoveId";
    public static final String FIELD_CANCELEDMOVEID = "CanceledMoveId";
    public static final String FIELD_TICKETNUMBER = "TicketNumber";
    private Connection Conn;
    private int Id;
    public Sale CancellerMove;
    public Sale CanceledMove;
    private String TicketNumber;

    public int getId() {
        return this.Id;
    }

    public Cancelation(Connection conn) throws SQLException {
        this.Conn = conn;
        this.Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.CancellerMove = null;
        this.CanceledMove = null;
        this.TicketNumber = "";
    }
    
    public String GetTicketNumber(){
        return this.TicketNumber;
    }
    
    public void SetTicketNumber(String ticketNumber){
        if(ticketNumber.length() == 12){
            String firstPart = ticketNumber.substring(0,4);
            String secondPart = ticketNumber.substring(4, 12);
            this.TicketNumber = firstPart + "-" + secondPart;
        }
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

    public boolean LoadByCanceller(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_CANCELLERMOVEID + " = ?";
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
                + this.FIELD_CANCELEDMOVEID + " = ?";
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
                this.CancellerMove = new Sale(Conn);
                this.CancellerMove.Load(results.getInt(Cancelation.FIELD_CANCELLERMOVEID));
                this.CanceledMove = new Sale(Conn);
                this.CanceledMove.Load(results.getInt(this.FIELD_CANCELEDMOVEID));
                this.TicketNumber = results.getString(this.FIELD_TICKETNUMBER);
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
                + this.FIELD_CANCELLERMOVEID + ", " + this.FIELD_CANCELEDMOVEID
                + ", " + this.FIELD_TICKETNUMBER
                + ") VALUES (?,?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setInt(1, this.CancellerMove.getId());
            qry.setInt(2, this.CanceledMove.getId());
            qry.setString(3, this.TicketNumber);
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
                + this.FIELD_CANCELLERMOVEID + " = ?,"
                + this.FIELD_CANCELEDMOVEID + " = ?,"
                + this.FIELD_TICKETNUMBER + " = ? "
                + "WHERE " + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, this.CancellerMove.getId());
            qry.setInt(2, this.CanceledMove.getId());
            qry.setString(3, this.TicketNumber);
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
