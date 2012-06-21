package babelsObjects;

import java.sql.*;

public class Cancelation {

    private final String TABLENAME = "Cancelations";
    private final String FIELD_ID = "Id";
    private final String FIELD_CANCELLERMOVEID = "CancellerMoveId";
    private final String FIELD_CANCELEDMOVEID = "CanceledMoveId";
    private Connection Conn;
    private int Id;
    public Sale CancellerMove;
    public Sale CanceledMove;

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
                this.CancellerMove.Load(results.getInt(this.FIELD_CANCELLERMOVEID));
                this.CanceledMove = new Sale(Conn);
                this.CanceledMove.Load(results.getInt(this.FIELD_CANCELLERMOVEID));
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
                + ") VALUES (?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setInt(1, this.CancellerMove.getId());
            qry.setInt(2, this.CanceledMove.getId());
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
                + this.FIELD_CANCELEDMOVEID + " = ? "
                + "WHERE " + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, this.CancellerMove.getId());
            qry.setInt(2, this.CanceledMove.getId());
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
