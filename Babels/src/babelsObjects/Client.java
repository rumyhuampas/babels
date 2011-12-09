package babelsObjects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Client extends Person {

    private final String TABLENAME = "Clients";

    public Client(Connection conn) throws SQLException {
        super(conn);
    }

    public boolean Load(Integer id) throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setInt(1, id);
            return SelectClient(qry);
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
            return SelectClient(qry);
        } finally {
            qry.close();
        }
    }

    private boolean SelectClient(PreparedStatement qry) throws SQLException {
        ResultSet results = qry.executeQuery();
        try {
            if (results.next()) {
                this.Id = results.getInt(this.FIELD_ID);
                this.Dni = results.getString(this.FIELD_DNI);
                this.Name = results.getString(this.FIELD_NAME);
                this.LastName = results.getString(this.FIELD_LASTNAME);
                this.Phone = results.getString(this.FIELD_PHONE);
                this.Phone2 = results.getString(this.FIELD_PHONE2);
                this.Address = results.getString(this.FIELD_ADDRESS);
                this.State = new State(this.Conn);
                this.State.Load(results.getInt(this.FIELD_IDSTATE));
                this.City = new City(this.Conn);
                this.City.Load(results.getInt(this.FIELD_IDCITY));
                this.Email = results.getString(this.FIELD_EMAIL);

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
            if (!Exists()) {
                return InsertClient();
            } else {
                return false;
            }
        } else {
            return UpdateClient();
        }
    }

    private boolean InsertClient() throws SQLException {
        String sql = "INSERT INTO " + this.TABLENAME + " ("
                + this.FIELD_DNI + "," + this.FIELD_NAME + ","
                + this.FIELD_LASTNAME + "," + this.FIELD_PHONE + ","
                + this.FIELD_PHONE2 + "," + this.FIELD_ADDRESS + ","
                + this.FIELD_IDSTATE + "," + this.FIELD_IDCITY + ","
                + this.FIELD_EMAIL + ") VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Dni);
            qry.setString(2, this.Name);
            qry.setString(3, this.LastName);
            qry.setString(4, this.Phone);
            qry.setString(5, this.Phone2);
            qry.setString(6, this.Address);
            qry.setInt(7, this.State.getId());
            qry.setInt(8, this.City.getId());
            qry.setString(9, this.Email);
            return qry.execute();
        } finally {
            qry.close();
        }
    }

    private boolean UpdateClient() throws SQLException {
        String sql = "UPDATE " + this.TABLENAME + " SET "
                + this.FIELD_DNI + " = ?," + this.FIELD_NAME + " = ?,"
                + this.FIELD_LASTNAME + " = ?," + this.FIELD_PHONE + " = ?,"
                + this.FIELD_PHONE2 + " = ?," + this.FIELD_ADDRESS + " = ?,"
                + this.FIELD_IDSTATE + " = ?," + this.FIELD_IDCITY + " = ?,"
                + this.FIELD_EMAIL + " = ? "
                + "WHERE " + this.FIELD_ID + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Dni);
            qry.setString(2, this.Name);
            qry.setString(3, this.LastName);
            qry.setString(4, this.Phone);
            qry.setString(5, this.Phone2);
            qry.setString(6, this.Address);
            qry.setInt(7, this.State.getId());
            qry.setInt(8, this.City.getId());
            qry.setString(9, this.Email);
            qry.setInt(10, this.Id);
            return qry.execute();
        } finally {
            qry.close();
        }
    }

    public boolean Exists() throws SQLException {
        String sql = "SELECT * FROM " + this.TABLENAME + " WHERE "
                + this.FIELD_DNI + " = ?";
        PreparedStatement qry = this.Conn.prepareStatement(sql);
        try {
            qry.setString(1, this.Dni);
            ResultSet results = qry.executeQuery();
            try {
                if (results.next()) {
                    return true;
                } else {
                    return false;
                }
            } finally {
                results.close();
            }
        } finally {
            qry.close();
        }
    }
}
