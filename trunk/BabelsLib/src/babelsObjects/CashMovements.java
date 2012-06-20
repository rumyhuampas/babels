package babelsObjects;

import java.sql.*;
import java.util.ArrayList;

public class CashMovements {

    public static final String TABLENAME = "cashmovement";
    public static final String FIELD_MOVEMENTTYPE = "MovementType";
    public static final String FIELD_DATEPOSTED = "DatePosted";
    public static final String FIELD_TOTAL = "Total";
    public static final String FIELD_ACTIONTYPE = "IdActionType";
    public static final String FIELD_IDSALE = "IdSale";
    public static final String FIELD_IDCANCELATION = "IdCancelation";
    public static final String FIELD_IDCASHREGISTER = "IdCashRegister";
    public static final String FIELD_CANCELEDSALEID = "CanceledSaleId";
    public static final String FIELD_CANCELERSALEID = "CancelerSaleId";
    private Connection Conn;
    public int IdCashRegister, Id;
    public float Total;
    public Date DatePosted;
    public String MovementType;
    
    
    public CashMovements(Connection conn){
        this.Conn = conn;
        this.Clear();
    }
    private void Clear(){
        this.Id = -1;
        this.IdCashRegister = 0;
        this.DatePosted = null;
        this.Total = 0;
    }
    public boolean InsertCashRegister() throws SQLException{
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_IDCASHREGISTER + ", " + this.FIELD_DATEPOSTED + ", "
                + this.FIELD_TOTAL + ", " + this.FIELD_MOVEMENTTYPE + ""
                + ") VALUES (?,?,?,?)";
               PreparedStatement qry = this.Conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            qry.setInt(1, this.IdCashRegister);
            qry.setDate(2, this.DatePosted);
            qry.setFloat(3, this.Total);
            qry.setString(4, this.MovementType);
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
    public static Object[] GetMovements(Connection conn) throws SQLException {
        String sql = "SELECT `cashmovement`.`MovementType`, `cashmovement`.`Dateposted`, `sales`.`Total`,"
                + " `cashregister`.`IdActionType`, `cashmovement`.`IdSale`,"
                + " `cashmovement`.`IdCancelation`, `cashmovement`.`IdCashRegister`,"
                + " `cancelations`.`CanceledSaleId`, `cancelations`.`CancelerSaleId`"
                + " FROM `cancelations` RIGHT JOIN"
                + " `cashmovement` ON `cancelations`.`Id` = `cashmovement`.`IdCancelation`"
                + " LEFT JOIN"
                + " `sales` ON `sales`.`Id` = `cashmovement`.`IdSale` LEFT JOIN"
                + " `cashregister` ON `cashregister`.`Id` = `cashmovement`.`IdCashRegister`"
                + " where DATE(`cashmovement`.`Dateposted`)= curdate()"
                + " order by `cashmovement`.`Dateposted`;";
        
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ArrayList rows = new ArrayList();
            ArrayList row = new ArrayList();
            ResultSet results = qry.executeQuery();
            try {
                while (results.next()) {
                    row.add(results.getString(FIELD_MOVEMENTTYPE));
                // uso getString y no getDate porque asi me devuelve la hora tb
                    row.add(results.getString(FIELD_DATEPOSTED));
                    row.add(results.getFloat(FIELD_TOTAL));
                    row.add(results.getString(FIELD_ACTIONTYPE));
                    row.add(results.getInt(FIELD_IDSALE));
                    row.add(results.getInt(FIELD_IDCANCELATION));
                    row.add(results.getInt(FIELD_IDCASHREGISTER));
                    row.add(results.getInt(FIELD_CANCELEDSALEID));
                    row.add(results.getInt(FIELD_CANCELERSALEID));
                    rows.add(row.toArray());
                    row.clear();
                }
                return rows.toArray();
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }
}
