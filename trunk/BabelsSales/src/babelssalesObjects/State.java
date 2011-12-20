package babelssalesObjects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class State {

    private final String TABLENAME = "States";
    private final String FIELD_ID = "Id";
    private final String FIELD_NAME = "Name";
    private Connection Conn;
    private int Id;
    public String Name;
    public ArrayList Cities;

    public int getId() {
        return this.Id;
    }

    public State(Connection conn) throws SQLException {
        this.Conn = conn;
        this.Cities = new ArrayList();
        Clear();
    }

    public void Clear() {
        this.Id = -1;
        this.Name = "";
        this.Cities.clear();
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectState(qry);
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
            return SelectState(qry);
        } finally {
            qry.close();
        }
    }

    private boolean SelectState(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.Name = results.getString(this.FIELD_NAME);
                this.Cities = City.GetStateCities(this.Conn, this);
                return true;
            } else {
                return false;
            }
        } finally {
            results.close();
        }
    }
}
