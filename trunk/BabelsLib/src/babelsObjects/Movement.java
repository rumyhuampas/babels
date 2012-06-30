package babelsObjects;

import java.sql.*;
import java.util.ArrayList;

public class Movement {

    public static final String TABLENAME = "movements";
    public static final String FIELD_ID = "Id";
    public static final String FIELD_TYPE = "Type";
    public static final String FIELD_DATEPOSTED = "DatePosted";
    public static final String FIELD_AMOUNT = "Amount";
    public static final String FIELD_USER = "IdUser";
    public Connection Conn;
    public int Id;
    public float Amount;
    public Date DatePosted;
    public MovementTypes Type;
    public User User;

    public Movement(Connection conn) {
        this.Conn = conn;
        this.Clear();
    }
    
    public int getId(){
        return this.Id;
    }

    private void Clear() {
        this.Id = -1;
        this.DatePosted = null;
        this.Amount = 0;
        this.Type = null;
        this.User = null;
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectMovement(qry);
        } finally {
            qry.close();
        }
    }

    protected boolean SelectMovement(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.Amount = results.getFloat(this.FIELD_AMOUNT);
                this.DatePosted = results.getDate(this.FIELD_DATEPOSTED);
                this.Type = new MovementTypes(this.Conn);
                this.Type.Load(results.getInt(this.FIELD_TYPE));
                this.User = new User(this.Conn);
                this.User.Load(results.getInt(this.FIELD_USER));
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
            return InsertMovement();
        } else {
            return false;
        }

    }

    protected boolean InsertMovement() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_AMOUNT + ", " + this.FIELD_DATEPOSTED + ", "
                + this.FIELD_TYPE + ", " + this.FIELD_USER + ""
                + ") VALUES (?,now(),?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setFloat(1, this.Amount);
            qry.setInt(2, this.Type.getId());
            if(this.User != null){
                qry.setInt(3, this.User.getId());
            }
            else{
                qry.setNull(3, java.sql.Types.INTEGER);
            }
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
