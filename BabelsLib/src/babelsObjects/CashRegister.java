package babelsObjects;

import java.sql.*;

public class CashRegister {

    public final String TABLENAME = "cashregister";
    public final String FIELD_ID= "Id";
    public final String FIELD_IDACTIONTYPE = "IdActionType";
    public final String FIELD_DATEPOSTED = "DatePosted";
    public final String FIELD_IDUSER = "IdUser";
    public final String FIELD_IDPOS = "IdPos";
    private Connection Conn;
    public int IdActionType;
    public Date DatePosted;
    public int IdUser;
    public int IdPos;
    public int Id;
   // public float Amount;
   // public CashMovements cashMov;

    public CashRegister(Connection conn) throws SQLException {
        this.Conn = conn;
        this.Clear();
        
    }

    public int getId() {
        return this.Id;
    }

    public void Clear() {
        this.Id = -1;
        this.DatePosted = null;
        this.IdActionType = 0;
        this.IdPos = 0;
        this.IdUser = 0;
    }

    public boolean InsertCashRegister() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_IDACTIONTYPE + ", " + this.FIELD_DATEPOSTED + ", "
                + this.FIELD_IDUSER + ", " + this.FIELD_IDPOS +  ""
                + ") VALUES (?,?,?,?)";
        
        PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setInt(1, this.IdActionType);
            qry.setDate(2, this.DatePosted);
            qry.setInt(3, this.IdUser);
            qry.setInt(4, this.IdPos);
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
   
    public boolean GetStateCashRegister() throws SQLException{
        String sql = "SELECT `cashregister`.* FROM `cashregister` where DATE(`cashregister`.`Dateposted`)= curdate()"
                + " order by DatePosted desc LIMIT 1 ; ";
 
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
             return SelectCashRegister(qry);
        } finally {
            qry.close();
        }
    }

    private boolean SelectCashRegister(PreparedStatement qry) throws SQLException {
         ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.IdActionType = results.getInt(this.FIELD_IDACTIONTYPE);
                this.DatePosted = results.getDate(this.FIELD_DATEPOSTED);
                this.IdUser = results.getInt(this.FIELD_IDUSER);
                this.IdPos = results.getInt(this.FIELD_IDPOS);
                return true;
            } else {
                return false;
            }
        } finally {
            results.close();
        }
    }
}
