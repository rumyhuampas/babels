package babelsObjects;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovementAdmin {

    private static Object[] GetMovements(Connection conn, Date BeginingDate, Date FinalDate) throws SQLException {

        DateFormat Sdf = new SimpleDateFormat("yyyy-MM-dd");
        String BeginingDateStr = Sdf.format(BeginingDate);
        String FinalDateStr = Sdf.format(FinalDate);
        String sql = "SELECT t." + MovementTypes.FIELD_NAME + ", m." + Movement.FIELD_DATEPOSTED + ",m." + Movement.FIELD_AMOUNT + ","
                + " c." + Cancelation.FIELD_CANCELLERMOVEID + ", m." + Movement.FIELD_ID
                + " FROM " + MovementTypes.TABLENAME + " t,  " + Movement.TABLENAME + " m LEFT JOIN"
                + " " + Cancelation.TABLENAME + " c ON c." + Cancelation.FIELD_CANCELEDMOVEID + " = m." + Movement.FIELD_ID
                + " WHERE DATE(m." + Movement.FIELD_DATEPOSTED + ")BETWEEN '" + BeginingDateStr + "' and '" + FinalDateStr + "'"
                + " and t." + MovementTypes.FIELD_ID + " = m." + Movement.FIELD_TYPE
                + " ORDER BY m." + Movement.FIELD_DATEPOSTED;

        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ArrayList rows = new ArrayList();
            ArrayList row = new ArrayList();
            ResultSet results = qry.executeQuery();
            try {
                while (results.next()) {
                    row.add(results.getString(MovementTypes.FIELD_NAME));
                    // uso getString y no getDate porque asi me devuelve la hora tb
                    row.add(results.getString(Movement.FIELD_DATEPOSTED));
                    row.add(results.getFloat(Movement.FIELD_AMOUNT));
                    row.add(results.getFloat(Cancelation.FIELD_CANCELLERMOVEID));
                    row.add(results.getInt(Movement.FIELD_ID));
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

    public static Object[] GetTodayMovements(Connection Conn) throws SQLException {
        Date fecha = new Date();
        return GetMovements(Conn, fecha, fecha);
    }

    public static boolean IsCashOpen(Connection conn) throws SQLException {
        String sql = "SELECT m.* FROM " + Movement.TABLENAME + " m, " + MovementTypes.TABLENAME + " t"
                + " WHERE m." + Movement.FIELD_TYPE + "=t." + MovementTypes.FIELD_ID
                + " AND t." + MovementTypes.FIELD_NAME
                + " IN ('" + MovementTypes.MT_APER + "','" + MovementTypes.MT_CIERRE + "','" + MovementTypes.MT_CIERREPARC + "')"
                + " AND Date(m." + Movement.FIELD_DATEPOSTED + ") = CurDate()"
                + " ORDER BY m." + Movement.FIELD_DATEPOSTED + " DESC LIMIT 1";

        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            ResultSet results = qry.executeQuery();
            try {
                MovementTypes aper = new MovementTypes(conn);
                aper.Load(MovementTypes.MT_APER);

                while (results.next()) {
                    if (results.getInt(Movement.FIELD_TYPE) == aper.getId()) {
                        return true;
                    }
                }
                return false;
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }
}
