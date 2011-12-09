package babelsObjects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class City {

    private static final String TABLENAME = "Cities";
    private static final String FIELD_ID = "Id";
    private static final String FIELD_NAME = "Name";
    private static final String FIELD_IDCITY = "IdCity";
    private static final String FIELD_IDSTATE = "IdState";
    private Connection Conn;
    private int Id;
    public String Name;

    public int getId() {
        return this.Id;
    }

    public City(Connection conn) throws SQLException {
        this.Conn = conn;
        Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.Name = "";
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectCity(qry);
        } finally {
            qry.close();
        }
    }

    public boolean Load(String name) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_NAME + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, name);
            return SelectCity(qry);
        } finally {
            qry.close();
        }
    }

    private boolean SelectCity(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.Name = results.getString(this.FIELD_NAME);
                return true;
            } else {
                return false;
            }
        } finally {
            results.close();
        }
    }

    static ArrayList GetStateCities(Connection conn, State state) throws SQLException {
        String sql = "SELECT C.* FROM " + TABLENAME + " C, StatesCities SC WHERE "
                + "SC." + FIELD_IDSTATE + " = ? "
                + "AND SC." + FIELD_IDCITY + " = C." + FIELD_ID + " "
                + "ORDER BY C." + FIELD_NAME + "";
        PreparedStatement qry = conn.prepareStatement(sql);
        try {
            qry.setInt(1, state.getId());
            ArrayList result = new ArrayList();
            ResultSet results = qry.executeQuery(sql);
            try {
                while (results.next()) {
                    City city = new City(conn);
                    city.Load(results.getInt(FIELD_ID));
                    result.add(city);
                }
                return result;
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }
}
